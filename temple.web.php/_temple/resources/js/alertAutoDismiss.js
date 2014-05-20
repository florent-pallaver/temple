var ALERT_AUTO_DISMISS_TIMEOUT = 5000 ;

// Auto Close success messages
window.setTimeout(function() {
	$('.alert.alert-dismissable.alert-success').alert('close') ;
}, ALERT_AUTO_DISMISS_TIMEOUT);

