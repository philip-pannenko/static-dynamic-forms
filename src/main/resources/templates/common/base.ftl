<#macro page_head>
  <title>Generic Menu Generator</title>
</#macro>

<#macro page_body>
  <h1>Generic Menu Generator</h1>
</#macro>

<#macro display_page>
<@compress single_line=true>
<!doctype html>
<html>
  <head> 
    <@page_head/>
    <link rel="stylesheet" href="/css/normalize.css">
    <link rel="stylesheet" href="/css/skeleton.css">
    <link rel="stylesheet" href="/css/app.css">
  </head>
  <body>
    <@page_body/>
  </body>
</html>
</@compress>
</#macro>