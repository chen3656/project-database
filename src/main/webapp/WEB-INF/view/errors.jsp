<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Access Denied</title>
<meta name="viewport" content="width=device-width">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/403.css">
</head>
<body>
	<div class="error">
        <div class="error-code m-b-10 m-t-20">${errorCode}<i class="fa fa-warning"></i></div>
        <h3 class="font-bold">${errorMsg}</h3>

        <div class="error-desc">
            ${errorMsg}
            <div>
                <a class=" login-detail-panel-button btn" href="${pageContext.request.contextPath}/home">
                        <i class="fa fa-arrow-left"></i>
                        Go back to Homepage                        
                    </a>
            </div>
        </div>
    </div>
</body>
</html>