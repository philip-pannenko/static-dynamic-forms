<#include "/common/base.ftl">
<#macro page_head>
  <title>Generic Menu</title>
</#macro>

<#macro page_body>
<div id="banner">
  <h1>Generic Menu Generator</h1>
  <p>Stop being a code monkey and do something interesting!</p>
</div>
<div id="landing-page" class="container">
  <div class="row">
    <h4>Dynamic Forms, Delivered Statically</h4>
    <p>What started as a hypothetical question, morphed into a theoretical discussion and ended up as a successful experiment.</p>
    <p>Behold an attempt to fuse server side generated code, sprinkled with client side enhancements to yield a statically fast, yet dynamicly built, form templating engine.</p>
    <p>Use the links below to learn about the solution and navigate through the live examples.</p>
  </div>
  <div class="row">
    <div class="one-third column nav">
      <h5><a href="/editor.html">Editor</a></h5>
      <p>Create and modify menus.</p>
    </div>
    <div class="one-third column nav">
      <h5><a href="/menus.html">Menus</a></h5>
      <p>Find existing menus.</p>
    </div>
    <div class="one-third column nav">
      <h5><a href="/orders.html">Orders</a></h5>
      <p>View and modify orders.</p>
    </div>
  </div>
</div>
</#macro>

<@display_page/>