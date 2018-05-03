<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
						<div class="element-wrapper">
							<h6 class="element-header">List of Medicines submitted</h6>
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
												<th class="text-center">Company</th>
												<th class="text-center">Type</th>
												<th class="text-center">Price Per Unit</th>
												<th class="text-center">Unit</th>
												<th class="text-center">Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${medicineList}" var="medicine">
											<tr>
												<td class="text-center">${medicine.name}</td>
												<td class="text-center">${medicine.company.name}</td>
												<td class="text-center">${medicine.type.name}</td>
												<td class="text-center">${medicine.price}</td>
												<td class="text-center">${medicine.unit.name}</td>
												<td class="text-center"><a href="/medicine/edit/${medicine.id}"><i class="os-icon os-icon-edit-1"></i></a>&nbsp;&nbsp;<a href="/medicine/delete/${medicine.id}"><i class="os-icon os-icon-ui-15 "></i></a></td>
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
</body>
