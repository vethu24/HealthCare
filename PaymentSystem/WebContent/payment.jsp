<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.payment"%> 
  

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/payment.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-4">
			
				<h1>Online Payment</h1>
				
					<form id="formItem" name="formItem" >
					
						<div class="input-group input-group-sm mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="lblName">Card HolderName: </span>
							</div>
							<input id="holderName" name="holderName" type="text" class="form-control form-control-sm" >
						</div>
					
						<div class="input-group input-group-sm mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="lblName">Card Type: </span>
							</div>
							<select id="ctype" name="ctype">
								<option value="0">--Select Card--</option>
								<option value="VISA">VISA</option>
								<option value="MasterCard">MasterCard</option>
						
							</select>
						</div>
						
						<div class="input-group input-group-sm mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="lblName">Card Number: </span>
							</div>
							 <input id="cardNo"  name="cardNo" type="text"  class="form-control form-control-sm" >
						</div>
						
						
						<div class="input-group input-group-sm mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="lblName"> CVV: </span>
							</div>
						<input id="cvv"  name="cvv" type="text" class="form-control form-control-sm"   >
						
						</div>
					
						
						
						<div class="input-group input-group-sm mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="lblName">Expire Date: </span>
							</div>
							<select id="expMonth" name="expMonth">
								<option value="0">Select Month</option>
								
							    <option value="01">January</option>
							    <option value="02">February</option>
							    <option value="03">March</option>
							    <option value="04">April</option>
							    <option value="05">May</option>
							    <option value="06">June</option>
							    <option value="07">July</option>
							    <option value="08">August</option>
							    <option value="09">September</option>
							    <option value="10">October</option>
							    <option value="11">November</option>
							    <option value="12">December</option>
						
							</select>
							<select id="expYear" name="expYear">
								
								<option value="0">Select Year</option>
							   
							    <option value="2020">2020</option>
							    <option value="2021">2021</option>
							    <option value="2022">2022</option>
							    <option value="2023">2023</option>
							    <option value="2024">2024</option>
							    <option value="2025">2025</option>
							    <option value="2026">2026</option>
							    <option value="2027">2027</option>
							    <option value="2028">2028</option>
							    <option value="2029">2029</option>
							    <option value="2030">2030</option>
							    <option value="2031">2031</option>
						
							</select>
						</div>
						
						<div class="input-group input-group-sm mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="lblName">Total Amount: </span>
							</div>
						<input id="total"  name="total" type="text" class="form-control form-control-sm"  >
						
						</div>
					
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
						
						<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
					</form>
					
					<div id="alertSuccess" class="alert alert-success"></div>
					
					<div id="alertError" class="alert alert-danger"></div>
					
					
			</div>
				<div class='col-12'>
				<div id="divItemsGrid">
					
						<%
						payment p=new payment();
						out.print(p.readPayment()); 
					%>
				</div>
					
					</div>
		</div>
	</div>
	
</body>
</html>