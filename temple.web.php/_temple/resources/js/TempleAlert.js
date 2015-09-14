
function TempleAlert(index, msg) {

	this.index = typeof index === 'string' ? parseInt(index) : index;
	this.msg = msg;
	this.divId = TempleAlert.ID_PREFIX + TempleAlert.ID_SUFFIX++;
}

// public static
TempleAlert.LEVELS = ['danger', 'warning', 'info', 'success'];
TempleAlert.ICONS = ['exclamation-sign', 'warning-sign', 'info-sign', 'ok-sign'];
TempleAlert.ALERTS_DIV = $('#_templeAlerts');
TempleAlert.ID_PREFIX = '_templeAlert';
TempleAlert.ID_SUFFIX = 0;
TempleAlert.AUTO_DISMISS_TIMEOUT = 5000;
TempleAlert.SUCCESS_STATUS = 3;

TempleAlert.autoClose = function(selector) {
	window.setTimeout(function() {
		$(selector).alert('close');
	}, TempleAlert.AUTO_DISMISS_TIMEOUT);
};

TempleAlert.showAll = function(messages) {
	$.each(messages, function(level, msgs) {
		for (var i = 0, l = msgs.length; i < l; i++) {
			new TempleAlert(level, msgs[i]).show();
		}
	});
} ;

TempleAlert.prototype.show = function() {
	if (this.msg) {
		TempleAlert.ALERTS_DIV.prepend('<div id="' + this.divId + '" class="fade in alert alert-dismissable alert-' +
				TempleAlert.LEVELS[this.index] + '">' +
				'<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>' +
				'<span class="glyphicon glyphicon-' + TempleAlert.ICONS[this.index] + '"></span> ' +
				this.msg.replace(/\n/g, '<br />') + '</div>');
		if (this.index === TempleAlert.SUCCESS_STATUS) {
			TempleAlert.autoClose('#' + this.divId) ;
		}
	}
};

// Auto Close success messages
TempleAlert.autoClose('.alert.alert-dismissable.alert-success') ;
