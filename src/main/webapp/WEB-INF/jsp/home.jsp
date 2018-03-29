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
        <style>@import url(https://fonts.googleapis.com/css?family=Raleway:400,3..);

            html {
                background: #29ce9a;
                font-family: Raleway;
                display: table;
                width: 100%;
                height: 100%;
            }

            body {
                display: table-cell;
                text-align: center;
            }

            .demo {
                display: inline-block;
                padding: 50px;
                background-color: #fff;
                border-radius: 20px;
                color: #666;
                text-align: center;
            }

            .demo__content {
                text-align: left;
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

            button {
                background: none;
                border: 3px solid #fff;
                border-radius: 5px;
                color: #fff;
                display: block;
                font-size: 1.6em;
                font-weight: bold;
                margin: 1em auto;
                padding: 2em 6em;
                position: relative;
                text-transform: uppercase;
            }

            button::before,
            button::after {
                background: #fff;
                content: '';
                position: absolute;
                z-index: -1;
            }

            button:hover {
                color: #2ecc71;
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
        <title>Title</title>
    </head>

    <body>
        <h1>Step 2 - Select available products</h1>
        <br>
        <div class="demo">
            <div class="demo__title">Products</div>
            <div class="demo__content">
                <form name="input" action="http://localhost:8080/SpringMVCRecipeApp-1.0-SNAPSHOT/home/submit-search" method="get">
                    <input type="submit" value="Submit search"/>
                    <br><br>
                    <c:forEach items="${products}" var="product">
                        <label class="switcher">
                            <input type="checkbox" name="titles" value="${product.toString()}"/>
                            <div class="switcher__indicator"></div>
                            <span>${product.toString()}</span>
                        </label><br/><br/>
                    </c:forEach>

                </form>
            </div>

        </div>
    </body>
</html>
