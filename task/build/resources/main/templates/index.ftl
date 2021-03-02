<html xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <title>${title}</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<#list latestCode as code>
<span id="load_date" > ${code.getDate()}  </span>
<pre id="code_snippet" ><code>${code.getCode()}</code></pre>
<#if code.getTime() gt 0>
<span id="time_restriction" > ${code.getTime()} </span>
    <#else>
</#if>
<#if code.getViews() gt 0 || displayView == true>
<span id="views_restriction" > ${code.getViews()} </span>
    <#else>
</#if>
</#list>
</body>
</html>