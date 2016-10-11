// Generated by CoffeeScript 1.10.0
(function() {
  window.asJSON = function(creneaux) {
    return JSON.stringify(creneaux);
  };

  window.envoyer = function(string) {
    console.log("envoi " + string);
    return $.ajax({
      url: window.location.href,
      type: 'post',
      data: 'creneaux=' + string,
      dataType: 'json',
      success: function(data) {
        if (data.state === "succes") {
          return toastr.success("Les créneaux ont été modifiés", "Succès");
        }
      },
      error: function(data) {
        if (data.state === "echec") {
          return toastr.error("Echec de la modification", "Echec");
        }
      }
    });
  };

  window.envoyerCreneaux;

  $(document).ready(function() {
    return $("#boutonValider").click(function() {
      var c, i, json, len, results;
      json = window.asJSON(window.creneauxJS);
      window.envoyer(json);
      results = [];
      for (i = 0, len = creneauxJS.length; i < len; i++) {
        c = creneauxJS[i];
        c.cree = false;
        results.push(c.modifie = false);
      }
      return results;
    });
  });

}).call(this);