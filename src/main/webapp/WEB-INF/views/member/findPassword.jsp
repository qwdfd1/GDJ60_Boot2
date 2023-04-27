<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Modern Business - Start Bootstrap Template</title>
        <!-- css Favicon-->
        <c:import url="../temp/style.jsp"></c:import>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
    <body class="d-flex flex-column h-100">
        <main class="flex-shrink-0">
            <!-- Navigation 적용 -->
				<c:import url="../temp/header.jsp"></c:import>
            <!-- Header-->
 					           <!-- Page content-->
            <section class="py-5">
                <div class="container px-5">
                    <!-- Contact form-->
                    <div class="bg-light rounded-3 py-5 px-4 px-md-5 mb-5">
                        <div class="text-center mb-5">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-envelope"></i></div>
                            <h1 class="fw-bolder">Get in touch</h1>
                            <p class="lead fw-normal text-muted mb-0">We'd love to hear from you</p>
                        </div>
                        <div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-6">
                                <!-- * * * * * * * * * * * * * * *-->
                                <!-- * * SB Forms Contact Form * *-->
                                <!-- * * * * * * * * * * * * * * *-->
                                <!-- This form is pre-integrated with SB Forms.-->
                                <!-- To make this form functional, sign up at-->
                                <!-- https://startbootstrap.com/solution/contact-forms-->
                                <!-- to get an API token!-->
                                
                                <form:form id="contactForm" action="./findPassword" method="post" modelAttribute="memberVO" data-sb-form-api-token="API_TOKEN">
                                
                                      <!-- USERNAME input-->
                                    <div class="form-floating mb-3">
                                        <form:input path="username" id="username" cssClass="form-control"/>
                                        <form:label path="username">userName</form:label>
                                        <form:errors path="username" cssStyle="color : red;"></form:errors>
                                    </div>
                                    <!-- Email input-->
                                    <div class="form-floating mb-3">
                                        <form:input path="email" id="email" cssClass="form-control"/>
                                        <form:label path="email">Email</form:label>
                                        <form:errors path="email" cssStyle="color : red;"></form:errors>
                                    </div>
                                    

          
                                    <!-- Submit success message-->
                                    <!---->
                                    <!-- This is what your users will see when the form-->
                                    <!-- has successfully submitted-->
                                    <div class="d-none" id="submitSuccessMessage">
                                        <div class="text-center mb-3">
                                            <div class="fw-bolder">Form submission successful!</div>
                                            To activate this form, sign up at
                                            <br />
                                            <a href="https://startbootstrap.com/solution/contact-forms">https://startbootstrap.com/solution/contact-forms</a>
                                        </div>
                                    </div>
                                    <!-- Submit error message-->
                                    <!---->
                                    <!-- This is what your users will see when there is-->
                                    <!-- an error submitting the form-->
                                    <div class="d-none" id="submitErrorMessage"><div class="text-center text-danger mb-3">Error sending message!</div></div>
                                    <!-- Submit Button-->
                                    <div class="d-grid"><button class="btn btn-primary btn-lg" id="submitButton" type="submit">Submit</button></div>

                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <!-- Footer 적용 -->
        <c:import url="../temp/footer.jsp"></c:import>
        <!-- Footer 끝 -->
</body>
</html>