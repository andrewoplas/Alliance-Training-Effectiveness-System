
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div id="page-wrapper">
          <div class="container-fluid">
  <h1>Project Management Training</h1>     
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam blandit volutpat diam, vitae porttitor turpis varius viverra. Donec eu congue nunc. Ut posuere nisl quis nulla vestibulum, </p>
  <!-- Specifying an 'open' attribute will make all the content visible when the page loads -->
  <details>
    <summary>Course Objective and Outline</summary>
    
    <table>
      <tr>
        <th scope="row">Order Date</th>
        <td>30th May 2003</td>
      </tr>
    </table>
  </details>
  
  <details>
    <summary>Schedule</summary>
    <ul>
      <li><a href="#">Open</a></li>
      <li><a href="#">Edit</a></li>
      <li><a href="#">Duplicate</a></li>
      <li><a href="#">Delete</a></li>
    </ul>
  </details>
  <details>
    <summary>Participants</summary>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>Name</th>
          <th>Position</th>
          <th>Email</th>
        </tr>
     </thead>
      <tbody>
         <c:forEach var="participant" items="${participant}">
      <tr>
      	
		<td>Marc</td>
 		<td>CEO</td>
 		<td>marccolina456@gmail.com</td>
 		<td>Taken</td>		
<!--  	   <td><input id=name="timeIn" type="time" value=""> </td> -->
<!--  	   		<input name="id" type="hidden" value=""> -->
 	   		 		
<%--      <td><button type="submit" name="IDToRemovePart" value="${participant.userID}" class="btn btn-danger btn-sm">x</button></td> --%>
        
      </tr>
      	</c:forEach>
      </tbody>
    </table>
  </details>
  <details>
    <summary>Facilitators</summary>
    <table class="table table-striped">
    <thead>
      <tr>
        <th>Name</th>
        <th>Position</th>
        <th>Email</th>
      </tr>
    </thead>
    <tbody>
         <c:forEach var="facilitator" items="${facilitator}">
      <tr>
      	
		<td>Marc</td>
 		<td>CEO</td>
 		<td>marccolina456@gmail.com</td>
 		<td>Taken</td>				
<%--      <td><button type="submit" name="IDToRemovePart" value="${participant.userID}" class="btn btn-danger btn-sm">x</button></td> --%>
        
      </tr>
      	</c:forEach>
    </tbody>
    </table>
  </details>
  <details>
    <summary>Supervisors</summary>
    <table class="table table-striped">
    <thead>
      <tr>
        <th>Name</th>
        <th>Position</th>
        <th>Email</th>
      </tr>
    </thead>
    <tbody>
        <c:forEach var="supervisor" items="${supervisor}">
      <tr>
      	
		<td>${supervisor.userID}</td>
 		<td>${supervisor.name}</td>
 		<td>${supervisor.email}</td>
 		<td>${supervisor.status}</td>			
<%--      <td><button type="submit" name="IDToRemovePart" value="${participant.userID}" class="btn btn-danger btn-sm">x</button></td> --%>
        
      </tr>
      	</c:forEach>
    </tbody>
    </table>
  </details>

  <!--details id="custom-marker">
    <summary>Custom Marker</summary>
    <p>Here be some content... yarr!</p>
  </details>
</div -->

	<div class="row">
  <div class="col-sm-9"><button type="button" class="btn btn-warning ">Leave from event</button></div>
  <div class="col-sm-3">
    <div class="btn-group">
      <button type="button" class="btn btn-primary "> Edit </button>
      <button type="button" class="btn btn-danger "> Delete </button>
    </div>
  </div>
  
	</div>
	<h1>TRAINING ID : </h1> <input type="text" name="trainingID" value=${trainingID}>
	<form action="http://localhost:8080/ates/attendance/attendanceCheck" method="post">
  <input name="timeIn" type="time">
 <input name="id" type="hidden" value="2">
 	   		
 	 <input name="timeIn" type="time">
 	 <input name="id" type="hidden" value="3">
 	 <button type="submit"> PASS </button>
 	 </form>
 </div>
 </div>
 