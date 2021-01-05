
      var branchstates;
      var officename;
      var geocoder, map, service, infowindow, bounds;

	$(document).ready(function() {
		    $(".communications").addClass('active').siblings().removeClass('active');
		    $(".contactdetails").addClass('active').siblings().removeClass('active');

		    StateSuggestion();
	});


	function StateSuggestion(){
		$.ajax({
			type : 'GET',
			url :   pageloadstate,
			 async:false,
			datatype : 'json',
			contentType: 'application/json',
			success : function(response) {
				var data = JSON.parse(response);
				setStateData(data.lstbranches);
			},
			error : function(xhr, status, error) {
				console.log(status);
			}
		});
     }

	function setStateData(data){
		 branchstates=data;
		 var streetstate = $("#cntstate");
		 streetstate.empty();
		 for(i = 0; i < data.length; i++){
			 streetstate.append($("<option></option>").attr("value", data[i].cbm_id).text(data[i].cbm_title));
		 }
		 populatestatedetail();
	}


	function populatestatedetail(){
		var streetstateid = $('#cntstate option:selected').val();
		BranchTimings(streetstateid);
	}

	function BranchTimings(streetstateid){
		$.ajax({
			type : 'GET',
			url :   branchtiming+streetstateid,
			 async:false,
			datatype : 'json',
			contentType: 'application/json',
			success : function(response) {
				var data = JSON.parse(response);
				populatetimings(data.branchdetail,streetstateid);
			},
			error : function(xhr, status, error) {
				console.log(status);
			}
		});
     }

	function populatetimings(branchdetails,streetstateid){
		var timingsobject=$('#timings');
		var addressobject=$('#address');
		var phoneobject=$('#phoneNo');
		var emailobject=$('#email');
		timingsobject.empty();
		addressobject.empty();
		phoneobject.empty();
		emailobject.empty();

		var address="<p>" + (branchdetails.cbmAddress1 != null ? branchdetails.cbmAddress1 : "") 
		                  + (branchdetails.cbmAddress2 != null ?  ", " + branchdetails.cbmAddress2 : "")
		                  +"<br/> "+ (branchdetails.cbmCityname != null ? branchdetails.cbmCityname : "") 
		                  +", "+ (branchdetails.cbmStatecode != null ? branchdetails.cbmStatecode : "")
		                  +" "+(branchdetails.cbmZipcode != null ? branchdetails.cbmZipcode : "")+"</p>";

		var gettimings=branchdetails.cbm_timings;
		for(i = 0; i < gettimings.length; i++){
		timingsobject.append("<p>"+gettimings[i].timing+"</p>");
		 }

	//	if(streetstateid == 1){
		var phoneNo=branchdetails.cbmPhoneno == null ? [] : branchdetails.cbmPhoneno.split(",");

		var formPhoneNo=springFieldPhoneNoText.replace("[[replaceHospitalsNo]]", phoneNo[0] == undefined ? "-" : phoneNo[0]).replace("[[replaceCourtNo]]", phoneNo[1] == undefined ? "-" : phoneNo[1]).replace("[[replaceFinancialNo]]", phoneNo[2] == undefined ? "-" : phoneNo[2]);

			phoneobject.append(formPhoneNo);
		/*}else{
			phoneobject.append(branchdetails.cbmPhoneno);
		}*/

		addressobject.append(address);
		emailobject.append(branchdetails.cbmMailid);
		addresslocator(branchdetails);
	}

  function addresslocator(branchdetails){

		var geocoder =  new google.maps.Geocoder();

	 	var  formAddress=branchdetails.cbmCompany+","+branchdetails.cbmAddress1+", "+branchdetails.cbmAddress2+", "+branchdetails.cbmCityname;
	 	officename=branchdetails.cbmCompany;

       geocoder.geocode(
    		{
    	    'address': formAddress},
            function(results, status) {
         if (status == google.maps.GeocoderStatus.OK) {

           success(results[0].geometry.location.lat(),results[0].geometry.location.lng(),formAddress);

           callback(results, status);
         } else {
           alert("Invalid Address " + status);
         }
       });
	}

	function success(lat,lng,formAddress){
		 map = new google.maps.Map(
				    document.getElementById("map_canvas"), {
				        center: new google.maps.LatLng(lat,lng),
				        zoom: 10,
				        mapTypeId: google.maps.MapTypeId.ROADMAP
				    });
				    var request = {
				    query: formAddress
				    }
				    infowindow = new google.maps.InfoWindow();
				    //service = new google.maps.places.PlacesService(map);
				    bounds = new google.maps.LatLngBounds();
				//service.textSearch(request, callback);
	}


	function callback(results, status) {
	  if (status == google.maps.GeocoderStatus.OK) {
	    for (var i = 0; i < results.length; i++) {
	      var place = results[i];
	      var marker = createMarker(results[i]);
	      bounds.extend(marker.getPosition());
	    }
	    //map.setCenter(bounds.getCenter());
	   // map.fitBounds(bounds);
	   map.setZoom(17);
	  }
	}
	function createMarker(place) {
	  var placeLoc = place.geometry.location;

	  var marker = new google.maps.Marker({
	    map: map,
	   icon: markerIcon,
	    animation: google.maps.Animation.DROP,
	    position: placeLoc
	  });

	 // google.maps.event.addListener(marker, 'dblclick', function() {

	    infowindow.setContent(officename);
	    infowindow.open(map, marker);
	//  });
	  return marker;
	}