function Temple() {
}

// public static
Temple.LEVELS = ['success', 'info', 'warning', 'danger'];
Temple.ICONS = ['ok-sign', 'info-sign', 'warning-sign', 'exclamation-sign'];
Temple.ALERTS_DIV = $('#_templeAlerts');

function TempleAlert(index, msg) {

	this.index = index;
	this.msg = msg;

}

TempleAlert.prototype.show = function() {
	Temple.ALERTS_DIV.prepend('<div class="fade in alert alert-dismissable alert-' +
			Temple.LEVELS[this.index] + '">' +
			'<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>' +
			'<span class="glyphicon glyphicon-' + Temple.ICONS[this.index] + '"></span> ' +
			this.msg.replace(/\n/g, '<br />') + '</div>');
};

function TempleForm(form) {

	this.form = form ;

}

String.prototype.endsWith = function(suffix) {
	return this.indexOf(suffix, this.length - suffix.length) !== -1;
};
	
TempleForm.prototype.submit = function() {
		var form = $(this.form);
		var submits = form.find('*[type="submit"]');

		submits.attr('disabled', 'disabled');

		var data = {};

		var button = form.find('button[type="submit"]._clicked');
		data[button.attr('name')] = button.val();

		var inputs = form.find('input');
		$.each(inputs, function(i, e) {
			var input = $(e) ;
			var name = input.attr('name') ;
			var val = input.val() ;
			if(name.endsWith('[]')) {
				if(!data[name]) {
					data[name] = [] ;
				}
				if(input.attr('type') !== 'checkbox' || input.prop('checked')) {
					data[name].push(val) ;
				}
			} else {
				data[name] = val;
			}
		});

		var selects = form.find('select');
		$.each(selects, function(i, e) {
			data[$(e).attr('name')] = $(e).val();
		});

		var texts = form.find('textarea');
		$.each(texts, function(i, e) {
			data[$(e).attr('name')] = $(e).val();
		});

		var formId = form.attr('id');
		if (formId) {
			$('*[form="' + formId + '"]');
		}

		$.ajax(form.attr('action'), {
			type: 'POST',
			dataType: 'json',
			cache: false,
			context: this.form,
			data: data})
				.done(function(result) {
					$.each(result.messages, function(level, msgs) {
						for (var i = 0, l = msgs.length; i < l; i++) {
							new TempleAlert(level, msgs[i]).show();
						}
					});
					if (!result.status) {
						if (result.reload) {
							window.setTimeout(function() {
								if (!window.location.reload()) {
									// link pour reloader
								}
							}, 3000);
						} else {
							this.reset();
						}
					}
				})
				.fail(function(jqXHR, textStatus, errorThrown) {
					alert('An error occured while sending the request.\n' + textStatus + ' ' + errorThrown);
				})
				.always(function() {
					button.removeClass('_clicked');
					submits.removeAttr('disabled');
				});


} ;

Temple.spySubmits = function() {

	$('button[type="submit"]').on('click', function() {
		$(this).addClass('_clicked');
	});

	$('form').on('submit', function(event) {
		event.preventDefault() ;
		new TempleForm(this).submit() ;
	});

};

Temple.spySubmits() ;
