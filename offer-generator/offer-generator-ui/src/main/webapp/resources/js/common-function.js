var _localeDictionaryUrl = "locale/lookup/?filterBy=#{filterBy}&value=#{value}";

/**
 * @param date (mm/dd/yyyy)
 * @param key defines payment date or date of birth field
 * @returns formatted date
 */
function formatDate(date, key){
	
    if(date.length == 8){
    	
    	var currentDate = new Date();
        var dateLast2 = date.substr(-2);
        
        var currentYear = currentDate.getFullYear().toString();
        
        var currentYearLast2 = currentYear.substr(-2);
        var currentYearFst2 = ((currentYear.substring(0, 2))*1);

        date = date.slice(0,(date.length-2));
        
        if(key == "dob" && dateLast2 > (currentYearLast2 * 1))
        	date = date + (currentYearFst2 - 1) + dateLast2;
        else
        	date = date + currentYearFst2 + dateLast2;

    }
    return date;
}

/** Locale Auto LookUp **/

function setLocaleAutoLookUp(lookUpField, cityField, stateField, countryField, zipcodeField, searchFieldName) {
	
	lookUpField.autocomplete({
		dataType : 'json',
		autoFocus : true,
		minLength : 1,
		
		source : function(request, response) {

			return $.ajax({
				type : 'GET',
				contentType : "application/json; charset=utf-8",
				url : serverContext + _localeDictionaryUrl.replace(/#\{filterBy\}/g, searchFieldName).replace(/#\{value\}/g, lookUpField.val()),
				dataType : 'json',
				success : function(data) {
					response($.map(data, function(c) {
						
						var returnDate = {
								cityName : c.cityName,
								stateCode : c.stateCode,
								countryCode : c.countryCode,
								zipcode : c.zipCode,
						      };
						
						if(searchFieldName == "CITY"){
							returnDate['label'] = c.cityName + " -  " + c.stateCode;
							returnDate['value'] = c.cityName;
						}
						if(searchFieldName == "STATE"){
							returnDate['label'] = c.stateCode;
							returnDate['value'] = c.stateCode;
					    }
						if(searchFieldName == "COUNTRY"){
							returnDate['label'] = c.countryCode;
							returnDate['value'] = c.countryCode;
						}
						if(searchFieldName == "ZIPCODE"){
							returnDate['label'] = c.zipCode + " -  " + c.cityName;
							returnDate['value'] = c.zipCode;
						}
						
						return returnDate;
					}));
				}
			});
		},

		select : function(event, ui) {
			if(cityField != null)
				cityField.val(ui.item.cityName);

			if(stateField != null)
				stateField.val(ui.item.stateCode);
			
			if(countryField != null)
				countryField.val(ui.item.countryCode);
			
			if(zipcodeField != null)
				zipcodeField.val(ui.item.zipcode);
			
			return true;
		}

	});
}

/** AJAX Setup **/
$.ajaxSetup({
	   beforeSend: function(request)
	    {
	    	 request.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
	    },

   statusCode:
    {
	     401: function (response) {
	         window.location.replace(homeUrl);
	      }
   }

});