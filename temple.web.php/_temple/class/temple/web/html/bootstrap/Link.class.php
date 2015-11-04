<?php

namespace temple\web\html\bootstrap;

use temple\web\html\HTMLElement ;
use temple\web\html\HTMLUtil ;

/**
 * Description of Link
 *
 * @author florent
 */
class Link extends AbstractComponent {

	/**
	 * 
	 * @param type $href
	 * @param type $icon
	 * @param type $text
	 * @param type $cssClass
	 */
	public function __construct($href, HTMLElement $e = null, $cssClass = null) {
		parent::__construct('a', $cssClass) ;
		$this->setAttribute('href', $href) ;
		if($e) {
			$this->addChild($e) ;
		}
	}

	/**
	 * @param type $target
	 * @return Link
	 */
	public function setTarget($target='_blank') {
		return $this->setAttribute('target', $target) ;
	}
	
	/**
	 * 
	 * @param type $href
	 * @param type $cssClass
	 * @return \temple\web\html\bootstrap\Link
	 */
	public static function create($href, $content, $cssClass = null) {
		return new Link($href, HTMLUtil::toHTMLElement($content), $cssClass) ;
	}
	
	/**
	 * 
	 * @param type $href
	 * @param mixed $e
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @param type $cssClass
	 * @return Link
	 */
	public static function createButton($href, $e = null, CssVariant $variant = null, $cssClass = null) {
		$l = Link::create($href, $e, $cssClass) ;
		return $l->setAttribute('role', 'button')->addCompositeCssClass('btn', _dif($variant, CssVariant::$DEFAULT)) ;
	}
	
	/**
	 * 
	 * @param string $email
	 * @param string $cssClass
	 * @return \temple\web\html\bootstrap\Link
	 */
	public static function createEmail($email, $cssClass=null) {
		return new Link('mailto:'.$email, HTMLUtil::toHTMLElement($email), $cssClass) ;
	}
	
	/**
	 * 
	 * @param string $tel
	 * @param string $cssClass
	 * @return \temple\web\html\bootstrap\Link
	 */
	public static function createTel($tel, $content = null, $cssClass=null) {
		return new Link('tel:'.$tel, $content === null ? HTMLUtil::toHTMLElement($tel) : $content, $cssClass) ;
	}

	/**
	 * 
	 * @param string $tel
	 * @param string $content
	 * @param string $cssClass
	 * @return \temple\web\html\bootstrap\Link
	 */
	public static function createSMS($tel, $content = null, $cssClass=null) {
		return new Link('sms:'.$tel, $content, $cssClass) ;
	}
}
