<?php

namespace temple\web\html\bootstrap;

use temple\web\html\HTMLNodeFactory as NF;
use temple\web\html\HTMLElement ;

/**
 * Description of HTML5Document
 *
 * @author florent
 * @deprecated since version alpha charger les assets dans un HTML5Document plutot
 */
class HTML5Document extends \temple\web\html\HTML5Document {

	const HEADER_ID = '_documentHeader';
	const MAIN_ID = '_documentMain';
	const FOOTER_ID = '_documentFooter';

	public static $BOOTSTRAP_VERSION = '3_3_5' ;
	public static $FONT_AWESOME_VERSION = '4_4_0' ;
	public static $JQUERY_VERSION = '2_1_1' ;
        
	private static $modalsKey = '_modals|' ;
	private static $scriptsKey = '_scripts|' ;
	
	public function __construct($title, $author = '', $description = '', $headerClass = 'container') {
		parent::__construct($title, $author, $description);
		$rp = \temple\web\Config::TEMPLE_RESOURCE_PATH ;
		$this->addMeta('viewport', 'width=device-width, initial-scale=1.0') ;
		$this->addCssLink($rp . 'bootstrap/' . self::$BOOTSTRAP_VERSION . '/css/bootstrap.min.css')
				->addCssLink($rp . 'font-awesome/' . self::$FONT_AWESOME_VERSION . '/css/font-awesome.min.css')
				->addCssLink($rp . 'css/temple.min.css');
		$this->getBody()
				->addChild(NF::createNode('header', ['class'=> $headerClass, 'id'=>self::HEADER_ID]), self::HEADER_ID) 
				->addChild(NF::createNode('main', ['class'=>'container', 'id'=>self::MAIN_ID]), self::MAIN_ID) 
				->addChild(NF::createNode('footer', ['class'=>'container', 'id'=>self::FOOTER_ID]), self::FOOTER_ID) 
				->addChild(new \temple\web\html\HTMLElementList(), self::$modalsKey)
				->addChild(new \temple\web\html\HTMLElementList(), self::$scriptsKey)
			;
		$this->getMain()->addChild(new AnchorLink($this->getBody(), \temple\web\html\IconFactory::getInstance()->createIcon('chevron-up'), '_backToTop')) ;
		$this->addScriptLink($rp . 'jquery/' . self::$JQUERY_VERSION . '/js/jquery.min.js') ;
//		$this->addScriptLink('//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js') ;
		$this->addScriptLink($rp . 'bootstrap/' . self::$BOOTSTRAP_VERSION . '/js/bootstrap.min.js') ;
		$this->addScriptLink($rp . 'js/jqueryField.min.js') ;
		$this->addScriptLink($rp . 'js/backToTop.min.js') ;
		$this->addScriptLink($rp . 'js/TempleAlert.min.js') ;
		$this->addScriptLink($rp . 'js/TempleForm.min.js') ;
		$this->addScript('TempleForm.CONFIRM_MESSAGE = \'' . \temple\view\ViewLocale::CONFIRM_MESSAGE . '\' ;') ;
	}

	/**
	 * @return \temple\web\html\HTMLNode ;
	 */
	public final function getHeader() {
		return $this->getBody()->getChild(self::HEADER_ID) ;
	}

	/**
	 * @return \temple\web\html\HTMLNode ;
	 */
	public final function getMain() {
		return $this->getBody()->getChild(self::MAIN_ID) ;
	}

	/**
	 * @return \temple\web\html\HTMLNode ;
	 */
	public final function getFooter() {
		return $this->getBody()->getChild(self::FOOTER_ID) ;
	}

	/**
	 * 
	 * @param mixed $title
	 * @param \temple\web\html\HTMLElement $body
	 * @param string $cssClass
	 * @param \temple\web\html\HTMLElement $footer
	 * @return \temple\web\html\bootstrap\Modal
	 */
	public final function addModal($title, HTMLElement $body, $cssClass = '', HTMLElement $footer = null) {
		$m = new Modal($title, $body, $footer, $cssClass) ;
		$this->getBody()->getChild(self::$modalsKey)->addElement($m) ;
		return $m ;
	}

	public final function addScript($script) {
		$this->getBody()->getChild(self::$scriptsKey)
				->addElement(NF::createScriptNode()
						->addChild(new \temple\web\html\HTMLString($script, true))) ;
		return $this ;
	}

	public final function addScriptLink($src) {
		$this->getBody()->getChild(self::$scriptsKey)
				->addElement(NF::createNode('script', ['src'=>$src])) ;
		return $this ;
	}

	public final function addCssLink($src) {
		$this->getHead()->addChild(NF::createCssLinkNode($src)) ;
		return $this ;
	}
}
