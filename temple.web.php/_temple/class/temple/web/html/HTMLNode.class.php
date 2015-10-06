<?php

namespace temple\web\html ;

/**
 * TODOC
 *
 * @author Florent
 */
class HTMLNode extends AbstractHTMLElement implements Node {

	/**
	 * string used for prefixing id creating by default.
	 * @var string
	 */
	public static $defaultIdPrefix = 'htmlNode_' ;

	private static $idCount = 0 ;

	/**
	 * @var string
	 */
	private $nodeType ;

	/**
	 * @var array
	 */
	private $attributes ;

	/**
	 * @var HTMLElementList
	 */
	private $children ;

	/**
	 * Cosntructor.
	 *
	 * @param string $nodeType - the type of the HTMLNode to create.
	 * @param array $attributes - an associative string => string array containing the attributes of the HTMLNode to create.
	 */
	public function __construct($nodeType, array $attributes = [], array $data = []) {
		$this->nodeType = strtolower($nodeType) ;
		$this->attributes = $attributes ;
		$this->setData($data) ;
		$this->children = new HTMLElementList() ;
	}

	protected final function _toString() {
		$rendering = '<' . $this->nodeType ;
		foreach($this->attributes as $k => $v) {
			// TODO html safe attribute and key values !!!
			if($v !== NULL) {
				$rendering .= ' ' . $k . '="' . $v . '"' ;
			}
		}
		if(in_array($this->nodeType, ['br', 'input', 'meta', 'base', 'link'])) {
			//	ONLY FOR XHTML $rendering .= '/' ; 
		} else {
			$rendering .= '>' . $this->children . '</' . $this->nodeType ;
		}
		$rendering .= '>' ;
		return $rendering ;
	}

	protected final function _render() {
		echo '<', $this->nodeType ;
		foreach($this->attributes as $k => $v) {
			// TODO html safe attribute and key values !!!
			if($v !== NULL) {
				echo ' ', $k, '="', $v, '"' ;
			}
		}
		if(in_array($this->nodeType, ['br', 'input', 'meta', 'base', 'link'])) {
			//	ONLY FOR XHTML $rendering .= '/' ; 
		} else {
			echo '>' ;
			$this->children->render() ;
			echo '</', $this->nodeType ;
		}
		echo '>' ;
	}
	
	public final function isNode($nodeType) {
		return $this->nodeType === $nodeType ;
	}

	public final function getId() {
		$id = $this->getAttribute('id') ;
		if($id === NULL) {
			$id = self::getNewId() ;
			$this->setAttribute('id', $id) ;
		}
		return $id ;
	}

	public final function getAttribute($key) {
		return _iod($this->attributes, $key) ;
	}

	public final function setAttribute($key, $value) {
		if($value === NULL) {
			unset($this->attributes[$key]) ;
		} else {
//			if(is_array($value)) {
//				\temple\Logger::getInstance()->debug(print_r($value, true)) ;
//			}
			$this->attributes[$key] = htmlentities($value) ;
		}
		return $this ;
	}

	public final function getAttributes() {
		return $this->attributes ;
	}

	public final function setAttributes(array $attributes) {
		foreach($attributes as $k => $v) {
			$this->setAttribute($k, $v) ;
		}
		return $this ;
	}
	
	public final function setId($id) {
		return $this->setAttribute('id', $id) ;
	}
	
	public final function setData(array $data) {
		foreach($data as $k => $v) {
			$this->setAttribute('data-' . $k, $v) ;
		}
		return $this ;
	}

	public final function setAria(array $aria) {
		foreach($aria as $k => $v) {
			$this->setAttribute('aria-' . $k, $v) ;
		}
		return $this ;
	}

	public final function addCssClass($cssClass) {
		if($cssClass) {
			if(!isset($this->attributes['class'])) {
				$this->setAttribute('class', $cssClass) ;
			} else {
				$this->attributes['class'] .= ' ' . $cssClass ;
			}
		}
		return $this ;
	}
	
	public final function hasChildren() {
		return $this->children->hasElement() ;
	}

	public final function addChild(HTMLElement $child = null, $key = null) {
		$this->children->addElement($child, $key) ;
		return $this ;
	}

	public final function addAllChildren($children, $useKeys = false) {
		if(is_array($children)) {
			foreach($children as $k => $child) {
				$this->addChild($child, $useKeys ? $k : NULL) ;
			}
		} else {
			$this->addChild($children);
		}
		return $this ;
	}

//	public final function removeChild(HTMLElement $child) {
//		foreach($this->children as $k => $c) {
//			if($c === $child) {
//				unset($this->children[$k]) ;
//				break ;
//			}
//		}
//	}

	public final function getChild($key) {
		return $this->children->getElement($key) ;
	}
	
//	public final function getChildren() {
//		return $this->children ;
//	}

	private static final function getNewId() {
		return self::$defaultIdPrefix . self::$idCount ++ ;
	}

}
