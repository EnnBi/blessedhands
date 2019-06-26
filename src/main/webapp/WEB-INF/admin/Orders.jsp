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
							<div class="element-box">
								<h5 class="form-header">${status } Orders</h5>
								<div class="form-desc">Here is the list of <code><b>${status } Orders</b></code></div>
								<div class="table-responsive">
									<table class="table table-striped table-bordered">
										<thead>
											<tr>
												<th>ID</th>
												<th>Ordered By</th>
												<th>Date</th>
												<th class="text-center">Items</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${orders}" var="order">
											<tr>
												<td><a href="/orders/${order.orderId }">${order.orderId }</a></td>
												<td>${order.placedUser.name}</td>
												<td>${order.placedDate}</td>
												<td>
													<table>
													<tbody>
													<tr>
													<c:forEach items="${order.cartItems}" var="item">
													<c:choose>
													<c:when test="${item.medicine != null }">
														<td>${item.medicine.name }</td>
														<td>${item.quantity }</td>
													</c:when>
													<c:otherwise>
														<td>${item.textMedicine.name }</td>
														<td>${item.quantity }</td>
													</c:otherwise>
													</c:choose>
													
													</c:forEach>
													</tr>
													</tbody>
													</table>
												</td>
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
		<div class="display-type"></div>
	</div>

</body>

</html>
