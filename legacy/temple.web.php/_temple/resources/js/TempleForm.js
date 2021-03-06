$(document).ajaxError(function (event, jqxhr, settings, thrownError) {
    alert('An error occured while processing the request.\n\nCause: Error ' + jqxhr.status + ' - ' + thrownError);
});

String.prototype.endsWith = function (suffix) {
    return this.indexOf(suffix, this.length - suffix.length) !== -1;
};

function TempleForm(form) {

    this.form = form;

    var f = $(form);
    this.submits = f.find('*[type="submit"]');
    this.clicked = f.find('button[type="submit"]._clicked');

}

TempleForm.SUCCESS_STATUS = 3;

TempleForm.prototype.submitDone = function (result) {
    var f = $(this.form);
    f.find('.form-group').removeClass('has-error');
    f.find('._temple_feedback').remove();
    TempleAlert.showAll(result.messages);
    if (result.status === TempleForm.SUCCESS_STATUS) {
        var data = f.data();
        var fn = data['onSuccess'];
        if (fn && (typeof window[fn] === 'function')) {
            window[fn](data, result.data);
        }
        if (result.goTo) {
            window.location.replace(result.goTo);
        } else if (result.reload) {
//			window.setTimeout(function() {
            if (!window.location.reload()) {
                // link pour reloader
            }
//			}, 1000);
        } else {
            if (result.resetForm) {
                this.form.reset();
            }
            f.find('input[type="password"]').val('');
        }
    } else {
        var feedbacks = result.feedbacks || [];
        var keys = Object.keys(feedbacks);
        if (keys && keys.length) {
            for (var i = 0; i < keys.length; i++) {
                var field = f.find('[name="' + keys[i] + '"]');
                field.closest('.form-group').addClass('has-error');
                var ig = field.closest('.input-group');
                if (ig.length) {
                    field = ig;
                }
                field.after('<small class="help-block _temple_feedback">' + feedbacks[keys[i]] + '</small>');
            }
            if (this.form[keys[0]] && typeof this.form[keys[0]].focus === 'function') {
                this.form[keys[0]].focus();
            }
        }
    }
};

TempleForm.prototype.submitAlways = function () {
    if (this.clicked.length) {
        this.clicked.removeClass('_clicked');
        this.clicked[0].blur();
    }
    this.submits.removeAttr('disabled');
};

/**
 * Submits the encapsulated form
 * 
 * @returns void
 */
TempleForm.prototype.submit = function () {
    this.submits.attr('disabled', 'disabled');

    var data = new FormData(this.form);
    if (this.clicked.length) {
        data.append(this.clicked.attr('name'), this.clicked.val());
    }

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

TempleForm.CONFIRM_MESSAGE = 'Are you sure you want to do this action?';

TempleForm.spySubmits = function () {

    $('input[type="file"][maxlength]').on('change', function () {
        var formGroup = $(this).closest('.form-group');
        var ok = true;
        if (this.files.length) {
            ok = this.files[0].size <= $(this).attr('maxlength');
        }
        if (ok) {
            formGroup.removeClass('has-error');
        } else {
            formGroup.addClass('has-error');
        }
    });

    $('button[type="submit"]').on('click', function (event) {
        if (!$(this).hasClass('_confirm') || confirm(TempleForm.CONFIRM_MESSAGE)) {
            $(this).addClass('_clicked');
        } else {
            this.blur();
            event.preventDefault();
        }
    });

    $('form._temple-form').on('submit', function (event) {
        event.preventDefault();
        new TempleForm(this).submit();
    });

};

TempleForm.spySubmits();
