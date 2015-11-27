<?php

namespace temple\web\html\bootstrap ;

use temple\controller\Config ;
use temple\controller\Controller ;

use temple\view\UserAction ;

/**
 * Description of ViewUtil
 *
 * @author flominou
 */
final class ViewUtil {

	private function __construct() {
		
	}

	/**
	 * 
	 * @param type $controllerClass
	 * @return \temple\web\html\bootstrap\Link
	 */
	public static function createLink(UserAction $a) {
		return Link::create(Config::getURL($a->getControllerClass()), $a);
	}

	/**
	 * 
	 * @param mixed $controller  \temple\controller\Controller | string 
	 * @param boolean $confirm
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @param boolean $showText
	 * @return Button
	 */
	public static function createSubmit($controller , $confirm = false, CssVariant $variant = null) {
		$c = $controller instanceof Controller ? $controller : self::newIntance($controller ) ;
		$b = Button::createSubmit2($c->getIcon(), $c->getName(), $variant) ;
		if($confirm) {
			$b->setToConfirm() ;
		}
		return $b ;
	}
	
	/**
	 * 
	 * @param mixed $controller  \temple\controller\Controller | string 
	 * @param boolean $confirm
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @param boolean $showText
	 * @return Button
	 */
	public static function createSubmit2(UserAction $a, $confirm = false, CssVariant $variant = null) {
		$b = Button::createSubmit($a, $variant) ;
		if($confirm) {
			$b->setToConfirm() ;
		}
		return $b ;
	}
	
	/**
	 * 
	 * @param string $controllerClass
	 * @param array $fields
	 * @param boolean $confirm
	 * @param CssVariant $variant
	 * @return Form
	 */
	public static function createAjaxForm($controllerClass, array $fields, $confirm = false, CssVariant $variant = null) {
		$c = self::newIntance($controllerClass);
		$all = $c->getLocales() ;
		$f = Form::createAjaxForm(Config::getURL($controllerClass)) ;
		foreach($fields as $field) {
			if($field instanceof Input && $field->getAttribute('type') == 'hidden') {
				$f->addField($field) ;
			} else {
				$f->addFormGroup($all[$field->getName()], $field) ;
			}
		}
		$f->addField(self::createSubmit($c, $confirm, $variant)) ;
		return $f;
	}

	/**
	 * 
	 * @param UserAction $a
	 * @param array $fields
	 * @param boolean $confirm
	 * @param CssVariant $variant
	 * @return Form
	 */
	public static function createAjaxForm2(UserAction $a, array $fields, $confirm = false, CssVariant $variant = null) {
		$controllerClass = $a->getControllerClass() ;
		$all = &$a->getFieldsLocale() ;
		$f = Form::createAjaxForm(Config::getURL($controllerClass)) ;
		foreach($fields as $field) {
			if($field instanceof Input && $field->getAttribute('type') == 'hidden') {
				$f->addField($field) ;
			} else {
				$f->addFormGroup($all[$field->getName()], $field) ;
			}
		}
		$f->addField(self::createSubmit2($a, $confirm, $variant)) ;
		return $f;
	}
	

	
	/**
	 * 
	 * @param string $class
	 * @return \temple\controller\Controller
	 */
	private static function newIntance($class) {
		return new $class ;
	}
	
}
