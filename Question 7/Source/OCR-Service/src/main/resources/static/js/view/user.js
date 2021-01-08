var type="";

function show(id){
	debugger;
	$('#choosefile').val("");
	$('#choosefile1').val("");
	type = $('#'+id).attr("data-attr");
	
	if(type=="PANCARD"){
		$('.pandiv').show();
		$('.maindiv').hide();
		$('.passdiv').hide();
	}else if(type=="PASSPORT"){
		$('.pandiv').hide();
		$('.maindiv').hide();
		$('.passdiv').show();
	}else if(type=="back"){
		$('.pandiv').hide();
		$('.maindiv').show();
		$('.passdiv').hide();
	}
	
}

var contryValue="";
function uploadFile(id){
	if(document.getElementById(id).files.length == 0){
		alert("Select a document to upload","W");
	}else{
		if(type=="PASSPORT"){
			if($('#contry').val() =="0"){
				alert("Select a contry","W");
				return false;
			}
			contryValue=$('#contry').val();
		}
		  input = document.getElementById(id);
	   
	      var oMyForm = new FormData();
	      oMyForm.append("file", input.files[0]);

	      $.ajax({
	          url: _URL+"?type="+type+"&country="+contryValue,
	          data: oMyForm,
	          dataType: 'text',
	          processData: false,
	          contentType: false,
	          async: false,
	          type: 'POST',

	          success: function (response) {

	        	  if(response!=null && response!="null" &&  !jQuery.isEmptyObject(response)){
	        		//  modals.docUpload.close();
	        		  alert(response);
	              }
	          },
	          error:function(e){
	        	  if(e.responseText!=null)
	        		  alert(e.responseText,"D");
	          }
	      	

	      });
	
	}
}
