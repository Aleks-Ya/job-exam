<#-- @ftlvariable name="path" type="java.lang.String" -->
<#-- @ftlvariable name="questions" type="java.util.List<ru.yaal.jobexam.Question>" -->
<#-- @ftlvariable name="name" type="java.lang.String" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Опрос по Spring</title>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.8.0/styles/default.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.8.0/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>

<h1>${name}</h1>

<p>
    Всего вопросов: ${questions?size}
</p>

<#--noinspection HtmlUnknownTarget-->
<form id="result" method="post" action="/result">

<#list questions as question>
    <#assign index = question_index + 1>
    <h2>Вопрос ${index}: </h2>

    <p>
    ${question.text}
    </p>

    <p>Варианты ответов:</p>
    <#if (question.rightAnswersCount() > 1)>
        <#assign input_type = "checkbox">
    <#else>
        <#assign input_type = "radio">
    </#if>
    <#list question.answers as answer>
        <label>
            <input type="${input_type}" name="${path}:${index}" value="${answer_index + 1}">${answer.text}<br/>
        </label>
    </#list>

</#list>
    <p><input type="submit" value="Отправить"/></p>

</form>

</body>
</html>