<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ggladko97
  Date: 28.01.18
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <link rel="shortcut icon" href="">
    <style>@import url(https://fonts.googleapis.com/css?family=Raleway:400,3..);
    @import url(https://fonts.googleapis.com/css?family=Open+Sans:700,300);
    @import url(https://fonts.googleapis.com/css?family=Roboto:400italic);

    html {
        background: #29ce9a;
        font-family: Raleway;
        display: table;
        width: 100%;
        height: 100%;
    }

    body {
        display: table-cell;
        vertical-align: middle;
        text-align: center;
    }

    .demo {
        display: inline-block;
        padding: 15px;
        background-color: #fff;
        border-radius: 20px;
        color: #666;
        width: 50%;
        float: center;
        text-align: center;
    }

    .demo__content {
        text-align: center;
        border: 1px solid #a80108;
        display: inline-block;
    }

    .demo__title {
        font-size: 50px;
        font-weight: bold;
        text-transform: uppercase;
        padding-bottom: 30px;
    }

    .switcher {
        position: relative;
        display: inline-block;
        cursor: pointer;
        padding-left: 100px;
        height: 40px;
        line-height: 40px;
        margin: 5px;
        font-size: 30px;
        user-select: none;
    }

    .switcher input {
        display: none;
    }

    .switcher__indicator::after {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        height: 40px;
        width: 40px;
        background-color: #d5d5d5;
        border-radius: 50%;
        transition: all .3s ease;
        animation-name: pulsein;
        animation-duration: .3s;
    }

    .switcher__indicator::before {
        content: '';
        position: absolute;
        top: 16px;
        left: 0;
        width: 80px;
        height: 8px;
        background-color: #d5d5d5;
        border-radius: 10px;
        transition: all .3s ease;
    }

    input:checked + .switcher__indicator::after {
        background-color: #29ce9a;
        transform: translateX(40px);
        animation-name: pulseout;
        animation-duration: .3s;
    }

    input:checked + .switcher__indicator::before {
        background-color: #29ce9a;
    }

    input:disabled + .switcher__indicator::after, input:disabled + .switcher__indicator::before {
        background-color: #e5e5e5;
    }

    @keyframes pulsein {
        0%, 100% {
            top: 0px;
            height: 40px;
            width: 40px;
        }
        50% {
            top: 6px;
            height: 28px;
            width: 52px;
        }
    }

    @keyframes pulseout {
        0%, 100% {
            top: 0px;
            height: 40px;
            width: 40px;
        }
        50% {
            top: 6px;
            height: 28px;
            width: 52px;
        }
    }


    .details {
        margin-left: 50px;
    }

    header p {
        margin-bottom: 0;
    }

    section {
        display: flex;
        justify-content: center;
        flex-direction: column;
        margin-bottom: 2em;
        padding: 0;
    }

    section:last-of-type {
        margin-bottom: 0;
    }

    section article {
        align-self: center;
        width: 20em;
        margin-bottom: 2em;
    }

    section article p, section article:last-of-type {
        margin-bottom: 0;
    }

    p {
        line-height: 1.5em;
        max-width: 20em;
        margin: 1.5em auto 2em;
        padding-bottom: 1.5em;
    }

    p span {
        display: block;
    }

    /**/
    .wrap {
        margin-left: 20px;
        margin: 40px auto;
        text-align: center;
    }
    img {
        width: 100px;
        height: 100px;
    }

    .green {
        text-align: center;
        border-radius: 5px;
        padding: 15px 25px;
        font-size: 22px;
        text-decoration: none;
        margin: 20px;
        color: #fff;
        position: relative;
        display: inline-block;
        background-color: #2ecc71;
        box-shadow: 0px 5px 0px 0px #15B358;
    }

    .green:hover {
        background-color: #48E68B;
    }
    .green:active {
        transform: translate(0px, 5px);
        -webkit-transform: translate(0px, 5px);
        box-shadow: 0px 1px 0px 0px;
    }
    h1 {
        color: #eee;
        font-size: 30px;
        text-align: center;
        margin: 20px 0 0 0;
        -webkit-font-smoothing: antialiased;
        text-shadow: 0px 1px #000;
    }
    </style>
    <title>Results</title>
</head>

<body>
<h1>Step 3 - Enjoy recipes from the best fit diets</h1>
<br>
<div class="demo">
    <div class="demo__title">Top diets:</div>
    <div class="demo__content">
        <c:forEach items="${results}" var="result">
            <h3>Diet plan:</h3>
            <div>${result.name}</div>
            <br>
            <h3>Matches</h3>
            <div>${result.procentProbability}%</div>
            <input type="button" value="Look for recipes"/>
        </c:forEach>
        <%--<form name="input" action="http://localhost:8080/home/submit-init-diets" method="get">--%>
            <%--<c:forEach items="${diets}" var="diet">--%>
                <%--<label class="switcher">--%>
                    <%--<input class="diet-info" type="checkbox" name="diets"--%>
                           <%--value="${diet.title}"/>--%>
                    <%--<div class="switcher__indicator"></div>--%>
                    <%--<span>${diet.title}</span>--%>
                <%--</label><br/><br/>--%>
            <%--</c:forEach>--%>
        <%--</form>--%>
    </div>
</div>

</div>
</body>
</html>
