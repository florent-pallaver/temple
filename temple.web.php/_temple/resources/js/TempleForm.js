$(document).ajaxError(function(event, jqxhr, settings, thrownError) {
	alert('An error occured while processing the request.\n\nCause: Error ' + jqxhr.status + ' - ' + thrownError);
});

String.prototype.endsWith = function(suffix) {
	return this.indexOf(suffix, this.length - suffix.length) !== -1;
};

function TempleForm(form) {

	this.form = form;

	var f = $(form);
	this.submits = f.find('*[type="submit"]');
	this.clicked = f.find('button[type="submit"]._clicked');

}

TempleForm.SUCCESS_STATUS = 3;

TempleForm.prototype.submitDone = function(result) {
	TempleAlert.showAll(result.messages);
	if (result.status === TempleForm.SUCCESS_STATUS) {
		var data = $(this.form).data();
		var fn = data['onSuccess'];
		if (fn && (typeof window[fn] === 'function')) {
			window[fn](data, result.data);
		}
		if(result.goTo) {
			window.location.replace(result.goTo) ;
		} else if (result.reload) {
//			window.setTimeout(function() {
				if (!window.location.reload()) {
					// link pour reloader
				}
//			}, 1000);
		} else {
			if(result.resetForm) {
				this.form.reset();
			}
			$(this.form).find('input[type="password"]').val('') ;
		}
	}
};

TempleForm.prototype.submitAlways = function() {
	this.clicked.removeClass('_clicked');
	this.clicked[0].blur();
	this.submits.removeAttr('disabled');
};

TempleForm.prototype.submit = function() {
	this.submits.attr('disabled', 'disabled');

	var data = new FormData(this.form);
	data.append(this.clicked.attr('name'), this.clicked.val());

	$.ajax($(this.form).attr('action'), {
		type: 'POST',
		dataType: 'json',
		cache: false,
		context: this,
		data: data,
		contentType: false,
		processData: false})
			.done(this.submitDone)
			.always(this.submitAlways);
};

TempleForm.spySubmits = function() {

	$('button[type="submit"]').on('click', function(event) {
		if(!$(this).hasClass('_confirm') || confirm('Are you sure you want to do this action?')) {
			$(this).addClass('_clicked');
		} else {
			this.blur() ;
			event.preventDefault() ;
		}
	});

	$('form._temple-form').on('submit', function(event) {
		event.preventDefault();
		new TempleForm(this).submit();
	});

};

TempleForm.spySubmits();

//	var data = {};
//
//	var button = form.find('button[type="submit"]._clicked');
//	data[button.attr('name')] = button.val();
//
//	var inputs = form.find('input');
//	$.each(inputs, function(i, e) {
//		var input = $(e);
//		var name = input.attr('name');
//		var val = input.val();
//		if (input.attr('type') !== 'checkbox' || input.prop('checked')) {
//			if (name.endsWith('[]')) {
//				if (!data[name]) {
//					data[name] = [];
//				}
//				data[name].push(val);
//			} else {
//				data[name] = val;
//			}
//		}
//	});
//
//	var selects = form.find('select');
//	$.each(selects, function(i, e) {
//		data[$(e).attr('name')] = $(e).val();
//	});
//
//	var texts = form.find('textarea');
//	$.each(texts, function(i, e) {
//		data[$(e).attr('name')] = $(e).val();
//	});
//
//	var formId = form.attr('id');
//	if (formId) {
//		$('*[form="' + formId + '"]');
//	}
