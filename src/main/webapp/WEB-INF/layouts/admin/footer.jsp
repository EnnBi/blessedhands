
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/jquery/dist/jquery.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/moment/moment.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/chart.js/dist/Chart.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/select2/dist/js/select2.full.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/jquery-bar-rating/dist/jquery.barrating.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/ckeditor/ckeditor.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/bootstrap-validator/dist/validator.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/ion.rangeSlider/js/ion.rangeSlider.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/dropzone/dist/dropzone.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/editable-table/mindmup-editabletable.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/fullcalendar/dist/fullcalendar.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/perfect-scrollbar/js/perfect-scrollbar.jquery.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/tether/dist/js/tether.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/bootstrap/js/dist/util.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/bootstrap/js/dist/alert.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/bootstrap/js/dist/button.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/bootstrap/js/dist/carousel.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/bootstrap/js/dist/collapse.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/bootstrap/js/dist/dropdown.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/bootstrap/js/dist/modal.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/bootstrap/js/dist/tab.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/bootstrap/js/dist/tooltip.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/bower_components/bootstrap/js/dist/popover.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/js/main.js"></script>
	<script>
	$('.itemAvailable').click(function(){
		var ref = $(this);
		if(ref.val()=='true'){
			ref.parents('td').next().next().find('input').attr('required','required');
			var quantity =ref.parents('td').next().text();
			var price = ref.parents('td').next().next().text();
			var total = Number(quantity)*Number(price);
			ref.parents('td').next().next().next().find('input').val(total);
		}
		else{
			ref.parents('td').next().next().next().find('input').val('');
			ref.parents('td').next().next().find('input').removeAttr('required');
		}
		
		grandTotal();
	});
	
	$('.textMedicinePrice').change(function(){
		var ref = $(this);
		var quantity = ref.parents('td').prev().text();
		var total = Number(ref.val())*Number(quantity);
		ref.parents('td').next().find('input').val(total);
		grandTotal();
	})
	
	function grandTotal(){
		var sum=0;
		$("table tr td:last-child").each(function(i){
			if(!($(this).find('input').attr('name') == 'total')){
				sum = sum+Number($(this).find('input').val());
			}
			else
				$(this).find('input').val(sum);
		});
		
	}
	
	$('#confirmship').click(function(e){
		console.log($('#deliveredUser').val()+"----");
		if($('#deliveredUser').val() == ""){
			alert("Please select Delivering User")
			e.preventDefault();
			return false;
		}
			
	});

	</script>