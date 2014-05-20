<?php

namespace temple\web\html\bootstrap;

use temple\web\html\HTMLNode as HN ;
use temple\web\html\HTMLString as HS ;

/**
 * Description of InnerText
 *
 * @author florent
 */
final class InnerText extends \temple\web\html\HTMLElementList {

	const ICON_KEY = '_icon|' ;
	
	const TEXT_KEY = '_text|' ;

	/**
	 * @var HN
	 */
	private static $CARET ;

	public function __construct($icon, $text = null, $caret = false, $formatted = false) {
		parent::__construct();
		if($icon) {
			$this->addElement(new Icon($icon), self::ICON_KEY) ;
		}
		if($text) {
			if($icon) {
				$this->addElement(HS::$NBSP) ;
			}
			$span = new HN('span') ;
			$this->addElement($span->addChild(new HS($text, $formatted)), self::TEXT_KEY) ;
		}
		if($caret) {
			if($icon || $text) {
				$this->addElement(HS::$NBSP) ;
			}
			$this->addElement(self::$CARET) ;
		}
	}
	
	/**
	 * 
	 * @param type $text
	 * @param type $formatted
	 * @return \temple\web\html\bootstrap\InnerText
	 */
	public static function create($text, $formatted = false) {
		return new InnerText(null, $text, false, $formatted) ;
	}
	
	private static function _init() {
		self::$CARET = new HN('span', ['class'=>'caret']) ;
	}

}
