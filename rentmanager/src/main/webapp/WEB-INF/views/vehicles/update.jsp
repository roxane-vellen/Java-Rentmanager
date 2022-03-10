<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<%@ include file="/WEB-INF/views/common/header.jsp"%>
		<!-- Left side column. contains the logo and sidebar -->
		<%@ include file="/WEB-INF/views/common/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Voitures</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<!-- Horizontal Form -->
						<div class="box">
							<!-- form start -->
							<form class="form-horizontal" method="post"	action="/rentmanager/cars/update">
								<div class="box-body">
									<div class="form-group">
										<label for="manufacturer" class="col-sm-2 control-label">Marque</label>

										<div class="col-sm-10">
											<input type="text" class="form-control" id="manufacturer"
												name="manufacturer" value="${constructor}" required>
										</div>
									</div>
									<div class="form-group">
										<label for="model" class="col-sm-2 control-label">Modele</label>

										<div class="col-sm-10">
											<input type="text" class="form-control" id="model"
												name="model" value="${modele}" required>
										</div>
									</div>
									<div class="form-group">
										<label for="seats" class="col-sm-2 control-label">Nombre de places</label>

										<div class="col-sm-10">
											<input type="number" class="form-control" id="seats"
												name="seats" value="${nbSeats}" min="2" max="9" required>
										</div>
									</div>
									
								</div>
								<!-- /.box-body -->
								<div class="box-footer">
									<button type="submit" class="btn btn-info pull-right">Effectuer modification(s)</button>
								</div>
								<!-- /.box-footer -->
							</form>
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col -->
				</div>
			</section>
			<!-- /.content -->
		</div>

		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="/WEB-INF/views/common/js_imports.jsp"%>
</body>
</html>