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
						<!-- ------------------
START - Chat Popup Box
-------------------
						<div class="floated-chat-btn"><i class="os-icon os-icon-mail-07"></i><span>Demo Chat</span></div>
						<div class="floated-chat-w">
							<div class="floated-chat-i">
								<div class="chat-close"><i class="os-icon os-icon-close"></i></div>
								<div class="chat-head">
									<div class="user-w with-status status-green">
										<div class="user-avatar-w">
											<div class="user-avatar"><img alt="" src="img/avatar1.jpg"></div>
										</div>
										<div class="user-name">
											<h6 class="user-title">John Mayers</h6>
											<div class="user-role">Account Manager</div>
										</div>
									</div>
								</div>
								<div class="chat-messages">
									<div class="message">
										<div class="message-content">Hi, how can I help you?</div>
									</div>
									<div class="date-break">Mon 10:20am</div>
									<div class="message">
										<div class="message-content">Hi, my name is Mike, I will be happy to assist you</div>
									</div>
									<div class="message self">
										<div class="message-content">Hi, I tried ordering this product and it keeps showing me error code.</div>
									</div>
								</div>
								<div class="chat-controls"><input class="message-input" placeholder="Type your message here..." type="text">
									<div class="chat-extra"><a href="#"><span class="extra-tooltip">Attach Document</span><i class="os-icon os-icon-documents-07"></i></a><a href="#"><span class="extra-tooltip">Insert Photo</span><i class="os-icon os-icon-others-29"></i></a><a href="#"><span class="extra-tooltip">Upload Video</span><i class="os-icon os-icon-ui-51"></i></a></div>
								</div>
							</div>
						</div>
						------------------
END - Chat Popup Box
------------------ -->
					</div>
				
		</div>
		<div class="display-type"></div>
	</div>

</body>

</html>
