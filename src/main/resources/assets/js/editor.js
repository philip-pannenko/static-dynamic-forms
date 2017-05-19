(function() {
  "use strict";

  $(window).on('load', function() {
    
    var $tabs = $( "#tabs" ).tabs();
    
    var counter=0;
    $("#add").click(function () {
      
      counter += 1;
      
      var $component = $(`
        <div id="field-` + counter + `" class="module_holder" style="cursor: pointer;">
          <label style="cursor: inherit;" for="` + counter +`">hi-`+ counter +`</label>
          <input style="cursor: inherit;" id="` + counter +`" type="text" name="menu.` + counter + `">
          <button style="cursor: inherit;" class="delete" type="button">remove</button>
        </div>
      `);
      
      $component.find('.delete').click(function () {
        $(this).parent().remove();
      });
      
      $component.click(function() {
        $('#field-id').val(this.id);
        $('#label-name').val($component.find('label').text());
        $('#data-model').val($component.find('input').attr('name'));
        $tabs.tabs( "option", "active", 1 );
      });
      
      $("#editor .row").append($component);
      
    }); 
    
    $('#saveField').click(function() {
      var fieldId = $('#field-id').val();
      $('#' + fieldId).find('label').text($('#label-name').val());
      $('#' + fieldId).find('input').attr('name', $('#data-model').val() );
    });
    
    $('#editor').on('submit', function(e) {

      var payload = $('#editor').serializeArray().reduce(function(a, x) {

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

      var type = (Number(payload.id) === -1 ? "post" : "put");
      $.ajax({
        url: "/resources/menus/",
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