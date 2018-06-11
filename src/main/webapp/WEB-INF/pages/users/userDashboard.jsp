	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
	
	<div id="page-wrapper">
		<div class="container-fluid">
			<div class="row bg-title">
				<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
					<h4 class="page-title">Dashboard</h4>
				</div>
				<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
					<ol class="breadcrumb">
						<li class="active">Dashboard</li>
					</ol>
				</div>
			</div>
	
			<!-- First Row -->
			<div class="row">
				<div class="col-md-12">
					<div class="white-box">
						<div class="row row-in">
							<div class="col-lg-3 col-sm-6 row-in-br">
								<ul class="col-in">
									<li><span class="circle circle-md bg-danger"><i
											class="ti-clipboard"></i></span></li>
									<li class="col-last">
										<h3 class="counter text-right m-t-15">23</h3>
									</li>
									<li class="col-middle">
										<h4>Training Invitation</h4>
										<div class="progress">
											<div class="progress-bar progress-bar-danger" style="width: 50%">
											</div>
										</div>
									</li>
								</ul>
							</div>
							<div class="col-lg-3 col-sm-6 row-in-br  b-r-none">
								<ul class="col-in">
									<li><span class="circle circle-md bg-info"><i
											class="ti-wallet"></i></span></li>
									<li class="col-last">
										<h3 class="counter text-right m-t-15">76</h3>
									</li>
									<li class="col-middle">
										<h4>Incoming Trainings</h4>
										<div class="progress">
											<div class="progress-bar progress-bar-info" style="width: 50%">
											</div>
										</div>
									</li>
								</ul>
							</div>
							<div class="col-lg-3 col-sm-6 row-in-br">
								<ul class="col-in">
									<li><span class="circle circle-md bg-success"><i
											class=" ti-shopping-cart"></i></span></li>
									<li class="col-last">
										<h3 class="counter text-right m-t-15">93</h3>
									</li>
									<li class="col-middle">
										<h4>Trainings Attended</h4>
										<div class="progress">
											<div class="progress-bar progress-bar-success" style="width: 50%">
											</div>
										</div>
									</li>
								</ul>
							</div>
							<div class="col-lg-3 col-sm-6  b-0">
								<ul class="col-in">
									<li><span class="circle circle-md bg-warning"><i
											class="fa fa-dollar"></i></span></li>
									<li class="col-last">
										<h3 class="counter text-right m-t-15">83</h3>
									</li>
									<li class="col-middle">
										<h4>Total Trainings</h4>
										<div class="progress">
											<div class="progress-bar progress-bar-warning" style="width: 50%">
											</div>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /First Row -->
			
			<!-- Second Row -->
			<div class="row">
				<div class="col-sm-12">
					<div id="calendar"></div>
				</div>
			</div>
			<!-- /Second Row -->
			
		</div>
	</div>

