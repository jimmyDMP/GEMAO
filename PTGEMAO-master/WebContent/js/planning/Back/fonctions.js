// Generated by CoffeeScript 1.10.0
(function() {
  window.supprimerCreneauCase = function(elem, creneau) {
    var i, indexTd, indexTr, j, ligne, ref, table, td;
    indexTr = $(elem).closest("tr").index();
    indexTd = $(elem).index();
    table = $(elem).closest("table");
    $(elem).removeAttr("style");
    $(elem).removeAttr("rowSpan");
    $(elem).removeAttr("title");
    $(elem).children().remove();
    $(elem).text("");
    $(elem).draggable("destroy");
    $(elem).tooltip("destroy");
    $.removeData(elem, "creneau");
    ligne = $(elem).closest("tr");
    for (i = j = 0, ref = creneau.nbQuartDHeure - 1; 0 <= ref ? j <= ref : j >= ref; i = 0 <= ref ? ++j : --j) {
      td = $(ligne).children().eq(indexTd);
      td.removeAttr("style");
      ligne = $(ligne).next();
    }
    return true;
  };

  window.supprimerCreneauListe = function(elem) {
    return $(elem).remove();
  };

  window.makeDroppableOnglets = function(elem) {
    var e, j, len, results;
    results = [];
    for (j = 0, len = elem.length; j < len; j++) {
      e = elem[j];
      results.push($(e).droppable({
        tolerance: "pointer",
        over: function() {
          console.log($(this).index());
          $("#edt").tabs("option", "active", $(this).index());
          return console.log($(this).find("a").attr("href"));
        }
      }));
    }
    return results;
  };

  window.ancienneTargetHover = null;


  /*
      DROPPABLE
   */

  window.creerZonesDroppables = function() {
    window.makeDroppable($("#edt td"));
    return $("#creneauxAPlacer").droppable({
      drop: function(event, ui) {
        var creneau, l, m;
        if (ui.draggable.prop("tagName") !== "LI") {
          m = new window.CreneauManager();
          creneau = ui.draggable.data("creneau");
          l = $(document.createElement("li"));
          $(l).addClass("creneau");
          $(l).text(creneau.intitule);
          $(l).css("background-color", "#" + creneau.backgroundColor);
          l.data("creneau", creneau);
          m.placerCreneauDansListe(l);
          return supprimerCreneauCase(ui.draggable, creneau);
        }
      }
    });
  };

  window.makeDroppable = function(elem) {
    return $(elem).droppable({
      drop: function(event, ui) {
        var creneau, nbLignes;
        creneau = $(ui.draggable).data("creneau");
        nbLignes = creneau.nbQuartDHeure;
        if (window.ancienneTargetHover !== null && window.ancienneTargetHover !== ui.draggable) {
          $(window.ancienneTargetHover).removeAttr("style");
        }
        if (!window.coursPeutEtrePlaceSurDroppable(this, nbLignes, creneau)) {
          toastr.warning("Cours implaçable", "Impossible");
        } else {
          placerCoursSurDroppable(this, nbLignes, ui.draggable, new window.CreneauManager());
        }
        return window.ancienneTargetHover = null;
      },
      over: function(event, ui) {
        var couleur, style, target;
        target = event.target;
        style = $(target).attr("style");
        couleur = $(ui.draggable).data("creneau").backgroundColor;
        if (target === ui.draggable) {
          console.log("la fin des haricots");
        }
        if (window.ancienneTargetHover !== null && window.ancienneTargetHover !== ui.draggable) {
          $(window.ancienneTargetHover).removeAttr("style");
        }
        if (style !== void 0 && target !== ui.draggable) {
          return window.ancienneTargetHover = null;
        } else {
          $(target).css("background-color", "#" + couleur);
          return window.ancienneTargetHover = target;
        }
      }
    });
  };


  /*
      DRAGGABLE
   */

  window.makeDraggable = function(elem) {
    $(elem).draggable({
      snapMode: "inner",
      snapTolerance: 10,
      zIndex: 100,
      helper: function() {
        var c;
        c = $(elem).clone();
        c.addClass("creneauDrag");
        $(elem).css("cursor", "grabbing");
        c.css("height", "30px");
        c.css("width", "60px");
        return c;
      },
      appendTo: "#edt",
      cursorAt: {
        right: 30,
        bottom: 15
      },
      start: function(event, ui) {
        window.enTrainDeDrag = true;
        window.dragged = ui.draggable;
        return true;
      },
      stop: function(event, ui) {
        window.enTrainDeDrag = false;
        window.dragged = null;
        $(elem).css("cursor", "grab");
        return true;
      }
    });
    $(elem).css("cursor", "grab");
    $(elem).addClass("unselectable");
    return true;
  };


  /*
      GESTION COURS DROPPABLE
   */

  window.placerCoursSurDroppable = (function(_this) {
    return function(elem, nbLignes, draggable, creneauManager, doitMettreAJour) {
      var chaineElem, creneau, i, indexTd, indexTr, ligne, nbTr, table, td, texte;
      if (doitMettreAJour == null) {
        doitMettreAJour = true;
      }
      ligne = $(elem).parent("tr");
      indexTr = $(ligne).index("tr");
      indexTd = $(elem).index();
      chaineElem = ":nth-child(" + (indexTd + 1) + ")";
      table = $(ligne).closest("table");
      nbTr = $(table).find("tr").length;
      texte = draggable.text();
      i = 1;
      while (i < nbLignes && i < nbTr) {
        ligne = $(ligne).next();
        td = $(ligne).find(chaineElem);
        td.css("display", "none");
        i = i + 1;
      }
      $(elem).attr("rowspan", nbLignes);
      $(elem).find("span").remove();
      creneau = $(draggable).data("creneau");
      if (enModif === true) {
        if (doitMettreAJour) {
          creneau.mettreAJour(elem);
        }
        $(elem).data("creneau", creneau);
        if ($(draggable).prop("tagName") === "TD") {
          window.supprimerCreneauCase(draggable, creneau);
        }
        if ($(draggable).prop("tagName") === "LI") {
          window.supprimerCreneauListe(draggable);
        }
        window.makeDraggable(elem);
      }
      creneauManager.ajouterTooltipCreneau(elem, creneau);
      $(elem).css("background-color", "#" + creneau.backgroundColor);
      return $(elem).text(texte);
    };
  })(this);

  window.coursPeutEtrePlaceSurDroppable = function(droppable, tailleElem, creneau, doitEmpecherSurClasseInvalide) {
    var classeInvalide, i, indexTd, indexTr, j, k, ligne, msg, nbTr, ref, ref1, td, tds, varchaineElem, warningInvalideLance;
    if (doitEmpecherSurClasseInvalide == null) {
      doitEmpecherSurClasseInvalide = false;
    }
    ligne = $(droppable).parent("tr");
    indexTd = $(droppable).index();
    indexTr = $(ligne).index();
    nbTr = $(ligne).siblings().length;
    warningInvalideLance = false;
    if (indexTr + tailleElem - 1 > nbTr) {
      return false;
    }
    classeInvalide = creneau.getChaineInvalide();
    varchaineElem = ":eq(" + indexTd + ")";
    for (i = j = 0, ref = tailleElem - 1; 0 <= ref ? j <= ref : j >= ref; i = 0 <= ref ? ++j : --j) {
      tds = $(ligne).children();
      td = tds.eq(indexTd);
      if (td.text().length > 0) {
        return false;
      }
      msg = td.data(classeInvalide);
      if (msg !== void 0) {
        if (doitEmpecherSurClasseInvalide === true) {
          return false;
        } else {
          if (warningInvalideLance === false) {
            if (msg === null) {
              msg = "Une contrainte a été définie pour ce créneau à cet emplacement";
            }
            toastr.warning(msg, "Attention");
            warningInvalideLance = true;
          }
        }
      }
      ligne = ligne.next();
    }
    ligne = $(elem).closest("tr");
    for (i = k = 0, ref1 = creneau.nbQuartDHeure - 1; 0 <= ref1 ? k <= ref1 : k >= ref1; i = 0 <= ref1 ? ++k : --k) {
      td = $(ligne).children().eq(indexTd);
      td.removeAttr("style");
      ligne = $(ligne).next();
    }
    return true;
  };

  window.supprimerCreneauListe = function(elem) {
    return $(elem).remove();
  };


  /*
      INIT
  
  $(document).ready( ->
    createur = new window.PlanningHtmlCreator(salles, 8, 22)
    classPlacer = new window.ClassPlacer()
  
    tableauJours = ["Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"]
    tableau = createur.creerTableauJour
    classPlacer.placerDropZones(tableau)
    
    for jour in tableauJours
      $("##{jour}").append(tableau)
    classPlacer.placerClassePlanning()
    $("#edt").tabs()
    $( document ).tooltip();
  
  )
   */

}).call(this);