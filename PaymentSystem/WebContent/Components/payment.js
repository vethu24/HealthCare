$(document).ready(function(){
	
	if ($("#alertSuccess").text().trim() == ""){
		$("#alertSuccess").hide();
	}
	
	$("#alertError").hide();
});



$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true){
		
		$("#alertError").text(status);
		$("#alertError").show();
		
		return;
	}
	
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
			{
			url : "paymentAPI",
			type : type,
			data : $("#formItem").serialize(),
			dataType : "text",
			complete : function(response, status)
			{
			onItemSaveComplete(response.responseText, status);
			}
			});
	
	
	
	
	
	
});

function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
	$("#alertSuccess").text("Successfully saved.");
	$("#alertSuccess").show();
	$("#divItemsGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
	$("#alertError").text(resultSet.data);
	$("#alertError").show();
	}
	} else if (status == "error")
	{
	$("#alertError").text("Error while saving.");
	$("#alertError").show();
	} else
	{
	$("#alertError").text("Unknown error while saving..");
	$("#alertError").show();
	}
	$("#hidItemIDSave").val("");
	$("#formItem")[0].reset();
}
$(document).on("click", ".btnRemove", function(event)
		{
		$.ajax(
		{
		url : "paymentAPI",
		type : "DELETE",
		data : "cardpayID=" + $(this).data("cardpayid"),
		dataType : "text",
		complete : function(response, status)
		{
		onItemDeleteComplete(response.responseText, status);
		}
		});
		});

function onItemDeleteComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully deleted.");
$("#alertSuccess").show();
$("#divItemsGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while deleting.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while deleting..");
$("#alertError").show();
}
}


$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
	$("#holderName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#ctype").val($(this).closest("tr").find('td:eq(1)').text());
	$("#cardNo").val($(this).closest("tr").find('td:eq(2)').text());
	$("#cvv").val($(this).closest("tr").find('td:eq(3)').text());
	$("#expMonth").val($(this).closest("tr").find('td:eq(4)').text());
	$("#expYear").val($(this).closest("tr").find('td:eq(5)').text());
	$("#total").val($(this).closest("tr").find('td:eq(6)').text());
	
});




//CLIENT-MODEL================================================================
function validateItemForm(){
	// holderName
	if ($("#holderName").val().trim() == ""){
	
		return "Insert Card HolderName.";
	}
	// ctype
	if ($("#ctype").val() == "0"){
		
		return "Select Card Type.";
	}
	// cardNo-------------------------------
	if ($("#cardNo").val().trim() == ""){
		
		return "Insert Card Number.";
	}
	
	
	var temcard=$("#cardNo").val().trim();
	if(!(/^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\d{3})\d{11})$/).test(temcard)){
		
		return "Enter the valid card number.";
	
	}
	
	// cvv------------------------
	if ($("#cvv").val().trim() == ""){
	
		return "Insert cvv number.";
	}
	var temcvv=$("#cvv").val().trim();
	if(!(/^[0-9]{3,4}$/).test(temcvv)){
		return "Enter the valid CVV Number.";
	}

	
	// ctype
	if ($("#expMonth").val() == "0"){
		
		return "Select Month.";
	}
	
	if ($("#expYear").val() == "0"){
		
		return "Select Year.";
	}
	
	var today, someday;
	var exMonth=$("#expMonth").val().trim();
	var exYear=$("#expYear").val().trim();
	today = new Date();
	someday = new Date();
	someday.setFullYear(exYear, exMonth, 1);

	if (someday < today) {
	   
	   return "The expiry date is before today's date. Please select a valid expiry date";
	}
	
	
	// total------------------------
	if ($("#total").val().trim() == ""){
		
		return "Enter Total Amount.";
	}
	
	
	
	var tmpPrice = $("#total").val().trim();
	if (!$.isNumeric(tmpPrice))
	{
	return "Enter numerical value for Total Amount.";
	}
	return true;
}





