var replacableName = "consumerName";

function replaceName() {
	var notifyText = $('#agreetoterms p:first').text().replace(replacableName,
			$('#consumername').val());
	replacableName = _consumerName;

	$('#agreetoterms p:first').text(notifyText);
	$('#livechatNotifyModal').modal();
}

function closeModal() {
	$('#livechatNotifyModal').modal('toggle');
}

function openLiveChat() {
	var radioValue = $("input[name='chice']:checked").val();

	if (radioValue == 'ok') {
		Mibew.Objects.ChatPopups['582af7c751cea6ee'].open();
		$('#livechatNotifyModal').modal('toggle');
	} else {
		Mibew.Objects.ChatPopups['582af7c751cea6ee'].close();
		$('#livechatNotifyModal').modal('toggle');
	}

	return false;
}