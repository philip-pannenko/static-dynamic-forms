<#include "/common/base.ftl">
<#macro page_head>
  <title>Menu</title>
</#macro>

<#macro page_body>
<#include "/common/nav.ftl">

<div id="single-page" class="container">
  <div class="row">
    <form id="form">
      <div>
        <input type="hidden" name="menu.id" value="${menuId}">
        <input type="hidden" name="id" value="-1">
        <#list components as component>
          <@testload comp=component />
        </#list>
      </div>
      <input class="button-primary" type="submit" value="Submit">
    </form>
    </div>
</div>

<#include "/common/scripts.ftl">
<script src="/js/forms.js"></script>
</#macro>

<#macro testload comp>
  <#include comp.filename>
</#macro>

<@display_page/>


