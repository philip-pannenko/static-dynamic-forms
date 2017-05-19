(function() {
  "use strict";

  $(window).on('load', function() {

    var menuid = window.location.hash.substr(1).split('=')[1];
    $.ajax({
      url: (menuid ? "/resources/orders/for-menu/" + menuid : "/resources/orders"),
    }).done(function(html) {
      $.each(html, function(name, val) {
        $("#menus").append('<tr><td>' + val.id + '</td><td><span>' + JSON.stringify(val.data) + '</span><a href="forms/' + val.menu.name + '#id=' + val.id + '"> link </a></td></tr>');
      });
    });
  });
})();