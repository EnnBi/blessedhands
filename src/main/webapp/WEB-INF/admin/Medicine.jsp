<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<body>
			<div class="content-w">
				<div class="content-i">
					<div class="content-box">
						<div class="row">
							<div class="col-lg-12">
								<div class="element-wrapper">
									<h6 class="element-header">Medicine</h6>
									<div class="element-box">
										<c:url var="action" value="/medicine"/>
										<form:form action="${action}" method="post" modelAttribute="medicine" enctype="multipart/form-data">
											<h5 class="form-header">Medicine</h5>
											<div class="form-desc">Add Medicines here</div>
											<c:if test="${not empty success}">
											 <div class="alert alert-success">${success}</div>
											</c:if>
											<c:if test="${not empty fail}">
											 <div class="alert alert-danger">${fail}</div>
											</c:if>
											<div class="form-group">
												<label for="">Company</label>
												<form:select class="form-control" path="company">
													<form:option value="">Select Company</form:option>
													<form:options items="${companyList}" itemLabel="name" itemValue="id"/>
												</form:select>
											</div>
											<div class="form-group">
												<label for="">Name</label>
												<form:input class="form-control" path="name" placeholder="Enter product name" type="text"/>
											</div>
											<div class="form-group">
												<label>Description</label>
												<form:textarea path="description" class="form-control" rows="3"></form:textarea>
											</div>
											<div class="form-group">
												<label for="">Type</label>
												<form:select path="type" class="form-control">
													<form:option value="">Select Type</form:option>
													<form:options items="${typeList}" itemLabel="name" itemValue="id"/>
												</form:select>
											</div>											
											<div class="form-group">
												<label for="">Unit</label>
													<form:select path="unit" class="form-control">
													  <form:option value="">Select Unit</form:option>
													  <form:options items="${unitList}" itemLabel="name" itemValue="id"/>
													</form:select>
											</div>
											<div class="form-group">
												<label for="">Price Per Unit</label>
												<form:input class="form-control" path="price" placeholder="Enter Price Per Unit" type="text"/>
											</div>
											<div class="form-group">
												<label for="">Image</label>
												<input type="file" class="form-control" name="files" />
												<input type="file" class="form-control" name="files" />
												<input type="file" class="form-control" name="files" />										
											</div>
											<div class="form-buttons-w"><button class="btn btn-primary" type="submit">Submit</button></div>
										</form:form>
									</div>
								</div>
							</div>
						</div>

					</div>
				
		</div>
		<div class="display-type"></div>
	</div>

</body>

</html>
