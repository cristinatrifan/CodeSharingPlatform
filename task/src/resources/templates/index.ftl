<html>
<head>
    <title>${title}</title>
</head>
<body>
<#list latestCode as key, code> <span id="load_date" > ${code.getDate()}  </span>
<pre id="code_snippet" >${code.getCode()}</pre> </#list>
</body>
</html>