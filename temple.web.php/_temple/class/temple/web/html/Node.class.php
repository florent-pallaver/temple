<?php

namespace temple\web\html ;

/**
 * TODOC
 *
 * @author Florent
 */
interface Node extends HTMLElement {

	/**
	 * Tells whether this node has of the given node type.
	 *
	 * @param string $nodeType - a node type.
	 * @return boolean <code>true</code> if this node is a $nodeName node, <code>false</code> otherwise.
	 */
	public function isNode($nodeType) ;

	/**
	 * Return this node's id.
	 * @return string the id of this node, never NULL.
	 */
	public function getId() ;

	/**
	 * 
	 * @param scalar $id
	 * @return Node
	 */
	public function setId($id) ;

	/**
	 * TODOC
	 *
	 * @param string $key - the attribute name
	 * @return string the value of the attribute if set, NULL otherwise.
	 */
	public function getAttribute($key) ;

	/**
	 * TODOC
	 *
	 * @param string $key
	 * @param string $value
	 * @return Node
	 */
	public function setAttribute($key, $value) ;

	/**
	 * TODOC
	 *
	 * @return array:
	 */
	public function getAttributes() ;

	/**
	 * TODOC
	 *
	 * @param array $attributes
	 * @return Node
	 */
	public function setAttributes(array $attributes) ;
	
	/**
	 * 
	 * @param array $data
	 * @return Node
	 */
	public function setData(array $data) ;

	/**
	 * 
	 * @param array $aria
	 * @return Node
	 */
	public function setAria(array $aria) ;
	
	/**
	 * 
	 * @param string $cssClass
	 * @return Node
	 */
	public function addCssClass($cssClass) ;
	
	/**
	 * TODOC
	 *
	 * @return boolean
	 */
	public function hasChildren() ;

	/**
	 * TODOC
	 *
	 * @param HTMLElement $child
	 * @param string $key
	 * @return Node
	 */
	public function addChild(HTMLElement $child = null, $key = null) ;

	/**
	 * TODOC
	 *
	 * @param unknown $children
	 * @param string $useKeys
	 * @return Node
	 */
	public function addAllChildren($children, $useKeys = false) ;
	
	/**
	 * 
	 * @param type $key
	 * @return HTMLElement
	 */
	public function getChild($key) ;
	
}
