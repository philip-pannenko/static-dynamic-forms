<#include "/common/base.ftl">
<#macro page_head>
  <title>Menus</title>
</#macro>

<#macro page_body>
<#include "/common/nav.ftl">

<div id="single-page" class="container">
  <div class="row">
    <h4>Generic Menu Generator</h4>
    <table id="menus" class="u-full-width" style="table-layout: fixed;">
      <colgroup>
        <col style="width:20%">
        <col style="width:20%">
        <col style="width:60%">
      </colgroup>  
      <thead>
        <tr>
          <th>Menu Id</th>
          <th>Link</th>
          <th>Orders</th>
      </thead>
    </table>
  </div>
</div>
<#include "/common/scripts.ftl">
<script src="/js/menus.js"></script>
</#macro>

<@display_page/>