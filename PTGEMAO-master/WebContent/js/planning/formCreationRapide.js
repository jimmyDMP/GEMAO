// Generated by CoffeeScript 1.10.0
(function() {
  $(document).ready(function() {
    $("#formCreerButton").click(function() {
      var c, cHTML, couleur, duree, heureDur, libelle, man, message, minuteDur, nbQuart, tabDuree;
      libelle = $("#libelle").val();
      heureDur = $("#heureDuree").val();
      minuteDur = $("#minuteDuree").val();
      duree = $("#heureDuree").val() + " : " + $("#minuteDuree").val();
      if (libelle !== "" && heureDur !== "" && minuteDur !== "") {
        couleur = $("#couleur").val().slice(1, 7);
        couleur.replace('#', '');
        tabDuree = window.parseHeure(duree);
        nbQuart = tabDuree[0] * 4 + tabDuree[1] / 15;
        c = new window.Creneau(null, libelle, nbQuart, null, null, null, couleur, null, false, true);
        man = new window.CreneauManager();
        cHTML = man.placerCreneau(c, true);
        return creneauxJS.push(c);
      } else {
        if (libelle === "") {
          message = "Le champ du libellé ";
        } else if (heureDur === "") {
          message = "Le champ de la durée ";
        } else if (minuteDur === "") {
          message = "Le champ minute ";
        }
        return toastr.error(message + "doit être renseigné", "Erreur de créneau");
      }
    });
    return $("#viderChampsCreneau").click(function() {
      console.log("coucou");
      $("#libelle").val("");
      $("#heureDuree").val("");
      return $("#minuteDuree").val("");
    });
  });

}).call(this);
