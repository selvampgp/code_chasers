
        var monthNames = ["January", "February", "March", "April", "May", "June",
                          "July", "August", "September", "October", "November", "December"
                        ];
        var monthShortNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
                          "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
                        ];

        $(document).ready(function(){
        	$.ajax({
        		type : 'GET',
    			url :   accdetailpath,
    			 async:false,
    			datatype : 'json',
    			contentType: 'application/json',
    			success : function(response) {
    				var data = JSON.parse(response);
    				accountdetails(data.accdetail);
    				setPaymentMethod(data.paymentMethod);
    			},
    			error : function(xhr, status, error) {
    				console.log(status);
    			}
        	});
        	   $('#todayspayment').number(true, 2);
        	   $('#recurringamount').number(true, 2);
        	   $('#totaldue').number(true, 2);
        	   $('#interestrate').number(true, 2);
        	   $('#monthstopay').number(true);
        });

        $(document).ready(function(){
        	 var getmonth = $("#pay-month");
        	 var getyear = $("#pay-year");
        	 getmonth.empty();
        	 getmonth.append($("<option class='option' selected='selected'></option>").attr("value", "Month").text(monthPlaceHolderText));
        	 for(i = 1; i<10; i++){
        		 getmonth.append($("<option class='option'></option>").attr("value", i).text("0"+i+"-"+monthShortNames[i-1]));
     		 }
        	 for(i = 10; i<=12; i++){
        		 getmonth.append($("<option class='option'></option>").attr("value", i).text(i+"-"+monthShortNames[i-1]));
     		 }
        	 getyear.empty();
        	 getyear.append($("<option class='option' selected='selected'></option>").attr("value", "Year").text(yearPlaceHolderText));
        	 var date=new Date();
        	 for(i = 1; i<=10; i++){
        		 getyear.append($("<option class='option'></option>").attr("value", date.getFullYear()).text(date.getFullYear()));
        		 date.setFullYear( date.getFullYear() + 1 );
     		 }

        });

        function changeMonthYear(){
        	var getNoOfMonths=$('#monthstopay').val();
        	
        	if(getNoOfMonths != ""){
        		var date=new Date();
            	date.setMonth(date.getMonth() + Math.abs(getNoOfMonths));
            	$("#pay-month").val(date.getMonth()+1);
            	$("#pay-year").val(date.getFullYear());
            	$("#pay-month").attr('disabled', 'disabled');
            	$("#pay-year").attr('disabled', 'disabled');
        	}else{
        		$("#pay-month").val("Month");
            	$("#pay-year").val("Year");
            	$("#pay-month").removeAttr('disabled');
            	$("#pay-year").removeAttr('disabled');
        	}
        }
         function changemonthsbyyear(){
        	 var date=new Date();
        	 var nextDate=new Date();
        	 var selectedMonth=$('select[id="pay-month"]').val()-1;
         	var selectedYear=$('select[id="pay-year"]').val();
         	
         	/*if($('select[id="pay-month"]').val() == 'Month' && selectedYear == 'Year'){
         		$('#monthstopay').removeAttr('disabled');
         	}else{
         		$("#monthstopay").attr('disabled', 'disabled');
         	}*/
        	nextDate.setMonth(Math.abs(selectedMonth));
        	nextDate.setFullYear(Math.abs(selectedYear));
        	$('#monthstopay').val(monthDiff(date, nextDate));
         }


        function accountdetails(accdetail){
        	if(accdetail.acoTotalDue !='' || accdetail.acoTotalDue !='NULL'){
        		$("#totaldue").val(accdetail.acoTotalDue);
        	}
        	if(accdetail.interestRate !='' || accdetail.interestRate !='NULL'){
        		$("#interestrate").val(accdetail.interestRate);
        	}
      }
        function setPaymentMethod(data){

     		 var getelement = $("#paymentfrequency");
     		 getelement.empty();
     		 getelement.append($("<option class='option' selected='selected'></option>").attr("value", "").text(paymentFreqPlaceHolderText));
     		 for(i = 0; i < data.length; i++){
     			 if(data[i].value != "O"){
     			 getelement.append($("<option class='option'></option>").attr("value", data[i].value).text(data[i].description));
     			 }
     		 }

     	}


        function formValidation() {
	    	var i=0;
		  var validation_repeat = [];
			var obj_rep;
			obj_rep = [ {
				"id" : "totaldue",
				"validate" : true,
				"message" : totaldueerrormsg,
				"class" : "form-totaldue"
			} ,{
				"id" : "interestrate",
				"validate" : true,
				"message" : interesterrormsg,
				"class" : "form-interestrate"
			} ,{
				"id" : "todayspayment",
				"validate" : true,
				"message" : todaysamounterrormsg,
				"class" : "form-todayspayment"
			} ,{
				"id" : "recurringamount",
				"validate" : true,
				"message" : recurringerrormsg,
				"class" : "form-recurringamount"
			} ,{
				"id" : "paymentfrequency",
				"validate" : true,
				"message" : payfreqerrormsg,
				"class" : "form-paymentfrequency"
			},
			{
				"id" : "monthstopay",
				"validate" : true,
				"message" : monthtopayerrormsg,
				"class" : "form-monthstopay"
			},
			{
				"id" : "pay-month",
				"validate" : true,
				"message" : paymontherrormsg,
				"class" : "form-pay-month"
			},
			{
				"id" : "pay-year",
				"validate" : true,
				"message" : payyearerrormsg,
				"class" : "form-pay-year"
			},
			];


			$.each(obj_rep, function(key, value) {
				var getId;
				var getcombotext;
				var getfielddata;

				if (obj_rep[key].validate) {
					getfielddata=$("#" + obj_rep[key].id).val();
					getId=obj_rep[key].id;

					if(obj_rep[key].id == "totaldue"){
                		getcombotext=$('#totaldue').val();
						getfielddata=getcombotext;

				 }
					if(obj_rep[key].id == "interestrate"){
                		getcombotext=$('#interestrate').val();
						getfielddata=getcombotext;

				 }

					if(obj_rep[key].id == "todayspayment"){
						 getcombotext=$('#todayspayment').val();
						 if(getcombotext != ''){
							 if(parseFloat(getcombotext) > $('#totaldue').val()){
								 obj_rep[key].message=payamountgreatererrormsg;
								 getfielddata='';
							 }else{
								 getfielddata=getcombotext;
							 }
						 }else{
						 getfielddata="0";
						 }
					 }

					 if(obj_rep[key].id == "recurringamount"){

						 getcombotext=$('#recurringamount').val();
						 if(getcombotext != ''){
							 if(parseFloat(getcombotext)>$('#totaldue').val()){
								 obj_rep[key].message=recurringamountgreatererrormsg;
								 getfielddata='';
							 }else if($('#todayspayment').val() != '' && parseFloat($('#todayspayment').val())+parseFloat(getcombotext)>$('#totaldue').val()){
								 obj_rep[key].message=sumofamountgreatererrormsg;
								 getfielddata='';
							 }else if(getcombotext == "0"){
								 getfielddata='';
							 }
							 else{
								 getfielddata=getcombotext;
							 }
						 }else{
						 getfielddata="0";
						 }

					 }
                     if(obj_rep[key].id == "paymentfrequency"){
                    		getcombotext=$('#paymentfrequency option:selected').val();
							getfielddata=getcombotext;

					 }
                     if(obj_rep[key].id == "monthstopay"){
						 getcombotext=$('#monthstopay').val();
						 if(getcombotext != ''){
							 if($('#recurringamount').val() != ''){
								 obj_rep[key].message=enteranyonefielderrormsg;
								 getfielddata='';
							 }else if(getcombotext == "0"){
								 getfielddata='';
							 }else{
								 getfielddata=getcombotext;
							 }
						 }else{
							 if($('#recurringamount').val() != ''){
							 	 getfielddata="0";
							 }else{
								 obj_rep[key].message=enteratleastonefielderrormsg;
								 getfielddata='';
							 }

						 }

					 }	// Year

                    if(obj_rep[key].id == "pay-month"){
                    	 getcombotext=$('#pay-month').val();
                    	 if(getcombotext != 'Month'){
                    		 if($('#pay-year').val() != 'Year'){
                    			 getfielddata=getcombotext;
                    		 }else{
                    			 obj_rep[key].message=selectyearfielderrormsg;
                    			 getfielddata='';
                    		 }
                    	 }else{
                    		 getfielddata=getcombotext;
                    	 }
                     }

                     if(obj_rep[key].id == "pay-year"){
                    	 getcombotext=$('#pay-year').val();
                    	 if(getcombotext != 'Year'){
                    		 if($('#pay-month').val() != 'Month'){
                    			 getfielddata=getcombotext;
                    		 }else{
                    			 obj_rep[key].message=selectmonthfielderrormsg;
                    			 getfielddata='';
                    		 }
                    	 }else{
                    		 getfielddata=getcombotext;
                    	 }
                     }

                     if (getfielddata=='') {
                    	 $("#" + obj_rep[key].id).css('border-color', 'red');
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

       function getPayFreq(){
		var selectedFreq=$('select[id="paymentfrequency"]').val();
        if(selectedFreq=="M"){

        	return "12";
        }
        if(selectedFreq=="S"){

        	return "24";
        }
        if(selectedFreq=="W"){

        	return "56";
        }
        if(selectedFreq=="B"){

        	return "23";
        }
		}

       function setSessionValue(){
    	   $.ajax({
				type:'GET',
				 url:setSessionValues + $('#totaldue').val() +"/"+ $('#interestrate').val(),
				 datatype : 'json',
				 contentType: 'application/json',
				 success : function(response) {

				 },
				error : function(xhr, status, error) {

				 }
			 });

       }


        function calculateNow(){
        	setSessionValue();
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
		    $('#calculate-now').attr('data-target','#myModal');
        	var totalDue=$("#totaldue").val();
        	var RateofInterest=$("#interestrate").val();
        	var onetymamt=$("#todayspayment").val();
        	var monthlypayment=$("#recurringamount").val();
        	var paymentfrequency=getPayFreq();
        	var monthstopay=$("#monthstopay").val();

        	if(onetymamt != ""){
        		totalDue=Math.abs(totalDue)-onetymamt;
        		$('#one-pay-amount').text($("#todayspayment").val());
        	}else{
        		$('#one-pay-amount').text("0.0");
        	}

        	 $('#pay-freq').text($("#paymentfrequency option:selected").text());
        	 if(monthlypayment !="" && monthlypayment !="-"){
            	var getmonthofpays=nper(RateofInterest,paymentfrequency,monthlypayment,"-"+totalDue,0);
            	if (isNaN(getmonthofpays)) {
            		$('#interestrate').val(0);
            		getmonthofpays=nper(0,paymentfrequency,monthlypayment,"-"+totalDue,0);
            	}
            	TotalPrincipalInterest(totalDue,getmonthofpays,monthlypayment);
            	 setMonthsAndEstimatedDate(getmonthofpays);
             }else{
            	$('#no-of-month').text(monthstopay);
            	setMonthsAndEstimatedDate(monthstopay);
             }


            	if(monthstopay !="" && monthstopay !="-"){
            	var paymentbymonth=pmt(RateofInterest, paymentfrequency, monthstopay, "-"+totalDue, 0);
            	var fs=Math.abs(paymentbymonth).toFixed(2);
                $('#pay-amount').text("$"+fs);
                TotalPrincipalInterest(totalDue,monthstopay,paymentbymonth);

               }else{
            	   $('#pay-amount').text("$"+Math.abs(monthlypayment).toFixed(2));
               }


			}else{
				$('#calculate-now').attr('data-target','');
			}

        }

        function setMonthsAndEstimatedDate(getmonthofpays){

        	var CurrentDate = new Date();
    		if($('select[id="paymentfrequency"]').val() == "M"){
    			 $('#no-of-month').text(Math.ceil(Math.abs(getmonthofpays)));
    	    CurrentDate.setMonth(CurrentDate.getMonth() + Math.ceil(Math.abs(getmonthofpays)));
    	    $('#estimated-date').text(monthNames[CurrentDate.getMonth()]+" "+CurrentDate.getFullYear());
    		}
    		if($('select[id="paymentfrequency"]').val() == "S"){

    			var semiMonth=Math.ceil(Math.abs(getmonthofpays))/2;
    			 $('#no-of-month').text(Math.ceil(semiMonth));
    			 CurrentDate.setMonth(CurrentDate.getMonth() + Math.ceil(semiMonth));
         	    $('#estimated-date').text(monthNames[CurrentDate.getMonth()]+" "+CurrentDate.getFullYear());
    		}
    		if($('select[id="paymentfrequency"]').val() == "W"){
    			var weekly=Math.ceil(Math.abs(getmonthofpays))*7;
    			 var wDate=new Date();
    			 CurrentDate.setDate(CurrentDate.getDate() + Math.ceil(weekly));
    			 $('#no-of-month').text(monthDiff(wDate,CurrentDate));
    			  $('#estimated-date').text(monthNames[CurrentDate.getMonth()]+" "+CurrentDate.getFullYear());
    		}
    		if($('select[id="paymentfrequency"]').val() == "B"){
    			var weekly=Math.ceil(Math.abs(getmonthofpays))*14;
    			 var bwDate=new Date();
    			 CurrentDate.setDate(CurrentDate.getDate() + Math.ceil(weekly));
    			 $('#no-of-month').text(monthDiff(bwDate,CurrentDate));
    			  $('#estimated-date').text(monthNames[CurrentDate.getMonth()]+" "+CurrentDate.getFullYear());
    		}
        }

        function monthDiff(d1, d2) {
            var months;
            months = (d2.getFullYear() - d1.getFullYear()) * 12;
            months -= d1.getMonth() + 1;
            months += d2.getMonth()+1;

            return months <= 0 ? 0 : months;
        }

        function TotalPrincipalInterest(totalDue,monthstopay,paymentbymonth){
        	var grandtotal=Math.abs(monthstopay)*Math.abs(paymentbymonth);
        	$('#grand-total').text("$"+grandtotal.toFixed(2));
        	var totalInterest=grandtotal-Math.abs(totalDue);
         //	var totalInterest=parseInt(parseInt(totalBalance.toFixed(2)) - parseInt(totalDue.toFixed(2))).toFixed(2);

        	$('#total-interest').text("$"+totalInterest.toFixed(2));
        	var totalBalance=grandtotal-totalInterest;
        	$('#total-balance').text("$"+totalBalance);


        }

        function nper(rate, per, pmt, pv, fv)
        {
        fv = parseFloat(fv);
        pmt = parseFloat(pmt);
        pv = parseFloat(pv);
        per = parseFloat(per);

        if (( per == 0 ) || ( pmt == 0 )){
        alert("Why do you want to test me with zeros?");
        return(0);
        }
        rate = eval((rate)/(per * 100));

        if ( rate == 0 ) // Interest rate is 0
        {
        nper_value = - (fv + pv)/pmt;
        }
        else
        {
        nper_value = Math.log((-fv * rate + pmt)/(pmt + rate * pv))/ Math.log(1 + rate);
        }

       // nper_value = conv_number(nper_value,2);
        return (nper_value);
        }

        function pmt(rate, per, nper, pv, fv)

        {

        fv = parseFloat(fv);

        nper = parseFloat(nper);

        pv = parseFloat(pv);

        per = parseFloat(per);

        if (( per == 0 ) || ( nper == 0 )){

        alert("Why do you want to test me with zeros?");

        return(0);

        }

        rate = eval((rate)/(per * 100));

        if ( rate == 0 ) // Interest rate is 0

        {

        pmt_value = - (fv + pv)/nper;

        }

        else

        {

        x = Math.pow(1 + rate,nper);

        pmt_value = -((rate * (fv + x * pv))/(-1 + x));

        }

      //  pmt_value = conv_number(pmt_value,2);

        return (pmt_value);

        }

      /*  function conv_number(expr, decplaces)
        {
        var str = "" + Math.round(eval(expr) * Math.pow(10,decplaces));
        while (str.length <= decplaces) {
        str = "0" + str;
        }
        var decpoint = str.length - decplaces;
        return (str.substring(0,decpoint) + "." + str.substring(decpoint,str.length));
        }*/

       function clearData(){
  		  /*$('#todayspayment').val("");
  		  $("#paymentfrequency").val("Please Select Frequency");
  		  $('#recurringamount').val("");
  		 $('#monthstopay').val("");	*/
  		   $('#myModal').modal('toggle');
         }

    /*    function setStaticValues(){
        	localStorage.setItem('totalDue', $('#totaldue').val());
        	localStorage.setItem('interestRate', $('#interestrate').val());
        }*/
