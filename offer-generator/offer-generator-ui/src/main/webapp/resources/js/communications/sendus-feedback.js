			//<![CDATA[
			/**
			* Form Validation
			**/

   $(document).ready(function() {
	   $.ajax({
				type 	 : 'GET',
				url 	 :   getFeedbackRateUrl,
				datatype : 'json',
				 async:false,
				contentType: 'application/json',
				success  : function(response) {
					for (i = 0; i < response.length; i++) {
					    var radioBtn = $(" <li><div class='radio-btn'><input type='radio' name='option_name' class='feedback' id="+response[i].cppKeyvalue+" value='"+response[i].cppVarDefValue+"' />"+response[i].cppVarDefValue+"</div></li>");
					    radioBtn.appendTo('#targetRadioButton');
					}
					 	},
			error : function(xhr, status, error) {
					console.log(status);
				}
			});

   });

	    function formValidation() {
			var validation_repeat = true;
			var obj_rep;
			obj_rep = [
			 {
			    "name" : "feedback",
				"validate" : true,
				"message" : "*Select any FeedBack Option",
				"class" : "form-feed_back_option"
			 },
			];

				$.each(obj_rep, function(key, value) {
					var getId;
					var getfielddata;
					if (obj_rep[key].validate) {

							  validation_repeat=validateRadiooptions(obj_rep[key].id,obj_rep[key].message);

					}
				});

			return validation_repeat;
		}



		function validateRadiooptions(id,message){
			 var validation_repeat = true;
			 var checked_option_radio = $("input[name='option_name']:checked").val();


			 if(checked_option_radio===null || checked_option_radio===undefined || checked_option_radio ===''){
				 $('input[type=radio]').css({'outline': '1px solid red'});
				// $('#form-feed_back_option').append('<span style="color:red" id='+id+'_span>'+message+'</span><br/>');
				$("." + obj_rep[key].class).html('<span style="color:red" >'+message+'</span>');
				 validation_repeat = false;
			 }
			 else{
				 $('input[type=radio]').css({'outline': ''});
				// $('#'+id+'_span').remove();
			 }
			 return validation_repeat;
		 }



		 /**
		 * Save Function Starts Here
		 **/

		 function getFeedback(){
			 var status= formValidation();
			 var feed_back_option=$('input[name=option_name]:checked').attr('id');
			 var feed_back_value=$('input[name=option_name]:checked').val();
			 var feed_back_text=$('#feed_back_text').val();
		if(status==true){
			 var formData = [];
			 formData = {
					 "cfbRatingId":feed_back_option,
					 "cfbRating":feed_back_value,
					 "cfbFeedback":feed_back_text
			 };

			 restFeedBackSave(formData);
		 }
		 }


		 function restFeedBackSave(formfieldData){

			 $.ajax({
				  type: 'POST',
				  url:  feedbacksaveurl,
				  async:false,
				  data: "{'feedbackoptions':"+JSON.stringify(formfieldData)+"}",
		          processData: false,
		          contentType: "application/json; charset=utf-8",
		          datatype: 'json',
				  success: function(data, textStatus, xhr){
					  if(data==true){
						  $("#feed_back_text").val("");
						  $("input:radio").attr("checked", false);
						  $("#myModal").modal();
					  }



					},
		          error: function(xhr, status, error) {
		        	  console.log(textStatus, errorThrown);
		        	}
				});
			}
			//]]>
