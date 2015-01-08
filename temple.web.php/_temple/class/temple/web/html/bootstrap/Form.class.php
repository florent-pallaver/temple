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
	
	/**
	 * @var \temple\web\html\HTMLNode
	 */
	private $lastFieldset ;
	
	public function __construct($action, $cssClass = null, $ajax = true, $fieldsetCssClass = null) {
		parent::__construct('form', $cssClass);
		$this->fieldCssClass = null ;
		$this->labelCssClass = null ;
//		$this->getId() ; // forces id generation
		if($ajax) {
			$this->addCssClass(self::AJAX_FORM_CLASS) ;
		}
		$this->setAttributes(['method'=>'post', 'action' => $action]) ;
		$this->addNewFieldSet($fieldsetCssClass) ;
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
	public function addNewFieldSet($cssClass = null) {
		$this->lastFieldset = ComponentFactory::createFieldset(true, $cssClass) ;
		return $this->addChild($this->lastFieldset) ;
	}
	
	/**
	 * 
	 * @param type $jsFunction
	 * @param array $parameters
	 * @return \temple\web\html\bootstrap\Form
	 */
	public function setSuccessJSCallback($jsFunction, array $parameters = []) {
		return $this->setData([self::SUCCESS_JS_CALLBACK=>$jsFunction])
				->setData($parameters) ;
	}
	
	/**
	 * 
	 * @param \temple\web\html\bootstrap\Component $comp
	 * @return \temple\web\html\bootstrap\Form
	 */
	public function addField(\temple\web\html\HTMLNode $comp) {
		$this->lastFieldset->addChild($comp) ;
		if(false) {
			$this->setAttribute('enctype', 'multipart/form-data') ;
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
	 * 
	 * @param type $label
	 * @param \temple\web\html\bootstrap\FormField $field
	 * @param type $labelCssClass
	 * @param type $fieldCssClass
	 * @return Form
	 */
	public function addFormGroup($label, FormField $field, $labelCssClass = null, $fieldCssClass = null) {
		$fg = new FormGroup($field, $label, false) ;
		$fg->getLabel()->addCssClass(_dif($labelCssClass, $this->labelCssClass))
				->addCssClass($field->isRequired() ? 'required': null);
		$fg->getFieldDiv()->addCssClass(_dif($fieldCssClass, $this->fieldCssClass)) ;
		return $this->addField($fg) ;
	}
	
	/**
	 * 
	 * @param type $action
	 * @param type $cssClass
	 * @param type $fieldsetCssClass
	 * @return \temple\web\html\bootstrap\Form
	 */
	public static function createAjaxForm($action, $cssClass = null, $fieldsetCssClass = null) {
		return new Form($action, $cssClass, true, $fieldsetCssClass) ;
	}
	
	/**
	 * 
	 * @param type $action
	 * @param type $cssClass
	 * @return \temple\web\html\bootstrap\Form
	 */
	public static function createForm($action, $cssClass = null) {
		return new Form($action, $cssClass, false) ;
	}
	
}
