<?php

namespace web ;

interface HTMLDocument {

	// return string
	function getURL() ;

	// return boolean
	function xpathExist($xpath) ;
	
	// return integer
	function xpathCount($xpath) ;
	
	// return DOMNodeList
	function xpathQuery($xpath) ;

	function save($path) ;
	
}

?>