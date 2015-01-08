<?php

namespace temple\web\html;

/**
 * Description of HTML5Document
 *
 * @author florent
 */
class HTML5Document implements htmldocument {

	const DOCTYPE = '<!DOCTYPE html>' ;
	
	private static $titleKey = '_title|' ;
	
	/**
	 * @var HTMLNode
	 */
	private $head ;
	
	/**
	 * @var HTMLNode
	 */
	private $body ;

	public function __construct($title, $author = '', $description = '') {
		$this->head = new HTMLNode('head') ;
		$this->head->addChild(new HTMLNode('meta', ['charset' => self::CHARSET_UTF_8])) ;
		$this->addMeta('author', $author) ;
		$this->addMeta('description', $description) ;
		$this->head->addChild(HTMLNodeFactory::createNodeWithString('title', $title), self::$titleKey) ;
		$this->body = new HTMLNode('body') ;
	}
	
	public final function getTitle() {
		return $this->head->getChild(self::$titleKey) ;
	}
	
	public final function getHead() {
		return $this->head;
	}

	public final function getBody() {
		return $this->body;
	}

	/**
	 * @param type $name
	 * @param type $content
	 * @return \temple\web\html\HTML5Document
	 */
	public final function addMeta($name, $content) {
		if($content) {
			$this->head->addChild(new HTMLNode('meta', ['name'=>$name, 'content'=>$content])) ;
		}
		return $this ;
	}
	
	public function render() {
		$html = new HTMLNode('html') ;
		$html->addChild($this->head)->addChild($this->body) ;
		echo self::DOCTYPE, PHP_EOL;
		$html->render() ;
	}
	
}
