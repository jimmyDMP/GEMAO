// Generated by CoffeeScript 1.10.0
(function() {
  window.makeDroppableOnglets = function(elem) {
    return $(elem).hover(function(event) {
      if (window.enTrainDeDrag === true) {
        console.log("JE DIS OUI");
        console.log("#" + (elem.children("a").eq(0).attr("href")));
        return $("" + (elem.children().eq(0).attr("href"))).show();
      }
    });
  };

  $(document).ready(function() {
    $("#addCreneau").click(function() {
      var crPlaI, form;
      form = $("#ajoutCreneau");
      crPlaI = $("#creneauxPlacement i");
      if (form.css("display") === "none") {
        form.slideDown();
        crPlaI.removeClass('fa-plus-square');
        return crPlaI.addClass('fa-minus-square');
      } else {
        form.slideUp();
        crPlaI.addClass('fa-plus-square');
        return crPlaI.removeClass('fa-minus-square');
      }
    });
    return $("#jours li").mouseenter(function() {
      var index;
      if (window.enTrainDeDrag === true) {
        console.log(this);
        index = $("#jours li").index(this);
        console.log(index);
        $("#edt").tabs({
          active: index
        });
        return $("#edt").tabs("refresh");
      }
    });
  });

}).call(this);
