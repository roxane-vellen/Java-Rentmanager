<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-md-3">

                    <!-- Profile Image -->
                    <div class="box box-primary">
                        <div class="box-body box-profile">
                            <h3 class="profile-username text-center">Reservation <br>ID : ${id} </h3>
                            <ul class="list-group list-group-unbordered">
                                <li class="list-group-item">
                                    <b>Debut</b> <a class="pull-right">${debut}</a>
                                </li>
                                <li class="list-group-item">
                                    <b>Fin</b> <a class="pull-right">${fin}</a>
                                </li>
                            </ul>

                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
                <div class="col-md-9">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#rents" data-toggle="tab">Reservation details</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="active tab-pane" id="rents">
                                <div class="box-body no-padding">
                                	<h3>Client</h3>
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 30px">#</th>
                                            <th>Nom</th>
                                            <th>Prenom</th>                                            
                                            <th>Naissance</th>
                                            <th>Email</th>
                                        </tr>
                                        
	                                    <tr>
		                                    <td>${idClient}</td>
		                                    <td>${nom}</td>
		                                    <td>${prenom}</td>
		                                    <td>${naissance}</td> 			                                 
		                                    <td>${email}</td>                        
	                                	</tr>
		                                
                                    </table>
                                    
                                    <h3>Vehicule</h3>
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 30px">#</th>
                                            <th>Constructeur</th>
                                            <th>Modele</th>
                                            <th>Nombre de places</th>
                                        </tr>
                                        
	                                    <tr>
		                                    <td>${idVehicle}</td>
		                                    <td>${constructor}</td>
		                                    <td>${model}</td>			                                 
		                                    <td>${seats}</td>                         
	                                	</tr>
		                                
                                    </table>
                                </div>
                            </div>
                        </div>
                        <!-- /.tab-content -->
                        <div class="box-footer">
							<a class="btn btn-info pull-right" href="${pageContext.request.contextPath}/rents">Retour</a> 						
						</div>
                    </div>
                    <!-- /.nav-tabs-custom -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->

        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
