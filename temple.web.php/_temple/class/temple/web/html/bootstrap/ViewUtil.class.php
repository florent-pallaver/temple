<?php

namespace temple\web\html\bootstrap ;

use temple\controller\Config ;
use temple\controller\Controller ;

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
	 * @return \temple\web\html\bootstrap\TextLink
	 */
	public static function createLink($controllerClass) {
		$c = self::newIntance($controllerClass) ;
		return new TextLink(Config::getURL($controllerClass), $c->getIcon(), $c->getName());
	}

	/**
	 * 
	 * @param mixed $controller  \temple\controller\Controller | string 
	 * @param boolean $confirm
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @param boolean $showText
	 * @return Button
	 */
	public static function createSubmit($controller , $confirm = false, CssVariant $variant = null, $showText = true) {
		$c = $controller instanceof Controller ? $controller : self::newIntance($controller ) ;
		$b = Button::createSubmit2($c->getIcon(), $showText ? $c->getName() : '', $variant) ;
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
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @param boolean $showText
	 * @return \temple\web\html\bootstrap\Form
	 */
	public static function createAjaxForm($controllerClass, array $fields, $confirm = false, CssVariant $variant = null, $showText = true) {
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
		$f->addField(self::createSubmit($c, $confirm, $variant, $showText)) ;
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
