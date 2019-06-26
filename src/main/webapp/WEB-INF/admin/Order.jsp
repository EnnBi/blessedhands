<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<div class="content-w">

				<div class="content-i">
					<div class="content-box">
						
						
						<div class="element-wrapper">
									<h6 class="element-header">Order Details</h6>
									<div class="element-box">
										<form:form action="update" method="post" commandName="order" >
											<form:hidden path="id"/>
											<h5 class="form-header">Order Details</h5>
											<div class="form-desc">Here are the details of the order</div>
											<div class="row">
												<div class="col-sm-4">
														<div class="form-group" ><label style="font-weight: bold;"> Order ID: </label><label style="float: right;">${order.orderId}</label></div>
												</div>
												<div class="col-sm-4">
													<div class="form-group"><label style="font-weight: bold;">Ordered Date: </label><label style="float: right;">${order.placedDate}</label></div>
												</div>
												<div class="col-sm-4">
													<div class="form-group"><label style="font-weight: bold;">Status: </label><label style="float: right;">${order.status}</label></div>
												</div>
											</div>
											<div class="table-responsive">
												<table class="table table-striped table-bordered">
													<thead>
														<tr>
															<th>Medicine Details</th>
															<th>Available</th>
															<th>Quantity</th>
															<th>Unit Price</th>
															<th class="text-center">Total Price</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${order.cartItems}" var="item" varStatus="i">
														<tr>
														<c:choose>
														<c:when test="${item.medicine != null}">
														<td><b style="font-weight: bold">${item.medicine.name}</b><br>
															${item.medicine.description} <br>
															${item.medicine.company.name}
														</td>
														</c:when>
														<c:otherwise>
														<td><b style="font-weight: bold">${item.textMedicine.name}</b><br>
															${item.textMedicine.dosage} <br>
															${item.textMedicine.company}
														</td>
														</c:otherwise>
														</c:choose>
														<td>
															<div class="form-check">
																<label class="form-check-label">
																		<form:radiobutton class="form-check-input itemAvailable" path="cartItems[${i.index}].available" value="true" />	Yes
																</label>
															</div>
															<div class="form-check">
																<label class="form-check-label">
																		<form:radiobutton class="form-check-input itemAvailable" path="cartItems[${i.index}].available" value="false" />	No
																</label>
															</div> 
															
														<td>${item.quantity}</td>
														
														<c:choose>
														<c:when test="${item.medicine != null}">
														<td>${item.medicine.price}</td>
														<td><input type="text" class="form-control" value="${item.medicine.price * item.quantity}" disabled="disabled"/></td>
														</c:when>
														<c:otherwise>
														<td><form:input type="text" path="cartItems[${i.index}].textMedicine.price" class="form-control textMedicinePrice" /></td>
														<td><input type="text" class="form-control" disabled="disabled"/></td>
														</c:otherwise>
														</c:choose>
														
														</tr>
														</c:forEach>
														<tr>
															<td></td>
															<td></td><td></td>
															<td><b style="font-weight: bold">Grand Total:</b></td>
															<td><form:input class="form-control" type="text" path="total" readonly="true"/></td>
														</tr>
													</tbody>
													</table>
												</div>
												<h5 class="form-header" style="border-bottom: 1px solid #dccccc !important;margin-bottom: 1.5rem;padding-bottom: 1rem;">Address</h5>
												<div class="row" style="margin-top:10px">
												<div class="col-sm-4">
														<div class="form-group"><label style="font-weight: bold"> Name: </label><label style="float: right;">${order.address.name }</label></div>
												</div>
												<div class="col-sm-4">
													<div class="form-group"><label style="font-weight: bold">Phone Number: </label><label style="float: right;">${order.address.contact}</label></div>
												</div>
												
												<div class="col-sm-4">
													<div class="form-group"><label style="font-weight: bold"> Complete Address: </label><label style="float: right;">${order.address.address}${order.address.landmark}</label></div>
												</div>
												</div>
												<c:choose>	
													<c:when test="${order.status eq 'Placed' }">
														<div class="form-group row"><label class="col-form-label col-sm-4" style="font-weight: bold"> Delivering User</label>
													
														<div class="col-sm-8"><form:select class="form-control" path="deliveredUser" id="deliveredUser">
																					<form:option value="">Select Delivering User</form:option>
																					<form:options items="${deliveringUsers}" itemLabel="name" itemValue="id"/>
																				</form:select></div>
														</div>
													<div class="form-buttons-w"><button class="btn btn-primary" type="submit" name="submit" value="confirm"> Confirm</button>
													<button class="btn btn-primary" style="float:right;" type="submit" name="submit" id="confirmship" value="confirmship"> Confirm & Ship</button></div>
													</c:when>
													<c:when test="${order.status eq 'Confirmed' }">
														<div class="form-group row">
															<label class="col-form-label col-sm-4" style="font-weight: bold"> Delivering User</label>
															<div class="col-sm-8">
																<form:select class="form-control" path="deliveredUser" required="true">
																	<form:option value="">Select Delivering User</form:option>
																	<form:options items="${deliveringUsers}" itemLabel="name" itemValue="id"/>
																</form:select>
															</div>
														</div>
														<div class="form-buttons-w">
															<button class="btn btn-primary" style="float:right;" type="submit" name="submit" value="ship">Ship</button>
														</div>
													</c:when>	
													<c:when test="${order.status eq 'Shipped' }">
														<div class="form-group row">
															<label class="col-form-label col-sm-4" style="font-weight: bold"> Delivering User</label>
															<div class="col-sm-8">
																<form:select class="form-control" path="deliveredUser" disabled="true">
																	<form:options items="${deliveringUsers}" itemLabel="name" itemValue="id"/>
																</form:select>
															</div>
														</div>
														<div class="form-buttons-w">
															<button class="btn btn-primary" style="float:right;" name="submit" value="cancel">Cancel</button>
														</div>
													</c:when>
													<c:otherwise>
													<div class="form-group row">
															<label class="col-form-label col-sm-4" style="font-weight: bold"> Delivering User</label>
															<div class="col-sm-8">
																<form:select class="form-control" path="deliveredUser" disabled="true">
																	<form:option value="">Select Delivering User</form:option>
																	<form:options items="${deliveringUsers}" itemLabel="name" itemValue="id"/>
																</form:select>
															</div>
														</div>
													</c:otherwise>
											</c:choose>
										</form:form>
									</div> 
								</div>
					</div>
				</div>
			</div>