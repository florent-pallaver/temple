<?php

namespace temple\web\html\bootstrap;

/**
 * Description of Form
 *
 * @author florent
 */
class Form extends AbstractComponent {

	const FIELDSET_KEY = '_fieldset|' ;
	
	const SUCCESS_JS_CALLBACK = 'on-success' ;
	
	const AJAX_FORM_CLASS = '_temple-form' ;
	
	private $labelCssClass ;
	
	private $fieldCssClass ;
	
	private $useCssClasses;
	
	/**
	 * @var \temple\web\html\HTMLNode
	 */
	private $lastFieldset ;

	public function __construct($action, $cssClass = null, $ajax = true, $fieldsetCssClass = null, $disabled = false) {
		parent::__construct('form', $cssClass);
		$this->fieldCssClass = null ;
		$this->labelCssClass = null ;
		$this->useCssClasses = true ;
//		$this->getId() ; // forces id generation
		if($ajax) {
			$this->addCssClass(self::AJAX_FORM_CLASS) ;
		}
		$this->setAttributes(['method'=>'post', 'action' => $action]) ;
		$this->addNewFieldSet($fieldsetCssClass, $disabled) ;
	}
	
	/**
	 * 
	 * @return \temple\web\html\HTMLNode
	 */
	public function getLastFieldset() {
		return $this->lastFieldset ;
	}
	
	/**
	 * @return Form
	 */
	public function addNewFieldSet($cssClass = null, $disabled = false) {
		$this->lastFieldset = ComponentFactory::createFieldset(true, $cssClass, !$disabled) ;
		return $this->addChild($this->lastFieldset) ;
	}
	
	/**
	 * 
	 * @param type $jsFunction
	 * @param array $parameters
	 * @return Form
	 */
	public function setSuccessJSCallback($jsFunction, array $parameters = []) {
		return $this->setData([self::SUCCESS_JS_CALLBACK=>$jsFunction])
				->setData($parameters) ;
	}
	
	/**
	 * 
	 * @param boolean $with
	 * @return Form
	 */
	public function setWithFileUpload($with) {
		return $this->setAttribute('enctype', $with ? 'multipart/form-data' : null) ;
	}
	
	/**
	 * 
	 * @param \temple\web\html\bootstrap\Component $comp
	 * @return \temple\web\html\bootstrap\Form
	 */
	public function addField(\temple\web\html\HTMLNode $comp) {
		$this->lastFieldset->addChild($comp) ;
		if(false) {
		}
		return $this ;
	}
	
	/**
	 * 
	 * @param type $labelCssClass
	 * @return \temple\web\html\bootstrap\Form
	 */
	public function setDefaultLabelCssClass($labelCssClass) {
		$this->labelCssClass = $labelCssClass ;
		return $this ;
	}
	
	/**
	 * 
	 * @param type $fieldCssClass
	 * @return \temple\web\html\bootstrap\Form
	 */
	public function setDefaultFieldCssClass($fieldCssClass) {
		$this->fieldCssClass = $fieldCssClass ;
		return $this ;
	}
	
	/**
	 * Toggles use of css classes on labels and fields when adding a form group.
	 * @return \temple\web\html\bootstrap\Form
	 */
	public function toggleCssClassesUse() {
		$this->useCssClasses = !$this->useCssClasses ;
		return $this ;
	}
	
	/**
	 * 
	 * @param type $label
	 * @param \temple\web\html\bootstrap\FormField $field
	 * @param type $labelCssClass
	 * @param type $fieldCssClass
	 * @param FormGroup $fg the created FormGroup
	 * @return Form
	 */
	public function addFormGroup($label, FormField $field, $labelCssClass = null, $fieldCssClass = null, FormGroup &$fg = null) {
		$fg = new FormGroup($field, $label, false) ;
		if($this->useCssClasses) {
			$fg->getLabel()->addCssClass(_dif($labelCssClass, $this->labelCssClass)) ;
			$fg->getFieldDiv()->addCssClass(_dif($fieldCssClass, $this->fieldCssClass)) ;
		}
		return $this->addField($fg) ;
	}
	
	/**
	 * Uses the Input's placeholder as label
	 * @param \temple\web\html\bootstrap\Input $input
	 * @param type $labelCssClass
	 * @param type $fieldCssClass
	 * @param FormGroup $fg the created FormGroup
	 * @return Form
	 */
	public function addInputInFormGroup(Input $input, $labelCssClass = null, $fieldCssClass = null, FormGroup &$fg = null) {
		return $this->addFormGroup($input->getAttribute('placeholder'), $input, $labelCssClass, $fieldCssClass, $fg ) ;
	}
	
	/**
	 * Uses the Input's placeholder as label
	 * @param \temple\web\html\bootstrap\Input $input
	 * @param type $labelCssClass
	 * @param type $fieldCssClass
	 * @param FormGroup $fg the created FormGroup
	 * @return Form
	 */
	public function addInputGroupInFormGroup(InputGroup $input, $labelCssClass = null, $fieldCssClass = null, FormGroup &$fg = null) {
		return $this->addFormGroup($input->getInput()->getAttribute('placeholder'), $input, $labelCssClass, $fieldCssClass, $fg ) ;
	}
	
	/**
	 * Uses the TextArea's placeholder as label
	 * @param \temple\web\html\bootstrap\TextArea $textArea
	 * @param type $labelCssClass
	 * @param type $fieldCssClass
	 * @param FormGroup $fg the created FormGroup
	 * @return Form
	 */
	public function addTextAreaInFormGroup(TextArea $textArea, $labelCssClass = null, $fieldCssClass = null, FormGroup &$fg = null) {
		return $this->addFormGroup($textArea->getAttribute('placeholder'), $textArea, $labelCssClass, $fieldCssClass, $fg ) ;
	}
	/**
	 * 
	 * @param type $action
	 * @param type $cssClass
	 * @param type $fieldsetCssClass
	 * @param boolean $disabled
	 * @return \temple\web\html\bootstrap\Form
	 */
	public static function createAjaxForm($action, $cssClass = null, $fieldsetCssClass = null, $disabled = false) {
		return new Form($action, $cssClass, true, $fieldsetCssClass, $disabled) ;
	}
	
	/**
	 * 
	 * @param type $action
	 * @param type $cssClass
	 * @param type $fieldsetCssClass
	 * @return \temple\web\html\bootstrap\Form
	 */
	public static function createForm($action, $cssClass = null, $fieldsetCssClass = null) {
		return new Form($action, $cssClass, false, $fieldsetCssClass) ;
	}
	
}
