var alreadySelectedId = [];
var currencySymbol = "<span class=inner-dollarSign>$</span>";

		$(document).ready(function(){

			$.ajax({
				type:'GET',
				 url:accountsummary,
				 datatype : 'json',
				 async:false,
 				 contentType: 'application/json',
 				 success : function(response) {
 					 try{
 					     populateAccountSummary(response.accountsummarydetails);
 					     toogleAccountTable(response.accountsummarydetails);
 					     
 					     populateJudgementDetails(response.judgementDetails);
 					     
 					 }catch(e){
 						 var EmptyRowContent="<tr><td colspan=\"7\" style='height:9px;text-align:center;'><b>"+tableEmptyMessage+"</b></td></tr>";
 	 		        	 $("#tblaccounts #accountdetailsbody").append(EmptyRowContent);
 					 }
 				 },
 				error : function(xhr, status, error) {
 					 var EmptyRowContent="<tr><td colspan=\"7\" style='height:9px;text-align:center;'><b>"+tableEmptyMessage+"</b></td></tr>";
 		        	 $("#tblaccounts #accountdetailsbody").append(EmptyRowContent);
 				 }
			 });

		    $('#makePaymentBtn').click(function(){
		    	window.location.href=paymentUrl;
		    	return false;
		    })

		    $('#historyBtn').click(function(){
		    	window.location.href=historyUrl;
		    	return false;
			})

		});

		$(document).ready(function(){
			$.ajax({
				type:'GET',
				 url:paymentDetails,
				 async:false,
				 datatype : 'json',
				 contentType: 'application/json',
				 success : function(response) {
					var data = JSON.parse(response);
					loadConsumerDetails(data.consumeraccountdetails);
				 },
	 				error : function(xhr, status, error) {

	 				 }

			 });
			});

		function loadConsumerDetails(consumeraccountdetails){
			$("#accountbalance").text(consumeraccountdetails.accoutbalance);
			$("#currentbalance").text(balanceAsOf+" "+consumeraccountdetails.currentDate);

			if(consumeraccountdetails.lastpaymentdate != null && consumeraccountdetails.minimumpaymentdue != null){
		       $('#recurringPayDiv').show();
			   $("#lastpaydate").text(consumeraccountdetails.lastpaymentdate);
			   $("#minpaydue").text(consumeraccountdetails.minimumpaymentdue);
			}else{
				$('#recurringPayDiv').hide();
			}

		}


		function toogleAccountTable(accountsummarydetails){
		 for(i = 0; i < accountsummarydetails.length; i++){
			 $('#collapse-tbl'+accountsummarydetails[i].account).toggle();
	      }
		}
		
		function populateJudgementDetails(judgementDetails){
			
			$('#judgmentTbl').css('display', 'none');
			
			 if(judgementDetails != undefined && !jQuery.isEmptyObject(judgementDetails)){
				 
				 $('#judgmentTbl').css('display', '');
				 $("#judgmentTbl tr:gt(0)").remove();
				 
				 $.map( judgementDetails, function( value, key ) {
					 
					 let judgmentRows = "";
					 
					 let judgmentId = value[0].judgmentId;
					 let judgmentDate = value[0].judgmentDate == null ? "" : value[0].judgmentDate;
					 let caseNumber = value[0].suitCaseNumber == null ? "" : value[0].suitCaseNumber; 
					 let judgmentTotal = value[0].judgmentTotal == null || value[0].judgmentTotal == 0 ? currencySymbol+'0.00' : currencySymbol+value[0].judgmentTotal.toFixed(2);
					 
					 judgmentRows = "<tr id=tble-plus"+judgmentId+">+<td><a style='display:"+(showAccountsInJudgementGrouping == "Y" ? "block" : "none")+"' id="+judgmentId+" ref-count='' ref-attr='' class=table-link><i id=imgtoogleid"+judgmentId+" class='fa fa-plus-circle'/></a></td><td align='center'>"+caseNumber+"</td><td></td><td align='center'>"+judgmentDate+"</td><td></td><td align='center'>"+judgmentTotal+"</td><td></td></tr>";
					 
					 if(showAccountsInJudgementGrouping == 'Y'){
						 judgmentRows = judgmentRows + "<tr class=row-list id=collapse-tbl-j-"+judgmentId+"><td colspan=7  class=clt-innerTbl id=clt-inner-tbl"+judgmentId+">";
						 
						 judgmentRows = judgmentRows +  "<div class='col-xs-12'><table><tbody><tr><th><label>"+innerTableAccountNoTxt+"</label></th><th><label>"+innerTableJudClientNameTxt+"</label></th><th><label>"+innerTableJudClientAccountTxt+"</label></th><th><label>"+innerTableDateOfServiceTxt+"</label></th><th><label>"+innerTableDateOfReferrelTxt+"</label></th><th><label>"+innerTableAmountReferredTxt+"</label></th><th><label>"+innerTableBalanceDueTxt+"</label></th><th><label>"+innerTableReceiptTxt+"</label></th></tr>";
						 
							for(var i = 0; i < value.length; i++) {
					       		var clientNamelVal=value[i].clientName==null?'':value[i].clientName;
					       		var clientAccountVal=value[i].clientAccount==null?'':value[i].clientAccount;
					       		var accountVal=value[i].account==null?'':value[i].account;
					       		var dateOfServiceVal=value[i].dateOfService==null?'':value[i].dateOfService;
					       		var dateOfReferralVal=value[i].dateOfReferral==null?'':value[i].dateOfReferral;
					       		var clientItemGroupRefIdVal=value[i].clientItemGroupRefId==null?'':value[i].clientItemGroupRefId; 
					       		var amountReferedVal=value[i].amountRefered==null||value[i].amountRefered==0?currencySymbol+'0.00':currencySymbol+value[i].amountRefered.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
					       		var balanceDueVal=value[i].balanceDue==null||value[i].balanceDue==0?currencySymbol+'0.00':currencySymbol+value[i].balanceDue.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
					       		
					       		var accountRow = "<tr><td align=center>"+accountVal+"</td><td align=center>"+clientNamelVal+"</td><td align=center>"+clientAccountVal+"</td><td align=center>"+dateOfServiceVal+"</td><td align=center>"+dateOfReferralVal+"</td><td align=right>"+amountReferedVal+"</td><td align=right>"+balanceDueVal+"</td><td align=center><a title=pdf class=pdf-gen-summary><button onClick=loadAccountReceipt("+accountVal+") class='pdf-icon' ><img src='../resources/images/pdf.png' width='22'/></button></a></td></tr>";
					       		judgmentRows = judgmentRows.concat(accountRow);
							}
							
							judgmentRows = judgmentRows.concat("</tbody></table></div></td></tr>");
					 }
						
					 $("#judgmentTbl #judgmentTdetailsbody").append(judgmentRows); 
				 });
				 
				$('[id^="collapse-tbl-j-"]').toggle();
			 }
		}

		function populateAccountSummary(accountsummarydetails){
		  $("#tblaccounts tr:gt(0)").remove();
		  loopAccountSummary(accountsummarydetails);
		}

        function loopAccountSummary(accountsummarydetails){

         var AccountSummaryRowContent;
         var DetailedAccountSummaryContent;
         if(accountsummarydetails.length>0){
       	 for(i = 0; i < accountsummarydetails.length; i++){
       		 
       		var clientItemGroupRefIds = accountsummarydetails[i].clientGroupedAccounts.split(',');
       		var accountWithRef = '';
       		
       		if(clientItemGroupRefIds.length > 1){
       			accountWithRef = groupRefTxt + accountsummarydetails[i].clientItemGroupRefId;
       		}else{
       			accountWithRef = accountsummarydetails[i].account==null?'':accountsummarydetails[i].account;
       		}
       		
       		var accountVal = accountsummarydetails[i].account==null?'':accountsummarydetails[i].account;
       		var clientNamelVal=accountsummarydetails[i].clientName==null?'':accountsummarydetails[i].clientName;
       		var clientAccountVal=accountsummarydetails[i].clientAccount==null?'':accountsummarydetails[i].clientAccount;
       		var dateOfServiceVal=accountsummarydetails[i].dateOfService==null?'':accountsummarydetails[i].dateOfService;
       		var dateOfReferralVal=accountsummarydetails[i].dateOfReferral==null?'':accountsummarydetails[i].dateOfReferral;
       		var interestVal=accountsummarydetails[i].interest==null||accountsummarydetails[i].interest==0?currencySymbol+'0.00':currencySymbol+accountsummarydetails[i].interest.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
       		var interestRateVal=accountsummarydetails[i].interestRate==null||accountsummarydetails[i].interestRate==0?'0':accountsummarydetails[i].interestRate;
       		var principalBalanceVal=accountsummarydetails[i].principalBalance==null||accountsummarydetails[i].principalBalance==0?currencySymbol+'0.00':currencySymbol+accountsummarydetails[i].principalBalance.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
       		var forxVal=accountsummarydetails[i].forx==null?'':accountsummarydetails[i].forx;
       		var lastPaymentDateVal=accountsummarydetails[i].lastPaymentDate==null?'':accountsummarydetails[i].lastPaymentDate;
       		var amountReferedVal=accountsummarydetails[i].amountRefered==null||accountsummarydetails[i].amountRefered==0?currencySymbol+'0.00':currencySymbol+accountsummarydetails[i].amountRefered.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
       		var otherChargesVal=accountsummarydetails[i].otherCharges==null||accountsummarydetails[i].otherCharges==0?currencySymbol+'0.00':currencySymbol+accountsummarydetails[i].otherCharges.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
       		var courtCostVal=accountsummarydetails[i].courtCost==null||accountsummarydetails[i].courtCost==0?currencySymbol+'0.00':currencySymbol+accountsummarydetails[i].courtCost;
       		var accumulatedInterestVal=accountsummarydetails[i].accumulatedInterest==null||accountsummarydetails[i].accumulatedInterest==0?currencySymbol+'0.00':currencySymbol+accountsummarydetails[i].accumulatedInterest.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
       		var balanceDueVal=accountsummarydetails[i].balanceDue==null||accountsummarydetails[i].balanceDue==0?currencySymbol+'0.00':currencySymbol+accountsummarydetails[i].balanceDue.replace(/\B(?=(\d{3})+(?!\d))/g, ",");

      		var AccountSummaryRowContent="<tr id=tble-plus"+accountVal+">+<td><a id="+accountVal+" ref-count="+accountsummarydetails[i].groupRefIdCount+" ref-attr='"+accountsummarydetails[i].groupRefId+"' class=table-link><i id=imgtoogleid"+accountVal+" class='fa fa-plus-circle'/></a></td><td align='center'>"+accountWithRef+"</td><td align='center'>"+clientNamelVal+"</td><td align='center'>"+dateOfServiceVal+"</td><td align='center'>"+dateOfReferralVal+"</td><td align='center'>"+interestRateVal+"</td><td align='right'>"+balanceDueVal+"</td></tr>";

      		var data="<tr class=row-list id=collapse-tbl"+accountVal+"><td colspan=7  class=clt-innerTbl id=clt-inner-tbl"+accountVal+"><div class=col-xs-6><ul><li><label>"+client+"</label><span>"+clientNamelVal+"</span></li><li><label>"+clientAccount+"</label><span>"+clientAccountVal+"</span></li><li><label>"+For+"</label><span>"+forxVal+"</span></li><li><label>"+interestRate+"</label><span>"+interestRateVal+"</span></li><li><label>"+referralDate+"</label><span>"+dateOfReferralVal+"</span></li><li><label>"+serviceDate+"</label><span>"+dateOfServiceVal+"</span></li><li><label>"+lastPayDate+"</label><span>"+lastPaymentDateVal+"</span></li></ul></div><div class=col-xs-6><ul class='account-detlsList'><li id=parentReceipt"+accountVal+"><button onClick=loadAccountReceipt("+accountVal+") class='table-link' id='pdfId'><img src='../resources/images/pdf.png'/>"+viewPaymentPdf+"</button></li><li><label>"+amountReferred+"</label><span>"+amountReferedVal+"</span></li><li><label>"+principalBalance+"</label><span>"+principalBalanceVal+"</span></li><li><label>"+interest+"</label><span>"+interestVal+"</span></li><li><label>"+accumulatedInterest+"</label><span>"+accumulatedInterestVal+"</span></li><li><label>"+otherCharges+"</label><span>"+otherChargesVal+"</span></li><li><label>"+balanceDue+"</label><span>"+balanceDueVal+"</span></li></ul></div></td></tr>";
      		  var newRowContent=AccountSummaryRowContent.concat(data);

       		$("#tblaccounts #accountdetailsbody").append(newRowContent);
      	  }
         }
         else{
        	 var EmptyRowContent="<tr><td colspan=\"7\" style='height:9px;text-align:center;'><b>"+tableEmptyMessage+"</b></td></tr>";
        	 $("#tblaccounts #accountdetailsbody").append(EmptyRowContent);
         }
		}
        var prevId=0;       
     $(document).on("click", "#tblaccounts #accountdetailsbody .table-link", function(event) {
    	 
    	 var id=this.id;
    	 
    	 var referenceId = $(this).attr("ref-attr");
    	 var referenceIdCount = $(this).attr("ref-count");
    	 
    	 if(referenceId != null && referenceId != "" && referenceId != "null" && referenceIdCount > 1 && $.inArray(referenceId, alreadySelectedId) <= -1){
    		 
    		 enableSubGrid(id, referenceId);
    		 
    		 $('#imgtoogleid'+prevId).removeClass('fa fa-minus-circle').addClass('fa fa-plus-circle');
    		 $("#collapse-tbl"+prevId).toggle();
    		 $('#imgtoogleid'+id).removeClass('fa fa-plus-circle').addClass('fa fa-minus-circle');
    		 $("#collapse-tbl"+id).toggle();
    		 prevId=id;
    		 alreadySelectedId.push(referenceId);
    		 
		 } else {
    	 if(prevId != 0){
    		 if(prevId != id){
    			 if($("#collapse-tbl"+prevId).is(":hidden")){

            	 }
            	 else{
            		 $('#imgtoogleid'+prevId).removeClass('fa fa-minus-circle').addClass('fa fa-plus-circle');
            		 $("#collapse-tbl"+prevId).toggle();
            	 }
    			 if($("#collapse-tbl"+id).is(":hidden")){
    				 prevId=id;
            		 $('#imgtoogleid'+id).removeClass('fa fa-plus-circle').addClass('fa fa-minus-circle');
            		 $("#collapse-tbl"+id).toggle();
            	 }
            	 else{
            		 prevId=id;
            		 $('#imgtoogleid'+id).removeClass('fa fa-minus-circle').addClass('fa fa-plus-circle');
            		 $("#collapse-tbl"+id).toggle();
            	 }
    		 }else{
    			 if($("#collapse-tbl"+id).is(":hidden")){
    				 prevId=id;
            		 $('#imgtoogleid'+id).removeClass('fa fa-plus-circle').addClass('fa fa-minus-circle');
            		 $("#collapse-tbl"+id).toggle();
            	 }
            	 else{
            		 prevId=id;
            		 $('#imgtoogleid'+id).removeClass('fa fa-minus-circle').addClass('fa fa-plus-circle');
            		 $("#collapse-tbl"+id).toggle();
            	 }
    		 }
    	 }else{
    		 prevId=id;
    		 $('#imgtoogleid'+id).removeClass('fa fa-plus-circle').addClass('fa fa-minus-circle');
    		 $("#collapse-tbl"+id).toggle();
    	 }
		 }

       	});
    
var prevIdJud=0;
$(document).on("click", "#judgmentTbl #judgmentTdetailsbody .table-link", function(event) {
    	 
    	 var id=this.id;
    	 if(prevIdJud != 0){
    		 if(prevIdJud != id){
    			 if($("#collapse-tbl-j-"+prevIdJud).is(":hidden")){ 

            	 }else{
            		 $('#imgtoogleid'+prevIdJud).removeClass('fa fa-minus-circle').addClass('fa fa-plus-circle');
            		 $("#collapse-tbl-j-"+prevIdJud).toggle();
            	 }
    			 if($("#collapse-tbl-j-"+id).is(":hidden")){
    				 prevIdJud=id;
            		 $('#imgtoogleid'+id).removeClass('fa fa-plus-circle').addClass('fa fa-minus-circle');
            		 $("#collapse-tbl-j-"+id).toggle();
            	 }else{
            		 prevIdJud=id;
            		 $('#imgtoogleid'+id).removeClass('fa fa-minus-circle').addClass('fa fa-plus-circle');
            		 $("#collapse-tbl-j-"+id).toggle();
            	 }
    		 }else{
    			 if($("#collapse-tbl-j-"+id).is(":hidden")){
    				 prevIdJud=id;
            		 $('#imgtoogleid'+id).removeClass('fa fa-plus-circle').addClass('fa fa-minus-circle');
            		 $("#collapse-tbl-j-"+id).toggle();
            	 } else{
            		 prevIdJud=id;
            		 $('#imgtoogleid'+id).removeClass('fa fa-minus-circle').addClass('fa fa-plus-circle');
            		 $("#collapse-tbl-j-"+id).toggle();
            	 }
    		 }
    	 }else{
    		 prevIdJud=id;
    		 $('#imgtoogleid'+id).removeClass('fa fa-plus-circle').addClass('fa fa-minus-circle');
    		 $("#collapse-tbl-j-"+id).toggle();
    	 }
       	});

function loadAccountReceipt(accountId){
	$.fileDownload(downloadAccountPdf+accountId)
	                 .fail(function () {
	                	  $("#file-download-error-msg").modal();
	                	 });
}

function enableSubGrid(lastRowId, referenceId){
	$.ajax({
		type:'GET',
		 url:accountsByGroupReferId + referenceId,
		 async:false,
		 datatype : 'json',
		 contentType: 'application/json',
		 success : function(response) {
			
			loadClientGroupAccounts(response, lastRowId);
		 },
				error : function(xhr, status, error) {

				 }
	 });
}

function loadClientGroupAccounts(accounts, lastRowId){
	
	
	
	 if(accounts.length>1){
		 
		 var clientGroupAccounts = "<div class='col-xs-12'><table><tbody><tr><th><label>"+innerTableAccountNoTxt+"</label></th><th><label>"+innerTableClientNameTxt+"</label></th><th><label>"+innerTableChargeGroupRefTxt+"</label></th><th><label>"+innerTableDateOfServiceTxt+"</label></th><th><label>"+innerTableDateOfReferrelTxt+"</label></th><th><label>"+innerTableAmountReferredTxt+"</label></th><th><label>"+innerTableBalanceDueTxt+"</label></th><th><label>"+innerTableReceiptTxt+"</label></th></tr>";
		 
		 $("#parentReceipt"+lastRowId).hide();
		 
		for(var i = 0; i < accounts.length; i++){
       		var clientNamelVal=accounts[i].clientName==null?'':accounts[i].clientName;
       		var clientAccountVal=accounts[i].clientAccount==null?'':accounts[i].clientAccount + "/";
       		var accountVal=accounts[i].account==null?'':accounts[i].account;
       		var dateOfServiceVal=accounts[i].dateOfService==null?'':accounts[i].dateOfService;
       		var dateOfReferralVal=accounts[i].dateOfReferral==null?'':accounts[i].dateOfReferral;
       		var clientItemGroupRefIdVal=accounts[i].clientItemGroupRefId==null?'':accounts[i].clientItemGroupRefId;
       		var amountReferedVal=accounts[i].amountRefered==null||accounts[i].amountRefered==0?currencySymbol+'0.00':currencySymbol+accounts[i].amountRefered.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
       		var balanceDueVal=accounts[i].balanceDue==null||accounts[i].balanceDue==0?currencySymbol+'0.00':currencySymbol+accounts[i].balanceDue.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
       		
       		var accountRow = "<tr><td align=center>"+accountVal+"</td><td align=center>"+clientAccountVal + clientNamelVal+"</td><td align=center>"+clientItemGroupRefIdVal+"</td><td align=center>"+dateOfServiceVal+"</td><td align=center>"+dateOfReferralVal+"</td><td align=right>"+amountReferedVal+"</td><td align=right>"+balanceDueVal+"</td><td align=center><a title=pdf class=pdf-gen-summary><button onClick=loadAccountReceipt("+accountVal+") class='pdf-icon' ><img src='../resources/images/pdf.png' width='22'/></button></a></td></tr>";
       		clientGroupAccounts = clientGroupAccounts.concat(accountRow);
		}
		clientGroupAccounts = clientGroupAccounts.concat("</tbody></table></div>");
		
		 $("#clt-inner-tbl"+ lastRowId).append(clientGroupAccounts);
	 }/*else{
		  clientGroupAccounts = clientGroupAccounts.concat("<tr><td colspan=\"7\" style='height:9px;text-align:center;'><b>"+tableEmptyMessage+"</b></td></tr></tbody></table></div>");
	 }*/
	 
	
}
