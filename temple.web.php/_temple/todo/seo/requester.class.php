<?php

interface Requester extends HTTPGetter {

	function getNumberOfResults() ;

	function setPreferences() ;
	
	function scrapeURLs($query, ProxyModel $proxy = null) ;

}

?>