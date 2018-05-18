	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
	
	<div id="page-wrapper">
		<div class="container-fluid">
			<div class="row bg-title">
				<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
					<h4 class="page-title">Dashboard</h4> 
				</div>
			</div>
			
			
			<!-- FIRST ROW -->
			<div class="row">
                    <div class="col-lg-3 col-sm-6 col-xs-12">
                        <div class="white-box">
                            <h3 class="box-title">Users</h3>
                            <ul class="list-inline two-part">
                                <li><i class="icon-people text-info"></i></li>
                                <li class="text-right"><span class="counter">${userCount}</span></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6 col-xs-12">
                        <div class="white-box">
                            <h3 class="box-title">Approval Request</h3>
                            <ul class="list-inline two-part">
                                <li><i class="icon-user-follow text-purple"></i></li>
                                <li class="text-right"><span class="counter">${pendingUserCount}</span></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6 col-xs-12">
                        <div class="white-box">
                            <h3 class="box-title">Trainings</h3>
                            <ul class="list-inline two-part">
                                <li><i class="ti-calendar text-danger"></i></li>
                                <li class="text-right"><span class="counter">${trainingCount}</span></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6 col-xs-12">
                        <div class="white-box">
                            <h3 class="box-title">Training Request</h3>
                            <ul class="list-inline two-part">
                                <li><i class="icon-bubbles text-success"></i></li>
                                <li class="text-right"><span class="counter">${trainingRequestCount}</span></li>
                            </ul>
                        </div>
                    </div>
                </div>
				
			<!-- SECOND ROW -->
			<div class="row">
				<div class="col-sm-12 col-md-6">
					
				
				
				
				
				
				
				
					<div id="calendar"></div>
				</div>							
			</div>
			
		</div>
	</div>