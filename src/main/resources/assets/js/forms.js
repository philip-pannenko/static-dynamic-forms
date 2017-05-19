(function() {
  "use strict";

  $(window).on('load', function() {

    var menuid = window.location.hash.substr(1).split('=')[1];
    if (menuid) {
      $.ajax({
        url: '/resources/orders/' + menuid,
      }).done(function(html) {

        var $id = $('[name="id"]').val(html.id);

        $.each(html.data, function(name, val) {
          var $el = $('[name="data.' + name + '"]'), type = $el.attr('type');

          switch (type) {
          case 'checkbox':
            $el.attr('checked', 'checked');
            break;
          case 'radio':
            $el.filter('[value="' + val + '"]').attr('checked', 'checked');
            break;
          default:
            $el.val(val);
          }
        });

        console.log(html);
      });
    }

    $('#form').on('submit', function(e) {

      var payload = $('#form').serializeArray().reduce(function(a, x) {

        var v = x.name.split('.');
        if (v.length === 1) {
          a[x.name] = x.value;
        } else {
          if (a[v[0]] === undefined) {
            a[v[0]] = {};
          }

          a[v[0]][v[1]] = x.value;
        }

        return a;
      }, {});

      var type = payload.id === -1 ? "post" : "put";

      $.ajax({
        url: "/resources/orders/",
        type: type,
        data: JSON.stringify(payload),
        contentType: "application/json",
        success: function(data) {
          alert("success");
        }
      });

      e.preventDefault();
      return false;
    });
  });
})();