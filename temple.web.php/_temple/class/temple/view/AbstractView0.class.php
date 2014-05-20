<?php

namespace temple\view;

use temple\URL;

/**
 * Description of AbstractView
 *
 * @author florent
 */
abstract class AbstractView0 implements View {

	protected static $icons = ['success' => 'ok-sign', 'info' => 'info-sign', 'warning' => 'warning-sign',
		'danger' => 'exclamation-sign'];
	private $labelClass;
	private $fieldClass;
	private $usingFormGroup;

	/** @var \paris\model\User */
	protected $user;

	/**
	 * @var \paris\model\UserType
	 */
	protected $userType;

	protected function __construct() {
		$this->user = & $_SESSION['user'];
		if ($this->user) {
			$this->userType = $this->user->getType();
		}
		$this->labelClass = 'col-sm-4';
		$this->fieldClass = 'col-sm-8';
		$this->usingFormGroup = true;
	}

	protected final function setLabelClass($labelClass) {
		$this->labelClass = $labelClass;
		return $this;
	}

	protected final function setFieldClass($fieldClass) {
		$this->fieldClass = $fieldClass;
		return $this;
	}

	/**
	 *
	 * @param type $action
	 * @param array $params
	 * @param type $root
	 * @return \paris\URL
	 */
	protected static final function getActionURL($action, array $params = []) {
		return URL::create('/action.php')->setParam('action', $action)
						->setParam('back', URL::getFromCurrentURL()->unsetParam('id'))
						->setParams($params);
	}

	protected final function renderWellTitle($icon, $title, $class = '') {
		?>
		<h4 class="paris <?= $class ?>">
			<?php $this->renderIcon($icon) ?> <?= $title ?>
		</h4>
		<?php
	}

	protected final function renderLinkButton(URL $url, $icon, $title, $confirm = '') {
		?>
		<a href="<?= $url ?>" class="btn btn-default" title="<?= $title ?>"
		   <?= $confirm ? 'onclick="return confirm(' . $this->toJSString($confirm) . ')"' : '' ?>>
			<span class="glyphicon glyphicon-<?= $icon ?>"></span>
		</a>
		<?php
	}

	protected final function renderAjaxButton(URL $url, $icon, $title, $toggleIcon = '', $toggleTitle = '') {
		$ti = $toggleIcon ? 'glyphicon glyphicon-' . $toggleIcon : '';
		$this->_renderAjaxButton($url, "<span class=\"glyphicon glyphicon-$icon\"></span>", $title, $ti, $toggleTitle);
	}

	protected final function renderAjaxTextButton(URL $url, $text, $title, $toggleText = '', $toggleTitle = '') {
		$this->_renderAjaxButton($url, "<b>$text</b>", $title, '', $toggleTitle, $toggleText);
	}

	private function _renderAjaxButton(URL $url, $in, $title, $toggleIcon = '', $toggleTitle = '', $toggleText = '') {
		?><a href="<?= $url ?>" class="btn btn-default" title="<?= $title ?>"
		<?= $toggleIcon ? 'data-toggle-icon="' . $toggleIcon . '"' : '' ?>
		<?= $toggleTitle ? 'data-toggle-title="' . $toggleTitle . '"' : '' ?>
		<?= $toggleText ? 'data-toggle-text="' . $toggleText . '"' : '' ?>
		   onclick="return parisAjax('<?= $url->unsetParam('back')->setParam('ajax', 1) ?>', this)"><?= $in ?>
		</a><?php
	}

	protected final function toJSString($str) {
		return "'" . str_replace("\n", '\\n', addslashes(htmlspecialchars($str, ENT_COMPAT | ENT_HTML5))) . "'";
	}

	protected final function renderIcon($icon, $title = '', $css = '') {
		?><span class="glyphicon glyphicon-<?= $icon ?> <?= $css ?>" title="<?= $title ?>"></span><?php
	}

	protected final function renderCheckbox($label, $name, $checked, $helpText = '') {
		?>
		<div class="form-group">
			<div class="<?= $this->fieldClass ?>">
				<label class="control-label <?= $this->fieldClass ?>"><?= $label ?> <input type="checkbox"
																						   name="<?= $name ?>"
																						   class="input-sm"
																						   value="1" <?= $checked ? 'checked="checked"' : '' ?>/></label>
					<?php $this->renderHelpText($helpText) ?>
			</div>
		</div><?php
				}

				protected final function renderFileInput($label, $name, $required, $helpText, $multiple = false) {
					?>
		<div class="form-group">
			<label class="<?= $this->labelClass ?> control-label<?= $required ? ' required' : '' ?>"><?= $label ?></label>

			<div class="<?= $this->fieldClass ?>">
				<input name="<?= $name ?>" <?= $required ? ' required="required"' : '' ?>
					   type="file"<?= $multiple ? ' multiple="multiple"' : '' ?> /><?php $this->renderHelpText($helpText) ?>
			</div>
		</div><?php
	}

	protected final function renderPassword($label, $name, $max, $required = false, $helpText = '') {
		$this->_renderInput('password', $label, $name, $max, '', $required, $helpText, '');
	}

	protected final function renderDatePicker($label, $name, $value, $required = false) {
		$date = date_parse($value);
		if ($date) {
			$year = $date['year'];
			$month = $date['month'];
			$day = $date['day'];
		}
					?>

		<div class="form-group">
			<?= $this->renderLabel($label, $required) ?>
			<div class="<?= $this->fieldClass ?>">
				<select id="<?= $name . '-month' ?>">
					<option value="">Month</option>
					<option value="1" <?= ($month == 1) ? 'selected' : '' ?>>Jan</option>
					<option value="2" <?= ($month == 2) ? 'selected' : '' ?>>Feb</option>
					<option value="3" <?= ($month == 3) ? 'selected' : '' ?>>Mar</option>
					<option value="4" <?= ($month == 4) ? 'selected' : '' ?>>Apr</option>
					<option value="5" <?= ($month == 5) ? 'selected' : '' ?>>May</option>
					<option value="6" <?= ($month == 6) ? 'selected' : '' ?>>Jun</option>
					<option value="7" <?= ($month == 7) ? 'selected' : '' ?>>Jul</option>
					<option value="8" <?= ($month == 8) ? 'selected' : '' ?>>Aug</option>
					<option value="9" <?= ($month == 9) ? 'selected' : '' ?>>Sep</option>
					<option value="10" <?= ($month == 10) ? 'selected' : '' ?>>Oct</option>
					<option value="11" <?= ($month == 11) ? 'selected' : '' ?>>Nov</option>
					<option value="12" <?= ($month == 12) ? 'selected' : '' ?>>Dec</option>
				</select>
				<select id="<?= $name . '-day' ?>">
					<option value="">Day</option>
					<?php
					for ($i = 1; $i <= 31; $i++) {
						if ($i == $day) {
							echo "<option value='$i' selected>$i</option>";
						} else {
							echo "<option value='$i'>$i</option>";
						}
					}
					?>
				</select>

				<select id="<?= $name . '-year' ?>">
					<option value="">Year</option>
					<?php
					$minYear = date("Y") - 18;
					$maxYear = date("Y") - 110;
					for ($i = $minYear; $i > $maxYear; $i--) {
						if ($i == $year) {
							echo("<option value='$i' selected>$i</option>");
						} else {
							echo("<option value='$i'>$i</option>");
						}
					}
					?>
				</select>
			</div>
			<input type="hidden" name="<?= $name ?>" value="" id="<?= $name . '-value' ?>">
		</div>

		<?php
        MainView::getInstance()->addJS("(function() {\$('#" . $name ."-day').change(updateDate); $('#". $name."-month').change(updateDate); $('#". $name ."-year').change(updateDate);	})(); function updateDate() {var day = $('#". $name ."-day').val(); var month = $('#". $name ."-month').val();var year = $('#". $name ."-year').val();$('#" . $name . "-value').val(day + '-' + month + '-' + year);}");
	}

	protected final function renderInput($label, $name, $max, $value, $required = false, $helpText = '', $pattern = '') {


		$this->_renderInput('text', $label, $name, $max, $value, $required, $helpText, $pattern);
	}

	protected final function _renderInput($type, $label, $name, $max, $value, $required, $helpText, $pattern) {
		if ($this->usingFormGroup) {
			?>
			<div class="form-group">
				<?php
			}
			$this->renderLabel($label, $required);
			?>
			<div class="<?= $this->fieldClass ?>">
				<input type="<?= $type ?>" name="<?= $name ?>" class="input-sm form-control" value="<?= $value ?>"
					   placeholder="<?= $label ?>"<?= $max ? " maxlength=\"$max\"" : '', $pattern ? " pattern=\"$pattern\"" : '', $required ? ' required="required"' : '' ?>/><?php $this->renderHelpText($helpText) ?>
			</div>
			<?php if ($this->usingFormGroup) { ?>
			</div>
			<?php
		}
	}

	protected final function renderTextArea($label, $name, $value, $helpText = '') {
		if ($this->usingFormGroup) {
			?><div class="form-group">
			<?php
		}
		$this->renderLabel($label);
		?>
			<div class="<?= $this->fieldClass ?>">
				<textarea name="<?= $name ?>" class="input-sm form-control" placeholder="<?= $label ?>"
						  rows="8"><?= $value ?></textarea><?php $this->renderHelpText($helpText) ?>
			</div>
			<?php if ($this->usingFormGroup) { ?>
			</div>
			<?php
		}
	}

	protected final function renderSelectGroup($label, array $optionsByNames, array $values, $allowNull, array $preLabels) {
		?>
		<div class="form-group">
			<fieldset>
				<?php $this->renderLabel($label) ?>
				<div class="<?= $this->fieldClass ?>">
					<?php
					foreach ($optionsByNames as $name => $options) {
						if (isset($preLabels[$name])) {
							?><label class="form-control-static"><?= $preLabels[$name] ?></label><?php
						}
						$this->_renderSelect($name, $options, $values[$name], $allowNull, 'paris');
					}
					?>
				</div>
			</fieldset>
		</div><?php
		}

		protected final function renderSelect($label, $name, array $options, $value = null, $allowNull = true, $helpText = '') {
			if ($this->usingFormGroup) {
						?><div class="form-group">
			<?php
		}
		$this->renderLabel($label);
		?>
			<div class="<?= $this->fieldClass ?>">
				<?php
				$this->_renderSelect($name, $options, $value, $allowNull);
				$this->renderHelpText($helpText);
				?>
			</div>
			<?php if ($this->usingFormGroup) { ?>
			</div>
			<?php
		}
	}

	private function _renderSelect($name, array $options, $value, $allowNull, $class = '') {
		?><select name="<?= $name ?>" class="input-sm form-control <?= $class ?>">
		<?php
		if ($allowNull) {
			?>
				<option value=""></option>
				<?php
			}
			foreach ($options as $k => $v) {
				?>
				<option
					value="<?= $k ?>"<?= $value !== null && $k == $value ? ' selected="selected"' : '' ?>><?= $v ?></option>
					<?php
				}
				?></select>
			<?php
	}

	protected final function renderEnumSelect($label, $name, array $options, $value = null, $allowNull = true, $helpText = '') {
		$this->renderSelect($label, $name, $options, $value === null ? null : $value->getOrdinal(), $allowNull, $helpText);
	}

	protected final function renderSubmit($icon, $text, $btnClass = 'btn-primary', $helpText = '') {
		$this->renderButtons(['submit' => ['icon' => $icon, 'text' => " $text", 'class' => $btnClass]], $helpText);
	}

	protected final function renderButtons(array $buttons, $helpText = '') {
		if ($this->usingFormGroup) {
			?><div class="form-group">
			<?php } ?>
			<div
				class="<?= substr($this->labelClass, 0, strrpos($this->labelClass, '-')), '-offset', substr($this->labelClass, strrpos($this->labelClass, '-')), ' ', $this->fieldClass ?> text-center paris">
				<div>
					<?php
					foreach ($buttons as $name => $values) {
						?>
						<button type="<?= _iod($values, 'type', 'submit') ?>"
								class="btn <?= _iod($values, 'class', 'btn-primary') ?>" name="<?= $name ?>"
								value="1" <?= _iod($values, 'extra', '') ?>>
							<?php $this->renderIcon(_iod($values, 'icon')) ?>&nbsp;<?= _iod($values, 'text') ?>
						</button>
						<?php
					}
					?>
					<?php $this->renderHelpText($helpText) ?>
				</div>
			</div>
			<?php if ($this->usingFormGroup) { ?>
			</div>
			<?php
		}
	}

	private function renderLabel($label, $req = false) {
		?><label class="<?= $this->labelClass ?> control-label<?= $req ? ' required' : '' ?>">
		<?= $label ?>
		</label>
		<?php
	}

	private final function renderHelpText($helpText) {
		if ($helpText) {
			?>
			<small class="help-block"><?= $helpText ?></small><?php
		}
	}

	protected final function setUsingFormGroup($use) {
		$this->usingFormGroup = $use;
		return $this;
	}

	protected final function renderBackTo($id, $title, $size = 6) {
		?>
		<div>
			<a href="#<?= $id ?>">
				<h<?= $size ?> class="glyphicon glyphicon-chevron-up" title="Back to top <?= $title ?>"></h<?= $size ?>>
			</a>
			<a href="#<?= $id ?>" class="pull-right">
				<h<?= $size ?> class="glyphicon glyphicon-chevron-up" title="Back to top <?= $title ?>"></h<?= $size ?>>
			</a>
		</div>
		<?php
	}

	/**
	 * Shortcut method to a JS file being in /resources/js/
	 * @param type $name the name of the javascript file without its extension
	 */
	protected final function addJavascriptFile($name) {
		MainView::getInstance()->addJSFile("/resources/js/$name.js");
	}

	protected final function renderSuccess($msg, $dismissable = false) {
		$this->renderAlert($msg, 'success', $dismissable);
	}

	protected final function renderInfo($msg, $dismissable = false) {
		$this->renderAlert($msg, 'info', $dismissable);
	}

	protected final function renderWarning($msg, $dismissable = false) {
		$this->renderAlert($msg, 'warning', $dismissable);
	}

	protected final function renderError($msg, $dismissable = false) {
		$this->renderAlert($msg, 'danger', $dismissable);
	}

	protected final function renderAlert($msg, $level, $dismissable) {
		?>
		<div class="fade in alert alert-<?= $level, ($dismissable ? ' alert-dismissable' : ' jumbotron') ?>" 
			 tabindex="-1">
				 <?php if ($dismissable) { ?>
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			<?php } ?>
			<span class="glyphicon glyphicon-<?= self::$icons[$level] ?>"></span>
			<?= nl2br($msg) ?>
		</div>
		<?php
	}

	public function renderHelp() {
		?>
		<dl class="dl-horizontal">
			<?php foreach ($this->getHelpItems() as $t => $d) { ?>
				<dt><?= $t ?></dt>
				<dd><?= $d ?></dd>
			<?php } ?>
		</dl>
		<?php
	}

	protected function getHelpItems() {
		return [] ;
	}

}
