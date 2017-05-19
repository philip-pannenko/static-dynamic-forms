<#include "/common/base.ftl">
<#macro page_head>
  <title>Error Page</title>
</#macro>

<#macro page_body>
<#include "/common/nav.ftl">

<div id="single-page" class="container">
  <div class="row">
    <h4>Whoops something went wrong.</h4>
    <p></p>
  </div>
</div>
</#macro>

<@display_page/>