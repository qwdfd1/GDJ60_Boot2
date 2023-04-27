<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            
            <!-- Pricing section-->
            <section class="bg-light py-5">
                <div class="container px-5 my-5">
                    <div class="text-center mb-5">
                        <h1 class="fw-bolder">${board} List</h1>
                        <p class="lead fw-normal text-muted mb-0">With our no hassle pricing plans</p>
                    </div>
                    
                    <div>
	                	<table class="table table-hover">
	                		<thead>
		                		<tr>
		                			<th>Num</th>
		                			<th>Tilte</th>
		                			<th>Writer</th>
		                			<th>Date</th>
		                			<th>Hit</th>
		                		</tr>
	                		</thead>
	                		<tbody>
	                			<c:forEach items="${list}" var="boardVO">
	                				<tr>
	                					<td>${boardVO.num}</td>
	                					<td><a href="./detail?num=${boardVO.num}">${boardVO.title}</a></td>
	                					<td>${boardVO.writer}</td>
	                					<td>${boardVO.regDate}</td>
	                					<td>${boardVO.hit}</td>
	                				</tr>
	                			</c:forEach>
	                		</tbody>
	                	</table>
	                </div>
	                
               		<div class="row">
						<nav aria-label="Page navigation example">
						  <ul class="pagination justify-content-center">
						   <li class="page-item ${pager.before ? 'disabled':''}">
						      <a class="page-link" aria-label="Previous" href="./list?page=1&kind=${pager.kind}&search=${pager.search}">
						      	<span aria-hidden="true">&laquo;</span>
						      </a>
						   	</li>
						    <li class="page-item ${pager.before ? 'disabled':''}">
			   			      <a class="page-link" aria-label="Previous" href="./list?page=${pager.startNum-1}&kind=${pager.kind}&search=${pager.search}">
						      	<span aria-hidden="true">&lsaquo;</span>
						      </a>
						   	</li>
						   	<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
						    <li class="page-item"><a class="page-link" href="./list?page=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a></li>
						    </c:forEach>
						    <li class="page-item ${pager.after ? 'disabled':''}">
			   			      <a class="page-link" aria-label="Previous" href="./list?page=${pager.lastNum+1}&kind=${pager.kind}&search=${pager.search}">
						      	<span aria-hidden="true">&rsaquo;</span>
						      </a>
						   	</li>
			 			    <li class="page-item ${pager.after ? 'disabled':''}">
			   			      <a class="page-link" aria-label="Previous" href="./list?page=${pager.totalPage}&kind=${pager.kind}&search=${pager.search}">
						      	<span aria-hidden="true">&raquo;</span>
						      </a>
						   	</li>
						  </ul>
						</nav>
					</div>
					
									<!-- 검색창  -->
					<div class="row">
						
						<form class="row" action="./list" method="get" id="searchForm">
							<input type="hidden" name="page" id="page" value="1">
							<div class="row justify-content-center my-2">
							 <div class="col-auto">
								 <label for="kind" class="visually-hidden">Kind</label>
								<select class="form-select" name="kind" id="kind" aria-label="Default select example">
								   <option value="title" ${pager.kind eq 'title' ? 'selected' : ''}>제목</option>
								  <option value="contents" ${pager.kind eq 'contents' ? 'selected' : ''}>글 내용</option> 
							  <c:if test="${boardName ne 'notice'}">
								  	<option value="writer" ${pager.kind eq 'writer' ? 'selected' : ''}>작성자</option>
								  </c:if> 
								</select>
							</div>
						
						  <div class="col-auto">
						    <label for="search" class="visually-hidden">Search</label>
						    <input type="text" class="form-control" value="${pager.search}" name="search" id="search" placeholder="검색어를 입력하세요">
						  </div>
						  <div class="col-auto">
						    <button type="submit" class="genric-btn primary-border mb-3">검색</button>
						  </div>
						  </div>
						</form>
					</div>
                
                </div>
                
               
            </section>
            
            <sec:authorize access="hasRole('ADMIN')">
            	<a href="./add">Write</a>
            </sec:authorize>
            
        </main>
        <!-- Footer 적용 -->
        <c:import url="../temp/footer.jsp"></c:import>
        <!-- Footer 끝 -->
</body>
</html>