<?php

abstract class AbstractRequester extends DefaultHTTPGetter implements Requester {

	private $queryFormat ;
	
	private $numberOfResults ;

	private $urlsXPath ;
	
	public function __construct($host, $queryFormat, $urlsXPath, $nr, $uc) {
		parent::__construct($host, $uc) ;
		$this->numberOfResults = $nr ;
		$this->queryFormat = $queryFormat ;
		$this->urlsXPath = $urlsXPath ;
	}
	
	public final function getNumberOfResults() {
		return $this->numberOfResults ;
	}
	
	public function scrapeURLs($query, ProxyModel $proxy = null) {
		$data = $this->get(sprintf($this->queryFormat, urlencode($query), $this->numberOfResults), $proxy) ;
		echo $data ;
		$doc = new DOMDocument() ;
		@$doc->loadHTML($data) ;
		$xpath = new DOMXPath($doc) ;
		$urls = array() ;
		foreach($xpath->query($this->urlsXPath) as $node) {
			$href = urldecode($node->nodeValue) ; 
			$urls[] = substr($href, strpos($href, 'http')) ;
		}
		return $urls ;
	}

}

?>