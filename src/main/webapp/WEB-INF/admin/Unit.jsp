<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<body>
			<div class="content-w">
				<c:choose>
					<c:when test="${add}">
						<c:set var="action" value="/unit"/>
						<c:set var="button" value="Submit"/>
					</c:when>
					<c:otherwise>
						<c:set var="action" value="/unit/edit/${unit.id}"/>
						<c:set var="button" value="Update"/>
					</c:otherwise>
				</c:choose>
				<div class="content-i">
					<div class="content-box">
						<div class="row">
							<div class="col-lg-12">
								<div class="element-wrapper">
									<h6 class="element-header">Unit</h6>
									<c:if test="${not empty success}">
											 <div class="alert alert-success">${success}</div>
											</c:if>
											<c:if test="${not empty fail}">
											 <div class="alert alert-danger">${fail}</div>
											</c:if>
									<div class="element-box">
										<form:form action="${action}" method="post" modelAttribute="unit">
											<h5 class="form-header">Unit</h5>
											<div class="form-desc">Add and Edit Unit here</div>
											<div class="form-group"><label for="">Name</label><form:input class="form-control" path="name" placeholder="Enter Unit Name" type="text"/></div>
											<div class="form-group">
												<label>Description</label>
												<form:textarea path="description" class="form-control" rows="3"></form:textarea>
											</div>
											<div class="form-buttons-w"><button class="btn btn-primary" type="submit"> ${button}</button></div>
										</form:form>
									</div>
								</div>
							</div>
						</div>
			<div class="content-w">
				<div class="content-i">
					<div class="content-box">
						<div class="element-wrapper">
							<h6 class="element-header">List of all Units</h6>
							<div class="element-box">
								<%-- <div class="controls-above-table">
									<div class="row">
										<div class="col-sm-6"><a class="btn btn-sm btn-primary" href="#">Download CSV</a><a class="btn btn-sm btn-primary" href="#">Archive</a><a class="btn btn-sm btn-danger" href="#">Delete</a></div>
										<div class="col-sm-6">
											<form class="form-inline justify-content-sm-end"><input class="form-control form-control-sm rounded bright" placeholder="Search" type="text"><select class="form-control form-control-sm rounded bright"><option selected="selected" value="">Select Status</option><option value="Pending">Pending</option><option value="Active">Active</option><option value="Cancelled">Cancelled</option></select></form>
										</div>
									</div>
								</div> --%>
								<div class="table-responsive">
									<table class="table table-lightborder table-striped">
										<thead>
											<tr>
												<th class="text-center">Name</th>
												<th class="text-center">Description</th>
												<th class="text-center">Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${unitList}" var="unit">
											<tr>
												<td class="text-center">${unit.name}</td>
												<td class="text-center">${unit.description}</td>
												<td class="text-center"><a href="/unit/edit/${unit.id}"><i class="os-icon os-icon-edit-1"></i></a>&nbsp;&nbsp;<a href="/unit/delete/${unit.id}"><i class="os-icon os-icon-ui-15 "></i></a></td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					</div>	
				</div>
			</div>
				
		</div>
		<div class="display-type"></div>
	</div>

</body>

