<?php

namespace temple\web\html\bootstrap;

use temple\web\html\HTMLNode as HN;
use temple\web\html\HTMLNodeFactory as NF;

/**
 * Description of Media
 *
 * @author florent
 */
class Media extends AbstractComponent {

	/**
	 * @var HN
	 */
	private $body;

	/**
	 * 
	 * @param type $href
	 * @param \temple\web\html\HTMLNode $media
	 * @param type $heading
	 * @param type $cssClass
	 */
	public function __construct($href, HN $media, $heading, $cssClass=null, $mediaCssClass='media-left', $bodyCssClass=null) {
		parent::__construct('div', 'media');
		$this->body = $this->createHTMLNode('div', 'media-body', $this->createHTMLNode('h4', 'media-heading', $heading))->addCssClass($bodyCssClass);
		$this->addCssClass($cssClass)
				->addChild(Link::create($href, $media->addCssClass('media-object'), $mediaCssClass))
				->addChild($this->body);
	}

	/**
	 * 
	 * @param type $content
	 * @return Media
	 */
	public function addToBody($content) {
		$this->body->addChild($this->toHTMLElement($content));
		return $this;
	}

	/**
	 * 
	 * @param type $href
	 * @param type $src
	 * @param type $alt
	 * @param type $heading
	 * @param type $body
	 * @return HN
	 */
	public static function createImage($href, $src, $alt, $heading, $body, $mediaCssClass='media-left', $bodyCssClass=null) {
		$m = new Media($href, NF::createImgNode($src, $alt), $heading, null, $mediaCssClass, $bodyCssClass);
		return $m->addToBody($body);
	}

	/**
	 * 
	 * @param type $href
	 * @param type $text
	 * @param type $heading
	 * @param type $body
	 * @return Media
	 */
	public static function createText($href, $text, $heading, $body) {
		$m = new Media($href, NF::createNodeWithString('div', $text), $heading);
		return $m->addToBody($body);
	}

	/**
	 * 
	 * @param type $href
	 * @param HN $node
	 * @param type $heading
	 * @param type $body
	 * @return Media
	 */
	public static function create($href, HN $node, $heading, $body) {
		$m = new Media($href, $node, $heading);
		return $m->addToBody($body);
	}

}
