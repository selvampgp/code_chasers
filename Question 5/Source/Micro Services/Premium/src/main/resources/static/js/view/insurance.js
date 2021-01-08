
$(document).ready(function(){
	
	$("#currentHealth").multipleSelect({ width: 460, filter: true,placeholder:"Select health" });
	$("#habits").multipleSelect({ width: 460, filter: true,placeholder:"Select habits" });
	
	$('#cardNumber').mask("9999-9999-9999-9999", {});
	$('#cvv').mask("999", {});
	//$('#phoneNumber').mask("9999999999", {});
	
	 $('#dob').daterangepicker({
		    "singleDatePicker": true,
		    "showDropdowns": true,
		    "autoApply": true,
		    "alwaysShowCalendars": true,
		    "opens": "center",
		    "autoUpdateInput": false,
		    "buttonClasses": "btn btn-lg",
		    "maxDate": new Date(),
		}, function(start, end, label) {
				$("#"+$(this)[0].element.attr("id")).val(start.format('MM/DD/YYYY'));		
	});
});

function clearValues(){
	$('#fname').val('');
	$('#lname').val('');
	$('#dob').val('');
	$('#gender').val('0');
	$('#phoneNumber').val('');
	$('#email').val('');
	$('#currentHealth').multipleSelect("uncheckAll");
	$('#habits').multipleSelect("uncheckAll");
	
	$('#firstnameSpan').text('');
	$('#lastnameSpan').text('');
	$('#phonenoSpan').text('');
	$('#emailSpan').text('');
	$('#premiumAMount').text('');
	
	$('#cardNumber').val('');
	$('#cvv').val('');
	$('#expMonth').val('01');
	$('#expYear').val('2021');
}

function backToCalculate(){
	$('#calculate-premium').show();
	$('#calculated-premium').hide();
	$('#buy-premium').hide();
}

function getPremium(){
	clearValues();
	$('#calculate-premium').show();
	$('#calculated-premium').hide();
	$('#buy-premium').hide();
	$('#premium-modal').modal('show');
}

function calculatePremium(){
	
	var status = true;
	
	if($('#fname').val() == ''){
		$('#fname').notify("Please enter your first name.",{ position:"top" }, "error");
		status = false;
	}
	if($('#lname').val() == ''){
		$('#lname').notify("Please enter your last name.",{ position:"top" }, "error");
		status = false;
	}
	if($('#phoneNumber').val() == ''){
		$('#phoneNumber').notify("Please enter your phone number.",{ position:"top" }, "error");
		status = false;
	}
	if($('#email').val() == ''){
		$('#email').notify("Please enter your email.",{ position:"top" }, "error");
		status = false;
	}
	if($('#dob').val() == ''){
		$('#dob').notify("Please enter your email.",{ position:"top" }, "error");
		status = false;
	}
	
	if(!status){
		return false;
	}
	
	$('#firstnameSpan').text($('#fname').val());
	$('#lastnameSpan').text($('#lname').val());
	$('#phonenoSpan').text($('#phoneNumber').val());
	$('#emailSpan').text($('#email').val());

			var personDetails = {
					firstName : $('#fname').val(),
					lastName: $('#lname').val(),
		            emailId : $('#email').val(),
		gender : $('#gender').val() == 0 ? null : $('#gender').val(),
		dateOfBirth : $('#dob').val(),
		phoneNumber : $('#phoneNumber').val(),
		healthIssues : $('#currentHealth').val(),
		habbits : $('#habits').val()
	}

	$.ajax({
		type : "POST",
		url : _calculatePremiumURL,
		data : JSON.stringify(personDetails),
		async : false,
		contentType : 'application/json',
		success : function(response) {
			$('#premiumAMount').text("Your Premium Amount is Rs." + response.toFixed(2));
			$('#calculate-premium').hide();
			$('#calculated-premium').show();
			$('#buy-premium').hide();
		},
		error : function(e) {
		}
	});
}

function buyPremium(){
	
	$('#calculate-premium').hide();
	$('#calculated-premium').hide();
	$('#buy-premium').show();
	
}

function submitPremium(){
	
    var status = true;
	
	if($('#cardNumber').val() == ''){
		$('#cardNumber').notify("Please enter your card number.",{ position:"top" }, "error");
		status = false;
	}
	if($('#cvv').val() == ''){
		$('#cvv').notify("Please enter your cvv number.",{ position:"top" }, "error");
		status = false;
	}
	
	if(!status){
		return false;
	}
	
	var personDetails = {
			firstName : $('#fname').val(),
			lastName: $('#lname').val(),
			emailId : $('#email').val(),
			gender : $('#gender').val() == 0 ? null : $('#gender').val(),
			dateOfBirth : $('#dob').val(),
			phoneNumber : $('#phoneNumber').val(),
			healthIssues : $('#currentHealth').val(),
			habbits : $('#habits').val(),
			cardNumber : $('#cardNumber').val(),
            cvv: $('#cvv').val(),
            expiryMonth: $('#expMonth').val(),
            expiryYear: $('#expYear').val()
		}

		$.ajax({
			type : "POST",
			url : _submitPremiumURL,
			data : JSON.stringify(personDetails),
			async : false,
			contentType : 'application/json',
			success : function(response) {
				$('#premium-modal').modal('hide');
				$('#alertmsg').modal('show');
				//$('body').notify("Success.",{ position:"top" }, "error");
			},
			error : function(e) {
			}
		});

}