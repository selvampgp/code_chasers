var currencySymbol = "<span class=inner-dollarSign>$</span>";
var alreadySelectedId;
$(document).ready(function(){

	alreadySelectedId=[];

				$.ajax({
					type:'GET',
					 url:transactionsummary,
					 datatype : 'json',
					 async:false,
	 				 contentType: 'application/json',
	 				 success : function(response) {
	 					 try{
	 					var data = JSON.parse(response);
	 					populateTransactionSummary(data.accountsummarytransactions);
	 					 }catch(e){
	 						var EmptyRowContent="<tr><td colspan=\"7\" style='height:9px;text-align:center;'><b>"+tableEmptyMessage+"</b></td></tr>";
				        	 $("#tbltransaction #transactiondetailsbody").append(EmptyRowContent);
	 					 }
	 				 },
	 				error : function(xhr, status, error) {
	 				   var EmptyRowContent="<tr><td colspan=\"7\" style='height:9px;text-align:center;'><b>"+tableEmptyMessage+"</b></td></tr>";
			        	 $("#tbltransaction #transactiondetailsbody").append(EmptyRowContent);
	 				 }
				 });
			});

			function populateTransactionSummary(accountsummarytransactions){
				  $("#tbltransaction tr:gt(0)").remove();
				  loopTransactionSummary(accountsummarytransactions);
				}
	         var temp=0;
	         var selectedRow;
			 function loopTransactionSummary(accountsummarytransactions){

		         if(accountsummarytransactions.length>0){
		       	 for(i = 0; i < accountsummarytransactions.length; i++){

		       		var balanceVal= accountsummarytransactions[i].balance==null||accountsummarytransactions[i].balance==0.00?currencySymbol+'0.00':currencySymbol+accountsummarytransactions[i].balance.toFixed(2);
                    var plusIconName = "fa fa-plus-circle";


		       		if(accountsummarytransactions[i].pymtype == "INI"){
		       			balanceVal = '-';
		       		   // $("#astericksDefi").show();
		       		    plusIconName = "";
		       		}

		       		var interestRateVal=accountsummarytransactions[i].interestper==null?'0.0':accountsummarytransactions[i].interestper;
		       		var amountVal=accountsummarytransactions[i].amount==null||accountsummarytransactions[i].amount==0.00?currencySymbol+'0.00':currencySymbol+accountsummarytransactions[i].amount.toFixed(2);
		       		var payDateVal=accountsummarytransactions[i].paymentDate==null?'':accountsummarytransactions[i].patpaypaymentdatestr;

		       		temp++;

		       		var receiptIcon = "";

		       		if(accountsummarytransactions[i].paymentMode != "DP" && accountsummarytransactions[i].status.indexOf("Reversed") < 0){

		       			var oldReceiptFrom = new Date("November 21, 2017");
                        var authDate = new Date();
                        var key = "new";
                        var receiptId;

                        if(accountsummarytransactions[i].paymentAuthDate != null && accountsummarytransactions[i].paymentAuthDate != "")
                        	authDate = new Date(accountsummarytransactions[i].paymentAuthDate);
                        else if(accountsummarytransactions[i].paymentDate != null && accountsummarytransactions[i].paymentDate != "")
                        	authDate = new Date(accountsummarytransactions[i].paymentDate);

                        if(oldReceiptFrom >= authDate || accountsummarytransactions[i].payReferId == null || accountsummarytransactions[i].payReferId == ""){
                        	key = "old";
                        	receiptId = accountsummarytransactions[i].payId;
                        }else{
                        	receiptId = accountsummarytransactions[i].payReferId;
                        }

		       		 receiptIcon = "<a id="+accountsummarytransactions[i].payId+" title=pdf class=pdf-gen-summary><button onClick=loadPaymentReceipt("+receiptId+",'"+key+"') class='pdf-icon' ><img src='../resources/images/pdf.png' width='22'/></button></a>";

		       		}

		       		var TransSummaryRowContent="<tr class='"+temp+"' name="+temp+" id="+accountsummarytransactions[i].payId+">+<td><a id="+temp+" class=tble-plus><i id=imgtoogleid"+temp+" onClick='loadPaymentData("+JSON.stringify(accountsummarytransactions[i])+","+temp+","+accountsummarytransactions[i].payId+");' class='"+plusIconName+"'/></a></td><td>"+payDateVal+"</td><td>"+accountsummarytransactions[i].paymentMode+"</td><td>"+accountsummarytransactions[i].status+"</td><td>"+interestRateVal+"</td><td width='10%' align='right'>"+amountVal+"</td><td align='center'>"+receiptIcon+"</td></tr>";//<td width='10%' align='right'>"+balanceVal+"</td>
		       		$("#tbltransaction #transactiondetailsbody").append(TransSummaryRowContent);
		      }
		      }
		       else{
		    	   var EmptyRowContent="<tr><td colspan=\"7\" style='height:9px;text-align:center;'><b>"+tableEmptyMessage+"</b></td></tr>";
		        	 $("#tbltransaction #transactiondetailsbody").append(EmptyRowContent);
		       }

		    }

			function loopTransactionDetailed(transactiondetail,payid){
		     var returnformattedtransactions;
		     var totalpaymentamount=0;
		     var totalpaidprincipal=0;
		     var totalinterest=0;
		     var totalothercharges=0;
		     var totalcourtcost=0;


			if(transactiondetail.length>0){
			  var transactionheader="<tr id=tble-plus-child"+selectedRow+"><td style='padding:0px;' colspan='9' id=collapse-tbl"+payid+"><table class='table accord-table table-striped'><thead><tr><th>"+accountNo+"</th><th>"+type+"</th><th>"+data+"</th><th>"+payAmount+"</th><th>"+paidOnPrincipal+"</th><th>"+paidOnInterest+"</th><th>"+paidOnOtherCharges+"</th><th>"+paidOnCourtCost+"</th></tr></thead><tbody>";
			  for(i = 0; i < transactiondetail.length; i++){

				  var paymentAmountVal=transactiondetail[i].paymentAmount==null||transactiondetail[i].paymentAmount==0?currencySymbol+'0.00':currencySymbol+transactiondetail[i].paymentAmount.toFixed(2);
				  var paidOnPrincipleVal=transactiondetail[i].paidOnPrinciple==null||transactiondetail[i].paidOnPrinciple==0?currencySymbol+'0.00':currencySymbol+transactiondetail[i].paidOnPrinciple.toFixed(2);
				  var paidOnInterestVal=transactiondetail[i].paidOnInterest==null||transactiondetail[i].paidOnInterest==0?currencySymbol+'0.00':currencySymbol+transactiondetail[i].paidOnInterest.toFixed(2);
				  var paidOnOtherChargesVal=transactiondetail[i].paidOnOtherCharges==null||transactiondetail[i].paidOnOtherCharges==0?currencySymbol+'0.00':currencySymbol+transactiondetail[i].paidOnOtherCharges.toFixed(2);
				  var courtCostVal=transactiondetail[i].courtCost==null||transactiondetail[i].courtCost==0?currencySymbol+'0.00':currencySymbol+transactiondetail[i].courtCost.toFixed(2);
				  var payDateVal=transactiondetail[i].paymentDate==null?'':transactiondetail[i].patpaypaymentdatestr;

				var transsummary="<tr class='inner-tble'><td>"+transactiondetail[i].account+"</td><td>"+transactiondetail[i].paymentMode+"</td><td>"+payDateVal+"</td><td class='amount-val'>"+paymentAmountVal+"</td><td class='amount-val'>"+paidOnPrincipleVal+"</td><td class='amount-val'>"+paidOnInterestVal+"</td><td class='amount-val'>"+paidOnOtherChargesVal+"</td><td class='amount-val'>"+courtCostVal+"</td></tr>";

				transactionheader=transactionheader.concat(transsummary);

				totalpaymentamount=totalpaymentamount+transactiondetail[i].paymentAmount;
				totalpaidprincipal=totalpaidprincipal+transactiondetail[i].paidOnPrinciple;
				totalinterest=totalinterest+transactiondetail[i].paidOnInterest;
				totalothercharges=totalothercharges+transactiondetail[i].paidOnOtherCharges;
				totalcourtcost=totalcourtcost+transactiondetail[i].courtCost;
			  }
			 totalpaymentamount=currencySymbol+totalpaymentamount.toFixed(2);
			 totalpaidprincipal=currencySymbol+totalpaidprincipal.toFixed(2);
			 totalinterest=currencySymbol+totalinterest.toFixed(2);
			 totalothercharges=currencySymbol+totalothercharges.toFixed(2);
			 totalcourtcost=currencySymbol+totalcourtcost.toFixed(2);

			  var transactionfooter="</tbody><tfoot><tr class='tble-foot'><td>"+total+"</td><td colspan='2'></td><td class='amount-val'>"+totalpaymentamount+
			  "</td><td class='amount-val'>"+totalpaidprincipal+"</td><td class='amount-val'>"+totalinterest+"</td><td class='amount-val'>"+totalothercharges+
			  "</td><td class='amount-val'>"+totalcourtcost+"</td></tr></tfoot></table></td></tr>";

			  transactionheader=transactionheader.concat(transactionfooter);
			  returnformattedtransactions=transactionheader;
			}
			else{
				 var EmptyRowContent="<tr id=tble-empty"+selectedRow+" style=display:table-row;><td colspan=\"8\" style='height:9px;text-align:center;'><b>"+tableEmptyMessage+"</b></td></tr>";
				 returnformattedtransactions=EmptyRowContent;
			}

			$("#tbltransaction #transactiondetailsbody ."+selectedRow).after(returnformattedtransactions);
			$("#tble-plus-child"+selectedRow).toggle();
			}

			 function getPaymentSummary(data,payId){

				 $.ajax({
						type:'POST',
						 url:transactionbyid,
						 async:false,
						 data: JSON.stringify(data),
						 datatype : 'json',
						 async : false,
		 				 contentType: "application/json; charset=utf-8",
		 				 success : function(response) {
		 					var data = JSON.parse(response);
		 					loopTransactionDetailed(data.transactiondetail,payId);
		 				 },
		 				error : function(xhr, status, error) {
		 					 var EmptyRowContent="<tr id=tble-empty"+selectedRow+" style=display:table-row;><td colspan=\"8\" style='height:9px;text-align:center;'><b>"+tableEmptyMessage+"</b></td></tr>";
		 					 returnformattedtransactions=EmptyRowContent;
		 				 }
					 });
			 }


			 function loadPaymentData(data,selectRow,payId){

				 selectedRow = selectRow;

				 if($.inArray(selectedRow, alreadySelectedId)>-1){
					 enableToggle(selectedRow);
				 }else{
					 alreadySelectedId.push(selectedRow);
					 getPaymentSummary(data,payId);
					// window.setTimeout(function (){
					 enableToggle(selectRow);
					// }, 500);
				 }
			 }

			 var t=0;
			 function enableToggle(selectedRow){

            	 var imagetoogler='imgtoogleid'+selectedRow;

            	 if(t != 0){
		    		 if(t != selectedRow){
		    			 if($("#tble-plus-child"+t).is(":hidden")){

		            	 }
		            	 else{
		            		$('#imgtoogleid'+t).removeClass('fa fa-minus-circle').addClass('fa fa-plus-circle');
		 		    		$("#tble-plus-child"+t).toggle();
		            	 }
		    			 if($("#tble-plus-child"+selectedRow).is(":hidden")){
		                     t=selectedRow;
		                     $('#'+imagetoogler).removeClass('fa fa-plus-circle').addClass('fa fa-minus-circle');
			 		    	 $("#tble-plus-child"+selectedRow).toggle();
		            	 }
		            	 else{
		            		 t=selectedRow;
		            		 $('#'+imagetoogler).removeClass('fa fa-minus-circle').addClass('fa fa-plus-circle');
			 		    	 $("#tble-plus-child"+selectedRow).toggle();
		            	 }
		    		 }else{
		    			 if($("#tble-plus-child"+selectedRow).is(":hidden")){

		                     t=selectedRow;
		                     $('#'+imagetoogler).removeClass('fa fa-plus-circle').addClass('fa fa-minus-circle');
			 		    	 $("#tble-plus-child"+selectedRow).toggle();
		            	 }
		            	 else{
		            		 t=selectedRow;
		            		 $('#'+imagetoogler).removeClass('fa fa-minus-circle').addClass('fa fa-plus-circle');
			 		         $("#tble-plus-child"+selectedRow).toggle();
		            	 }
		    		 }
		    	 }else{

		    		 t=selectedRow;
		    		  $('#'+imagetoogler).removeClass('fa fa-plus-circle').addClass('fa fa-minus-circle');
	 		    		$("#tble-plus-child"+selectedRow).toggle();
		    	 }
			 }

function loadPaymentReceipt(id,key){
	$.fileDownload(downloadPdf + id + "/" + key)
	 .fail(function () {
  	  $("#file-download-error-msg").modal();
	 });
}