<?php

namespace temple\web\html\bootstrap;

/**
 * Description of Blockquote
 *
 * @author florent
 */
class Blockquote extends AbstractComponent {

	public function __construct($cssClass = null) {
		parent::__construct('blockquote', $cssClass);
	}
	
	/**
	 * @return Blockquote
	 */
	public function reverse() {
		return $this->addCssClass('blockquote-reverse') ;
	}
	
	/**
	 * 
	 * @param mixed $paragraph
	 * @return Blockquote
	 */
	public function addParagraph($paragraph) {
		return $this->addChild($this->createHTMLNode('p', null, $paragraph)) ;
	}
	
	/**
	 * 
	 * @param mixed $footer
	 * @param mixed $source
	 * @return Blockquote
	 */
	public function addFooter($footer, $source = null) {
		if($footer || $source) {
			$f = $this->createHTMLNode('footer', null, $footer) ;
			if($source) {
				$f->addChild($this->createHTMLNode('cite', null, $source)->setTitle($source)) ;
			}
			$this->addChild($f) ;
		}
		return $this ;
	}
	
	/**
	 * 
	 * @param array $paragraphs
	 * @param mixed $footer
	 * @param mixed $source
	 * @param string $cssClass
	 * @return Blockquote
	 */
	public static function create(array $paragraphs, $footer = null, $source = null, $cssClass = null) {
		$b = new Blockquote($cssClass) ;
		foreach($paragraphs as $p) {
			$b->addParagraph($p) ;
		}
		return $b->addFooter($footer, $source) ;
	}

}
