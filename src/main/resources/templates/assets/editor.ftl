<#include "/common/base.ftl">
<#macro page_head>
  <title>Editor Page</title>
</#macro>

<#macro page_body>
<#include "/common/nav.ftl">
<div id="single-page" class="container">
  <div class="row">
    <button class="add"> Add </button>
    <form id="editor">
      <div class="row">
        <input type="hidden" name="id" value="-1">
        <label for="name">Menu Name</label>
        <input id="name" type="text" name="name">
      </div>
      <button id="add" type="button">Add</button>
      <input class="button-primary" type="submit" value="Submit">
    </form>
  </div>
</div>
<#include "/common/scripts.ftl">
<script src="/js/editor.js"></script>
</#macro>

<@display_page/>