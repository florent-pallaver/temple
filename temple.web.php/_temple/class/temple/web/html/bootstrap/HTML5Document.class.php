<?php

namespace temple\web\html\bootstrap;

use temple\web\html\HTMLNodeFactory as NF;
use temple\web\html\HTMLElement ;

/**
 * Description of HTML5Document
 *
 * @author florent
 */
class HTML5Document extends \temple\web\html\HTML5Document {

	const HEADER_ID = '_documentHeader';
	const MAIN_ID = '_documentMain';
	const FOOTER_ID = '_documentFooter';

	private static $modalsKey = '_modals|' ;
	private static $scriptsKey = '_scripts|' ;
	
	public function __construct($title, $author = '', $description = '') {
		parent::__construct($title, $author, $description);
		$rp = \temple\web\Config::TEMPLE_RESOURCE_PATH ;
		$this->addMeta('viewport', 'width=device-width, initial-scale=1.0') ;
		$this->addCssLink($rp . 'bootstrap/3_3_1/css/bootstrap.min.css')
				->addCssLink($rp . 'css/temple.min.css');
		$this->getBody()
				->addChild(NF::createNode('header', ['class'=>'container', 'id'=>self::HEADER_ID]), self::HEADER_ID) 
				->addChild(NF::createNode('main', ['class'=>'container', 'id'=>self::MAIN_ID]), self::MAIN_ID) 
				->addChild(NF::createNode('footer', ['class'=>'container', 'id'=>self::FOOTER_ID]), self::FOOTER_ID) 
				->addChild(new \temple\web\html\HTMLElementList(), self::$modalsKey)
				->addChild(new \temple\web\html\HTMLElementList(), self::$scriptsKey)
			;
		$this->getMain()->addChild(new AnchorLink($this->getBody(), new InnerText('chevron-up'), '_backToTop')) ;
		$this->addScriptLink($rp . 'jquery/2_1_1/js/jquery.min.js') ;
//		$this->addScriptLink('//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js') ;
		$this->addScriptLink($rp . 'bootstrap/3_3_1/js/bootstrap.min.js') ;
		$this->addScriptLink($rp . 'js/jqueryField.min.js') ;
		$this->addScriptLink($rp . 'js/backToTop.min.js') ;
		$this->addScriptLink($rp . 'js/TempleAlert.min.js') ;
		$this->addScriptLink($rp . 'js/TempleForm.min.js') ;
	}

	/**
	 * @return \temple\web\html\HTMLNode ;
	 */
	public function getHeader() {
		return $this->getBody()->getChild(self::HEADER_ID) ;
	}

	/**
	 * @return \temple\web\html\HTMLNode ;
	 */
	public function getMain() {
		return $this->getBody()->getChild(self::MAIN_ID) ;
	}

	/**
	 * @return \temple\web\html\HTMLNode ;
	 */
	public function getFooter() {
		return $this->getBody()->getChild(self::FOOTER_ID) ;
	}

	/**
	 * 
	 * @param type $icon
	 * @param type $title
	 * @param \temple\web\html\HTMLElement $body
	 * @param type $cssClass
	 * @param \temple\web\html\HTMLElement $footer
	 * @return \temple\web\html\bootstrap\Modal
	 */
	public function addModal($icon, $title, HTMLElement $body, $cssClass = '', HTMLElement $footer = null) {
		$m = new Modal($icon, $title, $body, $footer, $cssClass) ;
		$this->getBody()->getChild(self::$modalsKey)->addElement($m) ;
		return $m ;
	}

	public function addScript($script) {
		
	}

	public function addScriptLink($src) {
		$this->getBody()->getChild(self::$scriptsKey)
				->addElement(NF::createNode('script', ['src'=>$src])) ;
		return $this ;
	}

	public function addCssLink($src) {
		$this->getHead()->addChild(NF::createCssLinkNode($src)) ;
		return $this ;
	}
}
