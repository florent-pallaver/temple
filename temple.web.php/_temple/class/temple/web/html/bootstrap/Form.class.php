<?php

namespace temple\web\html\bootstrap;

/**
 * Description of Form
 *
 * @author florent
 */
class Form extends AbstractComponent {

	const FIELDSET_KEY = '_fieldset|' ;
	
	public function __construct($action, $cssClass = null, $method = 'post') {
		parent::__construct('form');
		$this->getId() ; // forces id generation
		$this->setAttributes(['method'=>$method, 'action' => $action]) ;
		$this->addNode(ComponentFactory::createComponent('fieldset', self::JQUERYFIELD_CSS_CLASS), self::FIELDSET_KEY)
			->setAttribute('disabled', 'disabled') ;
	}
	
	/**
	 * 
	 * @param \temple\web\html\bootstrap\FormComponent $comp
	 * @return \temple\web\html\bootstrap\Form
	 */
	public function addField(Component $comp) {
		$this->getChild(self::FIELDSET_KEY)->addChild($comp) ;
		if(false) {
			$this->setAttribute('enctype', 'multipart/form-data') ;
		}
		return $this ;
	}
	
}
