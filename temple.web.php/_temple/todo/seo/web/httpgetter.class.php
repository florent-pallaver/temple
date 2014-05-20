<?php

namespace web ;

interface HTTPGetter {

	function getHost() ;

	// throws HTTPGetException
	function get($query, \ProxyModel $proxy = null) ;

}

?>