

$(document).ready(function() {
		    $(".paymentoptions").addClass('active').siblings().removeClass('active');
		    $(".payonline").addClass('active').siblings().removeClass('active');
		    $('#todays_amount').number(true, 2);
		    $('#recurring_amount').number(true, 2);



	});

$('#accountnumber').mask("0000000000000000000", {
		//clearIfNotMatch: true
	});

$('#zipcode').mask("000000000000", {
		//clearIfNotMatch: true
	});

$(function() {

    //$( "#first-day-month" ).datepicker();
    //$( "#second-day-month" ).datepicker();
    $( "#day-week" ).datepicker({
        beforeShowDay: function (date) {

            if (date.getDate() == 29 || date.getDate() == 30 || date.getDate() == 31) {
                return [false, ''];
            }
            return [true, ''];
           }
    });
    
    $('#payment-date, #asPerDate').change(function(){
    	formatPayDate(this);
    }).datepicker();
    
  });

function formatPayDate(object){
	 var date = formatDate($(object).val(), "pay");
	 date = $.datepicker.parseDate('mm/dd/yy', date);
     $(object).datepicker('setDate', date);
}


$('#payment-date').mask("00/00/0000", {
  });
$('#day-week').mask("00/00/0000", {
   });
$('#asPerDate').mask("00/00/0000", {
	});

	 /**
	 **
	   Rest-URL Controller
	 **
	 **/

	    /**  Form Validation  **/

	  function formValidation() {
	    	var i=0;
		  var validation_repeat = [];
			var obj_rep;
			obj_rep = [ {
				"id" : "consumerId",
				"validate" : true,
				"message" : "consumerId cannot be blank",
				"class" : "form-consumerId"
			} ,{
				"id" : "totaldue",
				"validate" : true,
				"message" : totaldueerrormsg,
				"class" : "form-totaldue"
			} ,{
				"id" : "paymentfrequency",
				"validate" : true,
				"message" : payfreqerrormsg,
				"class" : "form-paymentfrequency"
			},{
				"id" : "first-day-month",
				"validate" : true,
				"message" : firstdateerrormsg,
				"class" : "form-first-day-month"
			},
			{
				"id" : "second-day-month",
				"validate" : true,
				"message" : seconddateerrormsg,
				"class" : "form-second-day-month"
			},
			{
				"id" : "day-week",
				"validate" : true,
				"message" : weekdayeerrormsg,
				"class" : "form-day-week"
			},
			{
				"id" : "payment-date",
				"validate" : true,
				"message" : paydateerrormsg,
				"class" : "form-payment-date"
			},
			{
				"id" : "todays_amount",
				"validate" : true,
				"message" : payamounterrormsg,
				"class" : "form-todays-amount"
			},{
				"id" : "recurring_amount",
				"validate" : true,
				"message" : recamounterrormsg,
				"class" : "form-recurring-amount"
			},{
				"id" : "asPerDate",
				"validate" : true,
				"message" : closedateerrormsg,
				"class" : "form-end-date"
			},
			{
				"id" : "routenumber",
				"validate" : true,
				"message" : routenoerrormsg,
				"class" : "form-routenumber"
			},
			{
				"id" : "accountnumber",
				"validate" : true,
				"message" : accountnoerrormsg,
				"class" : "form-accountnumber"
			},
			{
				"id" : "name",
				"validate" : true,
				"message" : nameerrormsg,
				"class" : "form-name"
			},
			{
				"id" : "address1",
				"validate" : true,
				"message" : add1errormsg,
				"class" : "form-address1"
			},
			{
				"id" : "address2",
				"validate" : false,
				"message" : add2errormsg,
				"class" : "form-address2"
			},
			{
				"id" : "city",
				"validate" : true,
				"message" : cityerrormsg,
				"class" : "form-city"
			},
			{
				"id" : "state",
				"validate" : true,
				"message" : stateerrormsg,
				"class" : "form-state"
			},
			{
				"id" : "selectedcountry",
				"validate" : true,
				"message" : countryerrormsg,
				"class" : "form-selectedcountry"
			},
			{
				"id" : "zipcode",
				"validate" : true,
				"message" : zipcodeerrormsg,
				"class" : "form-zipcode"
			}/*,
			{
				"id" : "comment",
				"validate" : false,
				"message" : commenterrormsg,
				"class" : "form-comment"
			},*/
			];


			$.each(obj_rep, function(key, value) {
				var getcombotext;
				var getfielddata;

				if (obj_rep[key].validate) {
					getfielddata=$("#" + obj_rep[key].id).val();

					if(obj_rep[key].id == "consumerId"){
						 getcombotext=$('#consumerId').val();
						 getfielddata=getcombotext;

					 }
					 if(obj_rep[key].id == "totaldue"){
						 getcombotext=$('#totaldue').val();
						 getfielddata=getcombotext;

					 }
                     if(obj_rep[key].id == "paymentfrequency"){
                    		getcombotext=$('#paymentfrequency').val();
							getfielddata=getcombotext;

					 }
                     if(obj_rep[key].id == "first-day-month"){
						 getcombotext=$('#first-day-month').val();
						    if($('select[id="paymentfrequency"]').val()=="M" || $('select[id="paymentfrequency"]').val()=="S"){
									 getfielddata=getcombotext;
						    }else{

						    	getfielddata="0";
						    }

					 }
                     if(obj_rep[key].id == "second-day-month"){
						 getcombotext=$('#second-day-month').val();
						  if($('select[id="paymentfrequency"]').val()=="S"){
							  if(getcombotext != '' && getcombotext == $('#first-day-month').val()){
								  obj_rep[key].message=firstAndSecondDayererrormsg;
	                    		  getfielddata='';
							  }else{
							   	 getfielddata=getcombotext;
							  }
					    }else{
					    	getfielddata="0";
					    }

					 }
                     if(obj_rep[key].id == "day-week"){
						 getcombotext=$('#hid-day-week').val();
						 if($('select[id="paymentfrequency"]').val()=="W" || $('select[id="paymentfrequency"]').val()=="B"){
							getfielddata=getcombotext;
				         }else{
				    	    getfielddata="0";
				         }
					 }
                     if(obj_rep[key].id == "payment-date"){
                    	 getcombotext=$('#payment-date').val();
	                       if(getcombotext != ''){
                    	var payDate=$('#payment-date').datepicker('getDate');
                    	var today = new Date();
                    	var todayOneTimePay=new Date();
                    	todayOneTimePay.setDate(todayOneTimePay.getDate() + 15);
                    	todayOneTimePay.setHours(0,0,0,0);
                    	 today.setHours(0,0,0,0);
                    	 payDate.setHours(0,0,0,0);
                    	 
                    	 if((getcombotext.length < 10 || ((getcombotext.substring(0, 2))*1) > 12) || ((getcombotext.substring(3, 5))*1) > 31){
                    		 obj_rep[key].message=invalidDateererrormsg;
                    		 getfielddata='';
                    	 }else{
                    	   if(today > payDate){
                    		 obj_rep[key].message=paydategreatererrormsg;
                    		 getfielddata='';
                    	   }else if($('select[id="paymentfrequency"]').val()=="O" && payDate > todayOneTimePay){
                    		 obj_rep[key].message=paydategreater15dayserrormsg;
                    		 getfielddata='';
                    	   }else{
                    		 getfielddata=getcombotext;
                    	   }
                    	 }
                       	    }else{
                       	    	if($('select[id="paymentfrequency"]').val()!="O"){
						            obj_rep[key].message=paydateerrormsg;
                       	    		   getfielddata=getcombotext;

                       	    	}else{
						            getfielddata=getcombotext;

                       	    	}
                       	    }

					 }
                     if(obj_rep[key].id == "todays_amount"){

                    	 getcombotext=$('#todays_amount').val();

         				 var totalDue=$('#totaldue').val().replace("TotalDue - ", "");

                    	 var miniPayPercentValue=(parseFloat(totalDue) * parseFloat(miniPayPercentage)) / 100;

                    	 if($('select[id="paymentfrequency"]').val()!="O"){
                    		 if($('#todays_amount').val()!=''){

                    			 if($('#todays_amount').val() <= miniPayPercentValue){
                    				 obj_rep[key].message=payamountlessthanminpayerrormsg;
		                    		 getfielddata='';
                    			 }else if(0 < parseFloat(miniPayValue) && $('#todays_amount').val() < parseFloat(miniPayValue)){
                                	   obj_rep[key].message=payamountlessthanminpayValueerrormsg.replace("[replaceMinPayValue]", miniPayValue);
  		                    		   getfielddata='';
                                   } else if(overPayment!="Y" && parseFloat(totalDue)<$('#todays_amount').val()){
                    					 obj_rep[key].message=payamountgreatertotaldueerrormsg.replace("[replaceTodaysAmount]", $('#todays_amount').val()).replace("[replaceTotalDue]", totalDue);
    		                    		 getfielddata='';
                        			 }else{
                        				 getfielddata="0";
                        			 }
                    		 }else{
                    			 getfielddata="0";
                    		 }
                    	 }else{
                    		 if($('#todays_amount').val()!=''){

                    			 if(overPayment!="Y" && parseFloat(totalDue)<$('#todays_amount').val()){
                    				 obj_rep[key].message=payamountgreatertotaldueerrormsg.replace("[replaceTodaysAmount]", $('#todays_amount').val()).replace("[replaceTotalDue]", totalDue);
		                    		 getfielddata='';
                    			 }else{
                                       if($('#todays_amount').val() <= miniPayPercentValue){
                                    	   obj_rep[key].message=payamountlessthanminpayerrormsg;
      		                    		   getfielddata='';
                                       }else if(0 < parseFloat(miniPayValue)){
                                    	   if($('#todays_amount').val() < parseFloat(miniPayValue)){
                                    	   obj_rep[key].message=payamountlessthanminpayValueerrormsg.replace("[replaceMinPayValue]", miniPayValue);
      		                    		   getfielddata='';
      		                    		   }else{getfielddata='0';}
                                       }else{
                                    	   getfielddata='0';
                                       }
                    			 }
                    		 }else{
                    			 getfielddata='';
                    		 }
                    	}
					 }
                     if(obj_rep[key].id == "recurring_amount"){
                    	 getcombotext=$('#recurring_amount').val();
                    	 if($('select[id="paymentfrequency"]').val()!="O"){
                    		 getfielddata=getcombotext;
                    	 }else{
                    		 getfielddata="0";

                    	 }
                     }
                     if(obj_rep[key].id == "asPerDate"){
                    	 getcombotext=$('#asPerDate').val();
                    	 if($('select[id="paymentfrequency"]').val()!="O" && $('input[type=radio][name=option_name]:checked').attr('id')=="datePaid"){
                    		 if(getcombotext != ''){
								 var perDate=new Date($('#asPerDate').val());
		                    	var today = new Date();
		                    	 var startDate = $('#payment-date').val();
		                    	 if(startDate != ''){
		                    		 startDate = new Date($('#payment-date').val());
		                    		 startDate.setHours(0,0,0,0);
		                    	 }
		                    	 today.setHours(0,0,0,0);
		                    	 perDate.setHours(0,0,0,0);
		                    	 if((getcombotext.length < 10 || ((getcombotext.substring(0, 2))*1) > 12) || ((getcombotext.substring(3, 5))*1) > 31){
		                    		 obj_rep[key].message=invalidDateererrormsg;
		                    		 getfielddata='';
		                    	 }else{
		                    	    if(today > perDate){
		                    		 obj_rep[key].message=closedategreatererrormsg;
		                    		 getfielddata='';
		                    	    }else if($('#payment-date').val() != '' & startDate > perDate){
		                    		 obj_rep[key].message="End date should not earlier than start date.";
		                    		 getfielddata='';
		                    	    }else{
						   	         getfielddata=getcombotext;
		                    	  }
		                    	 }
							 }else{
								 getfielddata=getcombotext;
							 }
                    	 }else{
 					    	getfielddata="0";
 					    }
                     }
                     if(obj_rep[key].id == "routenumber"){
                    	 getcombotext=$('#routenumber').val();
                    	 if (getfielddata=='') {
                    		  $('.bank-name').text("");
                    	 }
						 getfielddata=getcombotext;

					 }
                     if(obj_rep[key].id == "accountnumber"){
                    	 getcombotext=$('#accountnumber').val();
						 getfielddata=getcombotext;

					 }
                     if(obj_rep[key].id == "name"){
                    	 getcombotext=$('#name').val();
						 getfielddata=getcombotext;

					 }
                     if(obj_rep[key].id == "address1"){
                    	 getcombotext=$('#address1').val();
						 getfielddata=getcombotext;

					 }
                     if(obj_rep[key].id == "address2"){
                    	 getcombotext=$('#address2').val();
						 getfielddata=getcombotext;

					 }
                     if(obj_rep[key].id == "city"){
                    	 getcombotext=$('#city').val();
						 getfielddata=getcombotext;

					 }
                     if(obj_rep[key].id == "state"){
                    	 getcombotext=$('#state').val();
						getfielddata=getcombotext;

					 }
                     if(obj_rep[key].id == "selectedcountry"){
                    	 getcombotext=$('#selectedcountry').val();
						getfielddata=getcombotext;

					 }
                     if(obj_rep[key].id == "zipcode"){
                    	 getcombotext=$('#zipcode').val();
						 getfielddata=getcombotext;

					 }
                    /* if(obj_rep[key].id == "comment"){
                    	 getcombotext=$('#comment').val();
						 getfielddata=getcombotext;

					 }*/
                     if (getfielddata=='') {
                    	 $("#" + obj_rep[key].id).css('border-color', 'red');
                    	 // $("#" + obj_rep[key].class).append('<span class="help-block with-errors">Consumer Id is Required</span>');
                    	 $("." + obj_rep[key].class).html('<span style="color:red" id='+obj_rep[key].id+'_span>'+obj_rep[key].message+'</span>');
						 validation_repeat[i++] = false;
						} else {
							$("#" + obj_rep[key].id).css("border-color", '');
						$("." + obj_rep[key].class).html('');
							validation_repeat[i++] = true;
						}
			    	}
			});

			return validation_repeat;

	    }


	 /**
	 **
	 Auto Suggestions
	 **
	 **/

	 $(document).ready(function() {
		 
		 setLocaleAutoLookUp($("#city"), $("#city"), $("#state"), $("#selectedcountry"), $("#zipcode"), "CITY");
		 setLocaleAutoLookUp($("#state"), null, $("#state"), $("#selectedcountry"), null, "STATE");
		 setLocaleAutoLookUp($("#selectedcountry"), null, null, $("#selectedcountry"), null, "COUNTRY");
		 setLocaleAutoLookUp($("#zipcode"), $("#city"), $("#state"), $("#selectedcountry"), $("#zipcode"), "ZIPCODE");
		 
      	 StateSuggestion();
      });





	 $(document).ready(function() {

      	 $("#routenumber").autocomplete({
      	        dataType: 'json',
      	        autoFocus: true,
      	        minLength: 2,
      	        source: function (request, response) {
      	        	var routenumber=$("#routenumber").val();

      	            return $.ajax({
      	                type: 'GET',
      	                contentType: "application/json; charset=utf-8",
      	                url: routenumberurl + routenumber,
      	                dataType: 'json',
      	                success: function (data) {
      	                    response($.map(data, function (c) {

      	                        return {
      	                        	label: c.aba,
      	                            value: c.aba,
      	                            id: c.Id,
      	                            bankname:c.name,
      	                        };
      	                    }));
      	                }
      	            });
      	        },

      	        select: function (event, ui) {
      	            $('#hid_route').val(ui.item.id);
      	            $('.bank-name').text("("+ui.item.bankname+")");
      	            return true;
      	        }

      	    });
      });

	 function StateSuggestion(){
			$.ajax({
				type : 'GET',
				url :   demographicsUrl,
				 async:false,
				datatype : 'json',
				contentType: 'application/json',
				success : function(response) {
					var data = JSON.parse(response);
					setPaymentMethod(data.paymentMethod);
					accountdetails(data.accdetail);
				},
				error : function(xhr, status, error) {
					console.log(status);
				}
			});
	 }

	 function accountdetails(accdetail){
		 if(accdetail==null){
			 $("#totaldue").val("TotalDue - "+"0.0");
		 }else{
		 if(accdetail.acoConsumerId !='' || accdetail.acoConsumerId !=null){
	     		$("#consumerId").val(accdetail.acoConsumerId);
	     	}
     	if(accdetail.acoTotalDue !='' && accdetail.acoTotalDue !=null){
     		$("#totaldue").val("TotalDue - "+accdetail.acoTotalDue.toFixed(2));
     	}else{
     		$("#totaldue").val("TotalDue - "+"0.0");
     	}
	   }
     }
	 
	 function setPaymentMethod(data){

		 var getelement = $("#paymentfrequency");
		 getelement.empty();
		 getelement.append($("<option class='option' selected='selected'></option>").attr("value", "").text(paymentFrequency));
		 for(i = 0; i < data.length; i++){
			 getelement.append($("<option class='option'></option>").attr("value", data[i].value).text(data[i].description));
		 }

	}

		function getData(){
			var temp;
			var status=true;
		    var validation_repeat = formValidation();

		for(temp=0;temp<validation_repeat.length;temp++){

			if(validation_repeat[temp]==false){

				var status=false;
			}
		}

		    if(status==true)
			{
		    	$('#btnconfirm').attr('data-target','#myModal');
		    	  populatedialog();
			}
		    else{
		    	$('#btnconfirm').attr('data-target','');
		    }

		};

		function populatedialog(){
			 $('#dia_consumerId').text($('#consumerId').val());
			 $('#dia_totaldue').text($('#totaldue').val().replace("TotalDue - ", ""));
			 $('#dia_paymentfrequency').text($("#paymentfrequency option:selected").text());
			 $('#dia_payment-date').text($('#payment-date').val());
			 $('#dia_todays_amount').text($('#todays_amount').val());
			 $('#dia_recurring_amount').text($('#recurring_amount').val());
			 $('#dia_routenumber').text($('#routenumber').val());
			 $('#dia_accountnumber').text($('#accountnumber').val());
			 $('#dia_name').text($('#name').val());
			var billAddress=$('#address1').val()+", "+$('#address2').val()+", "+$('#selectedcountry').val()+", "+$('#state').val()+", "+$('#zipcode').val();
			 $('#dia_billingaddress').text(billAddress);
			/* $('#dia_address2').text($('#address2').val());
			 $('#dia_city').text($('#city').val());
			 $('#dia_hid_city').val($('#hid_city').val());
			 $('#dia_country').text($('#selectedcountry option:selected').text());
			 $('#dia_country_id').val($('#hid_country').val());
			 $('#dia_state').text($('#state').children(":selected").text());
			 $('#dia_hid_state').val($('#hid_state').val());
			 $('#dia_zipcode').text($('#zipcode').val());*/
		//	 $('#dia_comment').text($('#comment').val());

			 var option=$('input[name=option_name]:checked').attr('id');

			 if(option == 'accountPaid'){
			 $('#dia_accountPaid').text(account_master_paidLabelText);
			 }else if(option == 'datePaid'){
				 $('#dia_accountPaid').text($('#asPerDate').val());
			 }

			 var payFreq=$('select[id="paymentfrequency"]').val();
			 switch (payFreq) {
	     		case 'M':
	     			 $('#dia-pay-date-label').text(payStartDateLabelText);
	     			 $('#dia_first-day-month-div').show();
	     			 $('#dia_recurring-amount-div').show();
	     			 $('#dia_second-day-month-div').hide();
	     			 $('#dia_day-week-div').hide();
	     			 $('#dia_account-date-Paid-div').show();
	     			 $('#dia_first-day-month').text($('#first-day-month').val());
	     			break;
	             case 'S':
	            	 $('#dia-pay-date-label').text(payStartDateLabelText);
	            	 $('#dia_first-day-month-div').show();
	            	 $('#dia_recurring-amount-div').show();
	     			 $('#dia_second-day-month-div').show();
	     			 $('#dia_day-week-div').hide();
	     			 $('#dia_account-date-Paid-div').show();
	     			 $('#dia_first-day-month').text($('#first-day-month').val());
	     			 $('#dia_second-day-month').text($('#second-day-month').val());
	     			break;
	             case 'W':
	            	 $('#dia-pay-date-label').text(payStartDateLabelText);
	            	 $('#dia_first-day-month-div').hide();
	            	 $('#dia_recurring-amount-div').show();
	     			 $('#dia_second-day-month-div').hide();
	     			 $('#dia_day-week-div').show();
	     			 $('#dia_account-date-Paid-div').show();
	     			 $('#dia_day-week').text($('#day-week').val());
	     	        break;
	             case 'B':
	            	 $('#dia-pay-date-label').text(payStartDateLabelText);
	            	 $('#dia_first-day-month-div').hide();
	            	 $('#dia_recurring-amount-div').show();
	     			 $('#dia_second-day-month-div').hide();
	     			 $('#dia_day-week-div').show();
	     			 $('#dia_account-date-Paid-div').show();
	     			 $('#dia_day-week').text($('#day-week').val());
	     	        break;
	     		 default:
	     			 $('#dia-pay-date-label').text(payDateLabelText);
	     			 $('#dia_account-date-Paid-div').hide();
	     		     $('#dia_recurring-amount-div').hide();
	     		     $('#dia_first-day-month-div').hide();
    			     $('#dia_second-day-month-div').hide();
    			     $('#dia_day-week-div').hide();
	     			 break;
	     		}
		}


		/**
		**
		* Check Payment Save via WebService Call
		**
		**/
		function finalSubmit(){
			var checkpayment=checkpaymentsdetail();
			var receiptEmail='N';


			makecheckpaymentajax(checkpayment);
		};

		function makecheckpaymentajax(checkpayment){
			$.ajax({
				  type: 'POST',
				  url:  checkpaymenturl,
				  async:false,
				  data: "{\"checkpayment\":"+JSON.stringify(checkpayment)+"}",
		          processData: false,
		          contentType: "application/json; charset=utf-8",
		          datatype: 'json',
				  success: function(data, status, xhr){
			try{
					  $('#myModal').modal('hide');

					  var data1 = JSON.parse(data);
					  var statusone=data1.status_one;
					  var statusrec=data1.status_rec;
					  var payResource=data1.payResource;

					  $("#statusModal").modal();

					  if(statusone == "S" || statusrec == "S"){
						  if(statusone == "S" && statusrec == "S"){
							  $("#success_notify").empty();
							  $("#success_notify").append("<div><div class='submitted-txt'>"+oneTimePaySuccess+"</div></div><div><div class='submitted-txt'>"+payPlanSuccess+"</div></div><p>"+oneTimePaySuccessID+""+payResource.mpspostingid+"</p><p>"+payPlanSuccessID+""+payResource.mpspostingidrec+"</p><div class='clear-fix'></div><div class='cheque-img'><button id='pdfId' class='btn btn-primary' onClick=loadPaymentReceipt("+ payResource.payId +") >"+payReceipt+"</button></div>");

						  }else{
							  if(statusone == "S"){
								  $("#success_notify").empty();
								  $("#success_notify").append("<div><div class='submitted-txt'>"+oneTimePaySuccess+"</div></div><p>"+oneTimePaySuccessID+""+payResource.mpspostingid+"</p><div class='clear-fix'></div><div class='cheque-img'><button id='pdfId' class='btn btn-primary' onClick=loadPaymentReceipt("+ payResource.payId +") >"+payReceipt+"</button></div>");
							  }else if(statusone == "F"){
								  $("#success_notify").empty();
								  $("#success_notify").append("<div><p class='remarks red'>"+payResource.remarks+"</p></div>");
							  }else{
								  $("#success_notify").empty();
								  $("#success_notify").append("");
							  }

		                     if(statusrec == "S"){
		                    	 $("#success_notify").append("<div><div class='submitted-txt'>"+payPlanSuccess+"</div></div><p>"+payPlanSuccessID+" "+payResource.mpspostingidrec+"</p>");
							  }else if(statusrec == "F"){
								  $("#success_notify").append("<div><p class='remarks red'>"+payResource.remarksRec+"</p></div>");
							  }else{
								  $("#success_notify").append("");
							  }
						  }
						  unSelectFields();
					  }else{
						  $("#success_notify").empty();
						  if(payResource.remarksRec == null){
						  $("#success_notify").append("<div><p class='remarks red'>"+payResource.remarks+"</p></div>");
						  }else{
						  $("#success_notify").append("<div><p class='remarks red'>"+payResource.remarksRec+"</p></div>");}
					  }

			}catch(e){
				$('#myModal').modal('hide');
				  $("#statusModal").modal();
				 $("#success_notify").empty();
				  $("#success_notify").append("<div><p>"+APIFailed+"</p></div>");

				//  unSelectFields();
			}
					},
		          error: function(xhr, status, error) {
		        	//  console.log(textStatus, errorThrown);
		        	  $('#myModal').modal('hide');
		        	  $("#statusModal").modal();
		        	  $("#success_notify").empty();
		        	  $("#success_notify").append("<div><p>"+APIFailed+"</p></div>");
		        	}
				});
		}


		function unSelectFields(){

			  $("#paymentfrequency").val("");
			  $("#payment-date").val("");
			  $("#first-day-month").val("");
			  $("#second-day-month").val("");
			  $("#day-week").val("");

			  $("#todays_amount").val("");
			  $("#recurring_amount").val("");

			  $( "#accountPaid" ).prop( "checked", true );
			  $( "#datePaid" ).prop( "checked", false );

			  $("#asPerDate").prop('disabled', true);
			  $('#asPerDate').datepicker('setDate', null);

			  $("#routenumber").val("");
			  $("#accountnumber").val("");
			  $("#name").val("");
			  $("#address1").val("");
			  $("#address2").val("");
			  $("#city").val("");
			  $("#state").val("");
			  $("#selectedcountry").val("US");
			  $("#zipcode").val("");
			//  $("#comment").val("");

			  $('.bank-name').text("");

		}


		function checkpaymentsdetail(){
			 var formData = [];
			 var selectedFreq=$('select[id="paymentfrequency"]').val();
			 var option=$('input[name=option_name]:checked').attr('id');

			 var paymentenddate=null;
			 var runsuntil=null;
			 if(option == 'accountPaid'){
				 paymentenddate=$('#payment-date').val();
				 runsuntil="0";
				 }else if(option == 'datePaid'){
				 paymentenddate=$('#asPerDate').val();
				 runsuntil="1";
				 }
			 switch (selectedFreq) {
	     		case 'M':
	     			var minpayamount=$('#recurring_amount').val();
	     			var todaypayamount=$('#todays_amount').val() == "" ? "0" : $('#todays_amount').val();
	   			    var payamount=$('#recurring_amount').val();
	   			    var paymentdate=$.datepicker.formatDate('mm/dd/yy', new Date());
			        var paymentstartdate=$('#payment-date').val();
					var daytwo="";
					var weekday="";
	     			break;
	             case 'S':
	            	 var minpayamount=$('#recurring_amount').val();
		   			 var todaypayamount=$('#todays_amount').val() == "" ? "0" : $('#todays_amount').val();
		   			 var payamount=$('#recurring_amount').val();
		   			 var paymentdate=$.datepicker.formatDate('mm/dd/yy', new Date());
		   		     var paymentstartdate=$('#payment-date').val();
					 var daytwo=$('#second-day-month').val();
					 var weekday="";
	     			break;
	             case 'W':
	            	 var minpayamount=$('#recurring_amount').val();
	            	 var todaypayamount=$('#todays_amount').val() == "" ? "0" : $('#todays_amount').val();
		   			 var payamount=$('#recurring_amount').val();
		   			 var paymentdate=$.datepicker.formatDate('mm/dd/yy', new Date());
				     var paymentstartdate=$('#payment-date').val();
					 var daytwo="";
					 var weekdate=new Date($('#hid-day-week').val());
					 var weekday="0"+weekdate.getDay();
	     	        break;
	             case 'B':
	            	 var minpayamount=$('#recurring_amount').val();
	            	 var todaypayamount=$('#todays_amount').val() == "" ? "0" : $('#todays_amount').val();
		   			 var payamount=$('#recurring_amount').val();
		   			 var paymentdate= $.datepicker.formatDate('mm/dd/yy', new Date());
				     var paymentstartdate=$('#payment-date').val();
					 var daytwo="";
					 var weekdate=new Date($('#hid-day-week').val());
					 var weekday="0"+weekdate.getDay();
	     	        break;
	             case 'O':
	            	 var minpayamount="0";
		   			 var todaypayamount="0";
		   			 var payamount=$('#todays_amount').val();
		   			 var paymentdate=$('#payment-date').val();
				     var paymentstartdate=$.datepicker.formatDate('mm/dd/yy', new Date());
				     paymentenddate="";
					 var daytwo="";
					 var weekday="";
					 runsuntil=null;
	     			break;
	     		}



			 formData = {
				"paymentdate":paymentdate,
				"paymentstartdate":paymentstartdate,
				"paymentenddate":paymentenddate,
				"daytwo":daytwo,
				"weekday":weekday,
				"runsuntil":runsuntil,
				"payamount": payamount,
				"minpayamount": minpayamount,
				"todaypayamount": todaypayamount,
                "accountnumber":btoa($('#dia_accountnumber').text()),
                "totaldue":$('#dia_totaldue').text(),
				"paymode":$('select[id="paymentfrequency"]').val(),
                "routingnumber":$('#dia_routenumber').text(),
                "consumername":btoa($('#name').val()),
				"address1":btoa($('#address1').val()),
				"address2":btoa($('#address2').val()),
				"paytype":"CH",
				//"city":btoa($('#city').text()),
				"cityid":btoa($('#city').val()),
				//"state":btoa($('#state').children(":selected").text()),
				"stateid":btoa($('#state').val()),
			//	"country":btoa($('#country').children(":selected").text()),
				"countryid":btoa($('#selectedcountry').val()),
				"zipcode":btoa($('#zipcode').val()),
			//	"comments":$('#dia_comment').text(),
                "receiptEmail":$('#receiptEmail').is(":checked") == true ? 'Y' : 'N'
			 };

			 return formData;
		}
       function showPayFreqFields(){

			var selectedFreq=$('select[id="paymentfrequency"]').val();
			$("#payment-date").prop('disabled', true);
			$('#arrow-button-div').show();

			 switch (selectedFreq) {
	     		case 'M':
	     			 $('#first-day-month-div').show();
	     			$("#first-day-month option[value='']").remove();
	     			$('#first-day-month').prepend($('<option>', {
	     			    value: '',
	     			    text: 'Day Of The Month'
	     			}));
	     			$('#first-day-month').val('');
	     			 $('#account-date-Paid-div').show();
	     			 $('#recurring-amount-div').show();
	     			 $('#second-day-month-div').hide();
	     			 $('#day-week-div').hide();
	     			 $('#payment-date').attr("placeholder", payStartDatePlaceholderText);
	     			 $('#payment-date').val("");
	     			 $('#todays_amount').attr("placeholder", todaysPayAmountPlaceholderText);
	     			break;
	             case 'S':
	            	 $('#first-day-month-div').show();
		     			 setTimeout(function () {
		     				 $("#first-day-month option[value='']").remove();
		     				$('#first-day-month').prepend($('<option>', {
			     			    value: '',
			     			    text: 'First Day Of The Month'
			     			}));
		     				$('#first-day-month').val('');
			                 }, 0);
	            	 $('#account-date-Paid-div').show();
	            	 $('#recurring-amount-div').show();
	            	 $('#second-day-month-div').show();
	            	 $('#day-week-div').hide();
	            	 $('#payment-date').attr("placeholder", payStartDatePlaceholderText);
	            	 $('#payment-date').val("");
	            	 $('#todays_amount').attr("placeholder", todaysPayAmountPlaceholderText);
	     			break;
	             case 'W':
	            	 $('#account-date-Paid-div').show();
	            	 $('#day-week-div').show();
	            	 $('#recurring-amount-div').show();
	            	 $('#first-day-month-div').hide();
	     			 $('#second-day-month-div').hide();
	     			 $('#payment-date').attr("placeholder", payStartDatePlaceholderText);
	     			 $('#payment-date').val("");
	     			 $('#todays_amount').attr("placeholder", todaysPayAmountPlaceholderText);
	     	        break;
	             case 'B':
	            	 $('#account-date-Paid-div').show();
	            	 $('#day-week-div').show();
	            	 $('#recurring-amount-div').show();
	            	 $('#first-day-month-div').hide();
	     			 $('#second-day-month-div').hide();
	     			 $('#payment-date').attr("placeholder", payStartDatePlaceholderText);
	     			 $('#payment-date').val("");
	     			 $('#todays_amount').attr("placeholder", todaysPayAmountPlaceholderText);
	     	        break;
	     		default:
	     			 $("#payment-date").prop('disabled', false);
	     		     $('#arrow-button-div').hide();
	     			 $('#account-date-Paid-div').hide();
	     			 $('#day-week-div').hide();
	     			 $('#recurring-amount-div').hide();
           	         $('#first-day-month-div').hide();
    			     $('#second-day-month-div').hide();
    			     $('#payment-date').attr("placeholder", oneTimePayDatePlaceholderText);
    			     $('#payment-date').val("");
	     			 $('#todays_amount').attr("placeholder", payAmountPlaceholderText);
	     			break;
	     		}
		}

		function getDay(){
			
			 if($('#day-week').val() != '' && ($('#day-week').val().length < 8 || $('#day-week').val().length == 9 || (($('#day-week').val().substring(0, 2))*1) > 12) || (($('#day-week').val().substring(3, 5))*1) > 31){
				 $("#day-week_span").css('border-color', 'red');
				 $(".form-day-week").html('<span style="color:red" id="day-week_span_span">'+invalidDateererrormsg+'</span>');
				 $('#hid-day-week').val('');
				 $('#payment-date').val('');
			 }else{
					$("#day-week_span").css("border-color", '');
					$('#day-week_span_span').remove();
					
					formatPayDate($('#day-week'));
					
					 $('#hid-day-week').val($('#day-week').val());
					 var getAsDate=$('#day-week').datepicker('getDate');
					 var dayName = $.datepicker.formatDate('DD', getAsDate);
					 $('#day-week').val(dayName);
					 $('#payment-date').val($.datepicker.formatDate('mm/dd/yy', getAsDate));
			 }
		}

		function removeDisabled(radio){
			if(radio.id=="datePaid"){
				$("#asPerDate").prop('disabled', false);
			}else{
				$("#asPerDate").prop('disabled', true);
				$('#asPerDate').datepicker('setDate', null);
			}
		}

		function changeStartDate(){
			var firstDay=$('#first-day-month').val();
			var currentDate=new Date();
			var payStartDate=$('#payment-date').val();
			if(firstDay != "")
			if(payStartDate != ""){
				var date=new Date(payStartDate);
				date.setDate(firstDay);
				$('#payment-date').val($.datepicker.formatDate('mm/dd/yy', date));
			}else{
				if(currentDate.getDate()>firstDay){
					currentDate.setMonth(currentDate.getMonth()+1);
				}
				currentDate.setDate(firstDay);
				$('#payment-date').val($.datepicker.formatDate('mm/dd/yy', currentDate));
			}
		}

		var semiCount=0;
		function changeMonthLeft(){

			var firstDay=$('#first-day-month').val();
			var secondDay=$('#second-day-month').val();
			var weekDay=$('#day-week').val();
			var currentDate=new Date();

		 if($('#payment-date').val() != ""){
				var payStartDate=new Date($('#payment-date').val());

			if($('select[id="paymentfrequency"]').val() == 'M'){
				 //if(currentDate.getMonth() < payStartDate.getMonth()){
					 payStartDate.setMonth(payStartDate.getMonth() - 1);
					 $('#payment-date').val($.datepicker.formatDate('mm/dd/yy', payStartDate));
				// }
			}

            if($('select[id="paymentfrequency"]').val() == 'S'){
            	//if(currentDate.getMonth() <= payStartDate.getMonth()){
            	if(semiCount == 0){
            		payStartDate.setDate(firstDay);
            		$('#payment-date').val($.datepicker.formatDate('mm/dd/yy', payStartDate));
            		if(secondDay==""){semiCount=2;}else{semiCount++;}
            	}else
            	if(semiCount == 1){
            		payStartDate.setDate(secondDay);
            		$('#payment-date').val($.datepicker.formatDate('mm/dd/yy', payStartDate));
            		semiCount++;
            	}
            	else
            	 if(semiCount>1){// && currentDate.getMonth() < payStartDate.getMonth()
            		 payStartDate.setMonth(payStartDate.getMonth() - 1);
					 $('#payment-date').val($.datepicker.formatDate('mm/dd/yy', payStartDate));
					 if(secondDay==""){semiCount=2;}else{semiCount=0;}
            	 }
            //	}
			}
            if($('select[id="paymentfrequency"]').val() == 'W' || $('select[id="paymentfrequency"]').val() == 'B'){
            	//if(currentDate <= payStartDate){
            	payStartDate.setDate(payStartDate.getDate()-7);
            	 $('#payment-date').val($.datepicker.formatDate('mm/dd/yy', payStartDate));
            	//}
            }
		 }
		}
		function changeMonthRight(){
			var firstDay=$('#first-day-month').val();
			var secondDay=$('#second-day-month').val();

			 if($('#payment-date').val() != ""){
				 var payStartDate=new Date($('#payment-date').val());

            if($('select[id="paymentfrequency"]').val() == 'M'){
            	 payStartDate.setMonth(payStartDate.getMonth() + 1);
            	 $('#payment-date').val($.datepicker.formatDate('mm/dd/yy', payStartDate));
			}
            if($('select[id="paymentfrequency"]').val() == 'S'){

            	if(semiCount == 0){
            		payStartDate.setDate(firstDay);
            		$('#payment-date').val($.datepicker.formatDate('mm/dd/yy', payStartDate));
            		if(secondDay==""){semiCount=2;}else{semiCount++;}
            	}else
            	if(semiCount == 1){
            		payStartDate.setDate(secondDay);
            		$('#payment-date').val($.datepicker.formatDate('mm/dd/yy', payStartDate));
            		semiCount++;
            	}
            	else
            	 if(semiCount>1){
            		 payStartDate.setMonth(payStartDate.getMonth() + 1);
					 $('#payment-date').val($.datepicker.formatDate('mm/dd/yy', payStartDate));
					 if(secondDay==""){semiCount=2;}else{semiCount=0;}
            	 }
			}
            if($('select[id="paymentfrequency"]').val() == 'W' || $('select[id="paymentfrequency"]').val() == 'B'){
                	payStartDate.setDate(payStartDate.getDate()+7);
                	 $('#payment-date').val($.datepicker.formatDate('mm/dd/yy', payStartDate));
            }
		 }

		}

		function loadPaymentReceipt(payId){
			$.fileDownload(downloadPdf + payId)
			 .fail(function () {
		  	  $("#file-download-error-msg").modal();
			 });
		}