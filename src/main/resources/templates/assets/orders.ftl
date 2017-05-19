<#include "/common/base.ftl">
<#macro page_head>
  <title>Orders</title>
</#macro>

<#macro page_body>
<#include "/common/nav.ftl">
<div id="single-page" class="container">
  <div class="row">
    <h4>Generic Menu Generator</h4>
    <table id="menus"  class="u-full-width" style="table-layout: fixed;">
      <colgroup>
        <col style="width:20%">
        <col style="width:80%">
      </colgroup>  
      <thead>
        <tr>
          <th>Order Id</th>
          <th>Link</th>
      </thead>
    </table>
  </div>
</div>
<#include "/common/scripts.ftl">
<script src="/js/orders.js"></script>
</#macro>

<@display_page/>