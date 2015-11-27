<?php

namespace temple\web\html\bootstrap;

/**
 * Description of Link
 *
 * @author florent
 */
class Link extends AbstractComponent {

	/**
	 * 
	 * @param mixed $href
	 * @param mixed $inner
	 * @param string $cssClass
	 */
	public function __construct($href, $inner = null, $cssClass = null) {
		parent::__construct('a', $cssClass) ;
		$this->setAttribute('href', $href) ;
		if($inner) {
			$this->addChild(self::htmlElement($inner)) ;
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
		return new Link($href, $content, $cssClass) ;
	}
	
	/**
	 * 
	 * @param type $href
	 * @param mixed $e
	 * @param CssVariant $variant
	 * @param type $cssClass
	 * @return Link
	 */
	public static function createButton($href, $e = null, CssVariant $variant = null, $cssClass = null) {
		return Link::create($href, $e, $cssClass)->setAttribute('role', 'button')->addCompositeCssClass('btn', _dif($variant, CssVariant::$DEFAULT)) ;
	}
	
	/**
	 * 
	 * @param string $email
	 * @param string $cssClass
	 * @return \temple\web\html\bootstrap\Link
	 */
	public static function createEmail($email, $cssClass=null) {
		return new Link('mailto:'.$email, self::htmlElement($email), $cssClass) ;
	}
	
	/**
	 * 
	 * @param string $tel
	 * @param string $cssClass
	 * @return \temple\web\html\bootstrap\Link
	 */
	public static function createTel($tel, $content = null, $cssClass=null) {
		return new Link('tel:'.$tel, $content === null ? self::htmlElement($tel) : $content, $cssClass) ;
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
