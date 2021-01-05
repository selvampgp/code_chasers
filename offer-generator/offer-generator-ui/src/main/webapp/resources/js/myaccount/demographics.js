 var firstname;
 var middlename;
 var lastname;
 var address1;
 var address2;
 var cityname;
 var state;
 var zip;
 var mailaddress1;
 var mailaddress2;
 var mailcityid;
 var mailcityname;
 var mailstate;
 var mailzip;
 var contactno;
 var contactprefer;
 var alterphone;
 var altercontactprefer;
 var email;
 var _paperlessBilling;
 var ssn;
 var dob;
 var phoneauthorize;
 var mailauthorize;
 var employername;
// var employerId;
 var employerphonenumber;
 var updatedFields;

 $('#second_phone, #employer_phone, #primary_phone').mask("(000) 000-0000", {
	});
$('#ssn').mask("000-00-0000", {
	});
$('#dob').mask("00/00/0000", {
	});

function formatDob(){

    $('#dob').val(formatDate($('#dob').val(), "dob"));
}

/*$(function() {
	    $( "#dob" ).datepicker();
	    });*/

         $(document).ready(function() {
          $(".demographics").addClass('active').siblings().removeClass('active');
       	  $('#validation_message_div').hide();
         });


      /**  Form Validation  **/

       function formValidation() {
    		var i=0;
  		  var validation_repeat = [];

			var obj_rep;
			obj_rep = [ {
				"id" : "address1",
				"validate" : true,
				"message" : address1errormsg,
				"class" : "form-address1"
			} ,{
				"id" : "address2",
				"validate" : false,
				"message" : address2errormsg,
				"class" : "form-address2"
			},
			{
				"id" : "city",
				"validate" : true,
				"message" : cityerrormsg,
				"class" : "form-city"
			},
			{
				"id" : "street_state",
				"validate" : true,
				"message" : stateerrormsg,
				"class" : "form-street_state"
			},
			{
				"id" : "zipcode",
				"validate" : true,
				"message" : zipcodeerrormsg,
				"class" : "form-zipcode"
			},
			{
				"id" : "mail_state",
				"validate" : false,
				"message" : mailstateerrormsg,
				"class" : "form-mail_state"
			},
			{
				"id" : "mail_address1",
				"validate" : false,
				"message" : mailaddress1errormsg,
				"class" : "form-mail_address1"
			},
			{
				"id" : "mail_address2",
				"validate" : false,
				"message" : mailaddress2errormsg,
				"class" : "form-mail_address2"
			},
			{
				"id" : "mail_city",
				"validate" : false,
				"message" : mailcityerrormsg,
				"class" : "form-mail_city"
			},
			{
				"id" : "mail_zipcode",
				"validate" : false,
				"message" : mailzipcodeerrormsg,
				"class" : "form-mail_zipcode"
			},
			{
				"id" : "primary_phone",
				"validate" : false,
				"message" : priphoneerrormsg,
				"class" : "form-primary_phone"
			},
			{
				"id" : "primary_type",
				"validate" : false,
				"message" : pritypeerrormsg,
				"class" : "form-primary_type"
			},
			{
				"id" : "second_phone",
				"validate" : false,
				"message" : secphoneerrormsg,
				"class" : "form-second_phone"
			},
			{
				"id" : "second_type",
				"validate" : false,
				"message" : sectypeerrormsg,
				"class" : "form-second_type"
			},
			{
				"id" : "e-mail",
				"validate" : true,
				"message" : emailerrormsg,
				"class" : "form-email"
			},
			{
				"id" : "ssn",
				"validate" : false,
				"message" : ssnerrormsg,
				"class" : "form-ssn"
			},
			{
				"id" : "dob",
				"validate" : true,
				"message" : doberrormsg,
				"class" : "form-dob"
			},
			{
				"id" : "employer_name",
				"validate" : true,
				"message" : empnameerrormsg,
				"class" : "form-employer_name"
			},
			{
				"id" : "employer_phone",
				"validate" : false,
				"message" : empphoneerrormsg,
				"class" : "form-employer_phone"
			},
			];


				$.each(obj_rep, function(key, value) {
					var getId;
					var getcombotext;
					var getauthenticate;
					var getfielddata;
					if (obj_rep[key].validate) {

						getfielddata=$("#" + obj_rep[key].id).val();
						getId=obj_rep[key].id;

						  if(obj_rep[key].id == "address1")
							{
							  getcombotext=$('#address1').val();
							getfielddata=getcombotext;
							}

						  if(obj_rep[key].id == "address2")
							{
							  getcombotext=$('#address2').val();
							getfielddata=getcombotext;
							}

						  if(obj_rep[key].id == "city")
							{
							  getcombotext=$('#city').val();
							getfielddata=getcombotext;
							}


						  if(obj_rep[key].id == "street_state")
							{
							getcombotext=$('#street_state').val();
							getfielddata=getcombotext;
							}


						  if(obj_rep[key].id == "zipcode")
							{
							  getcombotext=$('#zipcode').val();
							getfielddata=getcombotext;
							}


						  if(obj_rep[key].id == "mail_state")
							{
							getcombotext=$('#mail_state').val();
							getfielddata=getcombotext;
							}


						  if(obj_rep[key].id == "mail_address1")
							{
							  getcombotext=$('#mail_address1').val();
							getfielddata=getcombotext;
							}


						  if(obj_rep[key].id == "mail_address2")
							{
							  getcombotext=$('#mail_address2').val();
							getfielddata=getcombotext;
							}


						  if(obj_rep[key].id == "mail_city")
							{
							  getcombotext=$('#mail_city').val();
							getfielddata=getcombotext;
							}


						  if(obj_rep[key].id == "mail_zipcode")
							{
							  getcombotext=$('#mail_zipcode').val();
							getfielddata=getcombotext;
							}


						  if(obj_rep[key].id == "primary_phone")
							{
							  getcombotext=$('#primary_phone').val();
							getfielddata=getcombotext;
							}


						  if(obj_rep[key].id == "primary_type")
							{
							getcombotext=$('#primary_type option:selected').text();
							getfielddata=getcombotext;
							}


						  if(obj_rep[key].id == "second_phone")
							{
							  getcombotext=$('#second_phone').val();
							getfielddata=getcombotext;
							}

						  if(obj_rep[key].id == "second_type")
							{
							getcombotext=$('#second_type option:selected').text();
							getfielddata=getcombotext;
							}

						  if(obj_rep[key].id == "e-mail")
							{
							  getcombotext=$('#e-mail').val();
							  getfielddata='0';
							  if($('#paperlessBilling').is(':checked') && (getcombotext == '' || getcombotext == null)){
								  getfielddata = '';
							  }
							}

						  if(obj_rep[key].id == "ssn")
							{
							  getcombotext=$('#ssn').val();
							getfielddata=getcombotext;
							}

						  if(obj_rep[key].id == "dob")
							{
							  getcombotext=$('#dob').val();
							  var today = new Date();
							  
							  if(getcombotext != '' && (getcombotext.length < 10 || ((getcombotext.substring(0, 2))*1) > 12) || ((getcombotext.substring(3, 5))*1) > 31){
								  getfielddata = '';
							  }
							  else if(getcombotext != '' && today < new Date(getcombotext)){
								  getfielddata = '';
								  obj_rep[key].message=dobGreatererrormsg;
							  }
							  else
								  getfielddata = '0';

							}

						  if(obj_rep[key].id == "employer_name")
							{
							  getcombotext=$('#employer_name').val();
							  if(getcombotext != ''){
								  getfielddata=getcombotext;
							  }else{
								  if($('#employer_phone').val() == ''){
									  getfielddata='0';
								  }else{
									  getfielddata='';
								  }
							  }

							}

						  if(obj_rep[key].id == "employer_phone")
							{
							getcombotext=$('#employer_phone').val();
							if(getcombotext != ''){
								if($('#employer_name').val() == ''){
									 obj_rep[key].message=empnameerrormsg;
									 getfielddata='';
								}else{
								getfielddata=getcombotext;
								}
							}else{
								getfielddata="0";
							}
							}

						if (getfielddata == "Select" || getfielddata=='' || getfielddata=='Type') {
							$("#" + obj_rep[key].id).css('border-color', 'red');
						//	$('#validation_message_div').append('<span style="color:red" id='+obj_rep[key].id+'_span>'+obj_rep[key].message+'</span><br/>');
							 $("." + obj_rep[key].class).html('<span style="color:red" id='+obj_rep[key].id+'_span>'+obj_rep[key].message+'</span>');
							 validation_repeat[i++] = false;
						} else {
							$("#" + obj_rep[key].id).css("border-color", '');
							$('#'+obj_rep[key].id+'_span').remove();
							validation_repeat[i++] = true;
						}
					}
				});
			return validation_repeat;
		}

		     function validate() {
		    	    var email = document.getElementById('e-mail').value;
		    	    var emailFilter =  /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		    	    if (!emailFilter.test(email)) {
		    	        $("#email").css('border-color', 'red');
		    	   	 $(".form-email").html('<span style="color:red" id=email_span>'+invalidemailformaterrormsg+'</span>');
						return false;
		    	    }
		    	    else
		    	    	{
		    	    	$("#email").css("border-color", '');
		    	    	$('#email_span').remove();
						return true;
		    	    	}
		    	}


      function getAddress(){

    	  if($('#sameas').is(":checked")){

    		 var address1=$('#address1').val() == "" ? null : $('#address1').val();
     		 var address2 =$('#address2').val() == "" ? null : $('#address2').val();
     		 var city =$('#city').val() == "" ? null : $('#city').val();
     		 var state =$('#street_state').val() == "" ? null : $('#street_state').val();
     		 var zip=$('#zipcode').val() == "" ? null : $('#zipcode').val();
     		 var hcity=$('#hid_city').val() == "" ? null : $('#hid_city').val();

     		 $('#mail_address1').val(address1);
     		 $('#mail_address2').val(address2);
     		 $('#mail_city').val(city);
     		 $('#hid_mail_city').val(hcity);
     		 $('select[id="mail_state"]').val(state);
     		 $('#mail_zipcode').val(zip);

    	  }
    	  else{

    		 $('#mail_address1').val(mailaddress1);
      		 $('#mail_address2').val(mailaddress2);
      		 $('#mail_city').val(mailcityname);
      		 $('#hid_mail_city').val(mailcityid);
      		 $('select[id="mail_state"]').val(mailstate);
      		 $('#mail_zipcode').val(mailzip);
    	  }

      }


      $(document).ready(function() {
    	  
    	  setLocaleAutoLookUp($("#city"), $("#city"), $("#street_state"), null, $("#zipcode"), "CITY");
    	  setLocaleAutoLookUp($("#street_state"), null, $("#street_state"), null, null, "STATE");
    	  setLocaleAutoLookUp($("#zipcode"), $("#city"), $("#street_state"), null, $("#zipcode"), "ZIPCODE");
    	  
    	  setLocaleAutoLookUp($("#mail_city"), $("#mail_city"), $("#mail_state"), null, $("#mail_zipcode"), "CITY");
    	  setLocaleAutoLookUp($("#mail_state"), null, $("#mail_state"), null, null, "STATE");
    	  setLocaleAutoLookUp($("#mail_zipcode"), $("#mail_city"), $("#mail_state"), null, $("#mail_zipcode"), "ZIPCODE");

/*    	$("#employer_name").autocomplete({
  	        dataType: 'json',
  	        autoFocus: true,
  	        minLength: 2,
  	        source: function (request, response) {
  	        	var getEmpdata=$("#employer_name").val();
  	            return $.ajax({
  	                type: 'GET',
  	                contentType: "application/json; charset=utf-8",
  	                url: empNameurl + getEmpdata,
  	                dataType: 'json',
  	                success: function (data) {
  	                    response($.map(data, function (c) {
  	                        return {
  	                            label: c.employername,
  	                            value: c.employername,
  	                               id: c.empId,
  	                        };
  	                    }));
  	                }
  	            });
  	        },

  	        select: function (event, ui) {
  	            $('#hid_emp_id').val(ui.item.id);
  	            return true;
  	        }

  	    });*/

      	 StateSuggestion();
      });

      function StateSuggestion(){
    			$.ajax({
    				type : 'GET',
    				url :   OnloadUrl,
    				 async:false,
    				datatype : 'json',
    				contentType: 'application/json',
    				success : function(response) {
    					var data = JSON.parse(response);
    					setContPrefData(data.contactpreference);
    					setProfileData(data.profile);
    				},
    				error : function(xhr, status, error) {
    					console.log(status);
    				}
    			});
    	 }

    	 function setProfileData(data){

    		  firstname=data.firstname;
    		  middlename=data.middlename;
    	      lastname=data.lastname;
    		  address1=data.address1;
    		  address2=data.address2;
    		  cityname=data.cityName;
    		  state=data.state;
    		  zip=data.zip;
    		  mailaddress1=data.mailaddress1;
    		  mailaddress2=data.mailaddress2;
    		  mailcityname=data.mailCityName;
    		  mailstate=data.mailstate;
    		  mailzip=data.mailzip;
    		  contactno=data.contact;
    		  contactprefer=data.contactprefer==0?'':data.contactprefer;
    		  alterphone=data.alternateContact;
    		  altercontactprefer=data.altercontactprefer==0?'':data.altercontactprefer;
    		  email=data.email;
    		  _paperlessBilling = data.paperlessBilling;
    		  ssn=data.ssn;
    		  dob=data.dobstr;
    		  phoneauthorize=data.authorize;
    		  mailauthorize=data.mailauthorize;
    		  employername=data.employername;
    		//  employerId=data.empId;
    		  employerphonenumber=data.employerphonenumber;
    		  updatedFields=data.updatedFields;

    		 $('#uniqueId').val(data.cpuId);
    		 $('#firstname_disabled').val(firstname);
    		 $('#middlename_disabled').val(middlename);
    		 $('#lastname_disabled').val(lastname);
    		 $('#firstname_spouse_disabled').val(data.spouseFirstname);
    		 $('#middlename_spouse_disabled').val(data.spouseMiddlename);
    		 $('#lastname_spouse_disabled').val(data.spouseLastname);
    		 $('#address1').val(address1);
    		 $('#address2').val(address2);
    		 $('#city').val(cityname);
    		 $('#street_state').val(state);
    		 $('#zipcode').val(zip);
    		 $('#mail_address1').val(mailaddress1);
    		 $('#mail_address2').val(mailaddress2);
    		 $('#mail_city').val(mailcityname);
    		 $('#mail_state').val(mailstate);
    		 $('#mail_zipcode').val(mailzip);
    		 $('#primary_phone').val(contactno);
    	     $('#primary_type').val(contactprefer);
    		 $('#second_phone').val(alterphone);
    	     $('#second_type').val(altercontactprefer);
    		 $('#e-mail').val(email);
    		 $('#ssn').val(ssn);
    		 $('#dob').val(dob);
    		 $('#employer_name').val(employername);
    		// $('#hid_emp_id').val(employerId);
    		 $('#employer_phone').val(employerphonenumber);
    		 if(phoneauthorize == 'Y'){
    			 $("#phoneauthorize").prop( "checked", true );
    		 }else{
    			 $("#phoneauthorize").prop( "checked", false );
    		 }
    		 if(mailauthorize == 'Y'){
    			 $("#mailauthorize").prop( "checked", true );
    		 }else{
    			 $("#mailauthorize").prop( "checked", false );
    		 }
    		 if(_paperlessBilling == 'Y'){
    			 $("#paperlessBilling").prop( "checked", true );
    		 }else{
    			 $("#paperlessBilling").prop( "checked", false );
    		 }
    		 
    		 maskProfileFields();

    	 }
    	 
    	 function maskProfileFields(){
    		 $('#second_phone, #employer_phone, #primary_phone').mask("(000) 000-0000", {
    			});
			 $('#ssn').mask("000-00-0000", {
				});
    	 }


    	function updateDemographics() {

    		var temp;
			var status = false;

    			var validation_repeat = formValidation();

    			if ($.inArray(false, validation_repeat) < 0) {

    					 status=true;

    			}

				var validateemail = true;

				if(document.getElementById('e-mail').value != "")
				{
					validateemail=validate();
				}else{
	    	    	$("#email").css("border-color", '');
	    	    	$('#email_span').remove();
				}


				if(validateemail && status==true)
					{
					var formfieldData = SaveandRetreive();

					restDemographicsave(formfieldData);
					}
			}


    	 function restDemographicsave(formfieldData){

				$.ajax({
					  type: 'POST',
					  url:  SaveDemo,
					  data: "{\"demographicsdata\":"+JSON.stringify(formfieldData)+"}",
			          processData: false,
			          async:false,
			          contentType: "application/json; charset=utf-8",
			          datatype: 'json',
					  success: function(data, textStatus, xhr){
						 //window.location.replace(pagereload);

							 
						 StateSuggestion();
						  $('#sameas').attr('checked', false);
								 $("#myModal").modal();



						},
			          error: function(xhr, status, error) {
			        	  maskProfileFields();
			        	  console.log(textStatus, errorThrown);
			        	}
					});
			}


    	 function  SaveandRetreive(){
    		 
 			$('#second_phone, #employer_phone, #primary_phone, #ssn').unmask();

    	//	 if($('#employer_name').val() == ""){$('#hid_emp_id').val("");}

    		 var uniqueId= $('#uniqueId').val() == "" ? null : $('#uniqueId').val();
    		 var firstname= $('#firstname_disabled').val() == "" ? null : $('#firstname_disabled').val();
    		 var middlename=$('#middlename_disabled').val() == "" ? null : $('#middlename_disabled').val();
    		 var lastname=$('#lastname_disabled').val() == "" ? null : $('#lastname_disabled').val();
    		 var address1=$('#address1').val() == "" ? null : $('#address1').val();
    		 var address2 =$('#address2').val() == "" ? null : $('#address2').val();
    		 var city =$('#city').val() == "" ? null : $('#city').val();
    		 var streetstatevalue=$('#street_state').val();
    		 var state = streetstatevalue == "0" ? null : streetstatevalue;
    		 var zip=$('#zipcode').val() == "" ? null : $('#zipcode').val();
    		 var mailaddress1=$('#mail_address1').val() == "" ? null : $('#mail_address1').val();
    		 var mailaddress2=$('#mail_address2').val() == "" ? null : $('#mail_address2').val();
    		 var mailcity=$('#mail_city').val() == "" ? null : $('#mail_city').val();
    		 var mailstatevalue=$('#mail_state').val();
    		 var mailstate = mailstatevalue == "0" ? null : mailstatevalue;
    		 var mailzip =$('#mail_zipcode').val() == "" ? null : $('#mail_zipcode').val();
    		 var contact =$('#primary_phone').val() == "" ? null : $('#primary_phone').val();
    		 var contacttypvalue=$('#primary_type option:selected').val();
    		 var contactprefer= contacttypvalue == "" ? null : contacttypvalue;
    		 var alternateContact=$('#second_phone').val() == "" ? null : $('#second_phone').val();
    		 var altercontacttypvalue=$('#second_type option:selected').val();
    		 var altercontactprefer=altercontacttypvalue == "" ? null : altercontacttypvalue;
    		 var email=$('#e-mail').val() == "" ? null : $('#e-mail').val();
    		 var paperlessBilling=$('#paperlessBilling').is(':checked') ? 'Y' : 'N';
    		 var ssn =$('#ssn').val() == "" ? null : $('#ssn').val();
    		 var dob =$('#dob').val() == "" ? null : $('#dob').val();
    		 var authorize=$('#phoneauthorize').is(':checked') ? 'Y' : 'N';
    		 var mailauthorize =$('#mailauthorize').is(':checked') ? 'Y' : 'N';
    		 var employername=$('#employer_name').val() == "" ? null : $('#employer_name').val();
    		 var employerphonenumber=$('#employer_phone').val() == "" ? null : $('#employer_phone').val();
             var updateField=setUpdatedFieldsValue();



		     var formData = [];

    			formData = {
                        "cpuId":uniqueId,
    					"firstname":firstname,
    					"middlename":middlename,
    					"lastname":lastname,
    					"address1":address1,
    					"address2":address2,
    					"cityName":city,
    					"state":state,
    					"zip":zip,
    					"mailaddress1":mailaddress1,
    					"mailaddress2":mailaddress2,
    					"mailCityName":mailcity,
    					"mailstate":mailstate,
    					"mailzip":mailzip,
    					"contact":contact,
    					"contactprefer":contactprefer,
    					"alternateContact":alternateContact,
    					"altercontactprefer":altercontactprefer,
    					"email":email,
    					"paperlessBilling":paperlessBilling,
    					"ssn":ssn,
    					"dobstr":dob,
    					"employername":employername,
    					"employerphonenumber":employerphonenumber,
    					"authorize":authorize,
    					"mailauthorize":mailauthorize,
    					"updatedFields":updateField
    			};
    			return formData;
    	 }

    	 function setUpdatedFieldsValue(){

    		 var addressCount=0;
    		 var mailAddressCount=0;
    		 var contactCount=0;
    		 var alterContactCount=0;
    		 var employerCount=0;
    		 var data;

    		 if(updatedFields != null){
    			 data=JSON.parse(updatedFields);
    		 }else{
    			 data={
    					 "dob":"Y",
    					 "ssn":"Y",
    					 "email":"Y",
    					 "paperlessBilling":"Y",
    					 "emailauthorize":"Y",
    					 "Address":"Y",
    					 "Mailingaddress":"Y",
    					 "ContactDetails":"Y",
    					 "AlterContactDetails":"Y",
    					 "EmployerDetails":"Y"
    					 }
    		 }
             var mail=$('#e-mail').val();
             if(email != "")
    		 if($('#e-mail').val() == ""){
    			 mail=null;
 			}
    		 if(mail != email){
    			 data.email="N";
     		}
    		 
     		var paperBilling=$('#paperlessBilling').is(':checked') ? 'Y' : 'N';
     		if(_paperlessBilling != paperBilling){
     			 data.paperlessBilling="N";
     		}

    		 var ss=$('#ssn').val();
    		 if(ssn != "")
    		 if($('#ssn').val() == ""){
    			 ss=null;
  			}
    		 if(ss != ssn){
    			 data.ssn="N";
      		}

    		 var birth=$('#dob').val();
    		 if(dob != "")
    		 if($('#dob').val() == ""){
    			 birth=null;
  			}
    		 if(birth != dob){
    			 data.dob="N";
      		}

    		var mailAuthorize=$('#mailauthorize').is(':checked') ? 'Y' : 'N';
     		if(mailAuthorize != mailauthorize){
     			 data.emailauthorize="N";
     		}


    		if(addressCount == 0){
    			var add1=$('#address1').val();
    			 if(address1 != "")
    			if($('#address1').val() == ""){
    				add1=null;
      			}
    		if(add1 != address1){
    			data.Address="N";
    			addressCount++;
    		}}

    		if(addressCount == 0){
    			var add2=$('#address2').val();
    			 if(address2 != "")
    			if($('#address2').val() == ""){
    				add2=null;
      			}
        		if(add2 != address2){
        			data.Address="N";
        			addressCount++;
        		}}
    		if(addressCount == 0){
    			var city=$('#city').val();
    			 if(cityname != "")
    			if($('#city').val() == ""){
    				city=null;
      			}
        		if(city != cityname){
        			data.Address="N";
        			addressCount++;
        		}}
    		if(addressCount == 0){
    			var streetstate=$('#street_state').val();
    			if(state != "")
        			if($('#street_state').val() == ""){
        				streetstate=null;
          			}
        		if(streetstate != state){
        			data.Address="N";
        			addressCount++;
        		}}
    		if(addressCount == 0){
    			var zipCode=$('#zipcode').val();
    			 if(zip != "")
    			if($('#zipcode').val() == ""){
    				zipCode=null;
      			}
        		if(zipCode != zip){
        			data.Address="N";
        			addressCount++;
        		}}

    		if(mailAddressCount == 0){
    			var mailAdd1=$('#mail_address1').val();
    			 if(mailaddress1 != "")
    			if($('#mail_address1').val() == ""){
    				mailAdd1=null;
    			}
        		if(mailAdd1 != mailaddress1){
        			data.Mailingaddress="N";
        			mailAddressCount++;
        		}}

    		if(mailAddressCount == 0){
    			var mailAdd2=$('#mail_address2').val();
    			 if(mailaddress2 != "")
    			if($('#mail_address2').val() == ""){
    				mailAdd2=null;
      			}
        		if(mailAdd2 != mailaddress2){
        			data.Mailingaddress="N";
        			mailAddressCount++;
        		}}

    		if(mailAddressCount == 0){
    			var mailCity=$('#mail_city').val();
    			 if(mailcityname != "")
    			if($('#mail_city').val() == ""){
    				mailCity=null;
      			}
        		if(mailCity != mailcityname){
        			data.Mailingaddress="N";
        			mailAddressCount++;
        		}}

    		if(mailAddressCount == 0){
    			var mailState=$('#mail_state').val();
    			 if(mailstate != "")
    			if($('#mail_state').val() == ""){
    				mailState=null;
      			}
        		if(mailState != mailstate){
        			data.Mailingaddress="N";
        			mailAddressCount++;
        		}}

    		if(mailAddressCount == 0){
    			var mailZip=$('#mail_zipcode').val();
    			 if(mailzip != "")
    			if($('#mail_zipcode').val() == ""){
    				mailZip=null;
      			}
        		if(mailZip != mailzip){
        			data.Mailingaddress="N";
        			mailAddressCount++;
        		}}

    		if(contactCount == 0){
    			var phone=$('#primary_phone').val();
    			 if(contactno != "")
    			if($('#primary_phone').val() == ""){
    				phone=null;
      			}
        		if(phone != contactno){
        			data.ContactDetails="N";
        			contactCount++;
        		}}

    		if(contactCount == 0){
    			var phone_type=$('#primary_type').val();
    			 if(contactprefer != "")
    	    			if($('#primary_type').val() == ""){
    	    				phone_type=null;
    	      			}
        		if(phone_type != contactprefer){
        			data.ContactDetails="N";
        			contactCount++;
        		}}

    		if(contactCount == 0){
    			var phoneAuthorize=$('#phoneauthorize').is(':checked') ? 'Y' : 'N';
        		if(phoneAuthorize != phoneauthorize){
        			data.ContactDetails="N";
        			contactCount++;
        		}}

    		if(alterContactCount == 0){
    			var alterPhone=$('#second_phone').val();
    			 if(alterphone != "")
    			if($('#second_phone').val() == ""){
    				alterPhone=null;
      			}
        		if(alterPhone != alterphone){
        			data.AlterContactDetails="N";
        			alterContactCount++;
        		}}

    		if(alterContactCount == 0){
    			var secondType=$('#second_type').val();
   			 if(altercontactprefer != "")
   			if($('#second_type').val() == ""){
   				secondType=null;
     			}
        		if(secondType != altercontactprefer){
        			data.AlterContactDetails="N";
        			alterContactCount++;
        		}}

    		if(employerCount == 0){
    			var empPhone=$('#employer_phone').val();
    			 if(employerphonenumber != "")
    			if($('#employer_phone').val() == ""){
    				empPhone=null;
      			}
        		if(empPhone != employerphonenumber){
        			data.EmployerDetails="N";
        			employerCount++;
        		}}
    		if(employerCount == 0){
    			var empName=$('#employer_name').val();
    			 if(employername != "")
    			if($('#employer_name').val() == ""){
    				empName=null;
      			}
        		if(empName != employername){
        			data.EmployerDetails="N";
        			employerCount++;
        		}}

    		return JSON.stringify(data);

    	 }

    	 function setContPrefData(data){

    		 var primary = $("#primary_type");
    		 primary.empty();
    		 primary.append($("<option></option>").attr("value", '').text(primaryTypePlaceholderText).attr("selected","selected"));
    		 for(i = 0; i < data.length; i++){
    			 primary.append($("<option></option>").attr("value", data[i].key).text(data[i].value));
    		 }

    		 var secondary = $("#second_type");
    		 secondary.empty();
    		 secondary.append($("<option></option>").attr("value", '').text(secondaryTypePlaceholderText).attr("selected","selected"));
    		 for(i = 0; i < data.length; i++){
    			 secondary.append($("<option></option>").attr("value", data[i].key).text(data[i].value));
    		 }

    	}

    	 function saveNewPassWord(){
    		var validation_repeat=validatePassword();
    		var status=true;
    		for(temp=0;temp<validation_repeat.length;temp++){

    			if(validation_repeat[temp]==false){

    				var status=false;
    			}
    		}
    		 if(status==true){
    			 finalSavePassword();
    		 }
    	 }

    	 function validatePassword(){

    		 var alpCount=0;
    		 var splCount=0;
    		 var numCount=0;
    		 var upperCount=0;

    		 var obj_rep;
    		 var j=0;
   		     var validation_repeat = [];

    		 obj_rep = [ {
 				"id" : "newPassword",
 				"validate" : true,
 				"message" : newpasserrormsg,
 				"class" : "form-new-password"
 			 },{
 				"id" : "confirmNewPassword",
 				"validate" : true,
 				"message" : oldpasserrormsg,
 				"class" : "form-confirm-new-password"
 			 }];

    			$.each(obj_rep, function(key, value) {

    				if (obj_rep[key].validate) {

    					getfielddata=$("#" + obj_rep[key].id).val();

    					if(obj_rep[key].id == "newPassword"){
   						    getfielddata=$('#newPassword').val();
    					}

                       if(obj_rep[key].id == "confirmNewPassword"){
        					if($('#confirmNewPassword').val()==''){
        						 getfielddata=$('#confirmNewPassword').val();
        					}else if($('#confirmNewPassword').val() != $('#newPassword').val()){

        						 obj_rep[key].message=samenewconfirmpasserrormsg;
                        		 getfielddata='';
        					}else{
        						var password=$('#confirmNewPassword').val();
        						var length=password.length;
        						for(var i=0;i<length;i++) {
        						if(/[A-Z]/.test(password.charAt(i))){
        							upperCount++;
        						}
        						if(/[A-Za-z]/.test(password.charAt(i))){
        							alpCount++;
        						}
        						if(/[0-9]/.test(password.charAt(i))){
        							numCount++;
        						}
        						if(/[$#@$!%*?&]/.test(password.charAt(i))){
        							splCount++;
        						}
        						}
        						if(alphabetsCount>alpCount){
        							 obj_rep[key].message=alphabetsCounterrormsg.replace("[[replaceCount]]", alphabetsCount);
                            		 getfielddata='';
        						}else if(uppercaseCount>upperCount){
        							 obj_rep[key].message=uppercaseCounterrormsg.replace("[[replaceCount]]", uppercaseCount);
                        		 getfielddata='';
                        		 }else if(numericsCount>numCount){
                        			 obj_rep[key].message=numericsCounterrormsg.replace("[[replaceCount]]", numericsCount);
                            		 getfielddata='';
        						}else if(splcharCount>splCount){
        							 obj_rep[key].message=splcharCounterrormsg.replace("[[replaceCount]]", splcharCount);
                        		 getfielddata='';
    						}
        					}
    					}

    					 if (getfielddata=='' || getfielddata == "Country" ) {
                        	 $("#" + obj_rep[key].id).css('border-color', 'red');
                        	 $("." + obj_rep[key].class).html('<span style="color:red" id='+obj_rep[key].id+'_span>'+obj_rep[key].message+'</span>');
                        	 validation_repeat[j++] = false;
    						} else {
    							$("#" + obj_rep[key].id).css("border-color", '');
    						    $("." + obj_rep[key].class).html('');
    						    validation_repeat[j++] = true;
    						}
    			    	}

    			});

    		 return validation_repeat;
    	 }

    	 function finalSavePassword(){


    		$.ajax({
				  type: 'POST',
				  url:  savePassword,
				  data: $('#confirmNewPassword').val(),
		          processData: false,
		          async:false,
		          contentType: "application/json; charset=utf-8",
		          datatype: 'json',
				  success: function(data, textStatus, xhr){
					  $("#newPassword").val("");
					  $("#confirmNewPassword").val("");
					  $("#passwordStatusModal").modal();
					},
		          error: function(xhr, status, error) {

		        	  console.log(textStatus, errorThrown);
		        	}
				});
    	 }
