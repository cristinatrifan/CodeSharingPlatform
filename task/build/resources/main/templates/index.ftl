<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<#list latestCode as code> <span id="load_date" > ${code.getDate()}  </span>
<pre id="code_snippet" ><code>${code.getCode()}</code></pre> </#list>
</body>
</html>