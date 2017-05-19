(function() {
  "use strict";

  $(window).on('load', function() {

    $.ajax({
      url: "/resources/menus",
    }).done(function(html) {

      $.each(html, function(name, val) {
        $("#menus").append('<tr><td>' + val.id + '</td><td><a href="forms/' + val.name + '">' + val.name + '</a></td><td><a href="orders.html#id=' + val.id + '">Orders</a></td></tr>');
      });

    });
  });

})();