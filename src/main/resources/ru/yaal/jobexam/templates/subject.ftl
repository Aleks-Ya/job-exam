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

<form>

<#list questions as question>
    <h2>Вопрос ${question_index + 1}: </h2>

    <p>
    ${question.text}
    </p>
    <label>Варианты ответов:<br/>
        <#list question.answers as answer>
            <input type="radio" name="spring-1" value="1">${answer.text}<br/>
        </#list>
    </label>

    <p><input type="submit" value="Отправить"/></p>
</#list>

</form>

</body>
</html>