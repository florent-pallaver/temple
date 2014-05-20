<?php

namespace web ;

use \data\model\ProxyModel ;

class DefaultHTTPGetter implements HTTPGetter {

	private static $timeOut = 10 ;

	private static $options = array(
			CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1, CURLOPT_HEADER => false, CURLOPT_RETURNTRANSFER => true,
			CURLOPT_USERAGENT => 'User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0', 
			CURLOPT_ENCODING => 'gzip,deflate', CURLOPT_FOLLOWLOCATION => true,	CURLOPT_AUTOREFERER => true
		) ;
	
	private $host ;

	private $rootURL ;

	private $useCookies ;
	
	private $headers = array(
			'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8', 
			'Accept-Charset: utf-8;q=0.7,*;q=0.3', 
			'Accept-Language: en-US,en;q=0.5', 
			'Connection: keep-alive', 
			'Keep-Alive: 300',
			'Cache-Control: max-age=0'
		) ;
	
	public function __construct($host, $uc) {
		$this->host = $host ;
		$this->headers[] = 'Host: '. $host ;
		$this->rootURL = 'http://' . $host . '/';
		$this->useCookies = $uc ;
	}
	
	public final function getHost() {
		return $this->host ;
	}
	
	// throws HTTPGetException
	public final function get($query, ProxyModel $proxy = null) {
		$curl = curl_init() ;
		curl_setopt_array($curl, self::$options) ;
		curl_setopt($curl, CURLOPT_HTTPHEADER, $this->headers) ;
		curl_setopt($curl, CURLOPT_TIMEOUT, self::$timeOut) ;
		
		if($proxy) {
			curl_setopt($curl, CURLOPT_PROXY, $proxy) ;
		}
		
		if($this->useCookies) {
			$cookie = dirname(__FILE__) . '/cookies/' . ($proxy ? $proxy->getIPAsString().'_'.$proxy->port : 'local'). '.txt' ;
			curl_setopt($curl, CURLOPT_COOKIEJAR, $cookie) ;
			curl_setopt($curl, CURLOPT_COOKIEFILE, $cookie) ;
			curl_setopt($curl, CURLOPT_URL, $this->rootURL) ;

			curl_exec($curl) ;
		}
		
		curl_setopt($curl, CURLOPT_URL, strpos($query, 'http') === false ? $this->rootURL . $query : $query) ;

		$data = curl_exec($curl) ;
		
		if(curl_errno($curl) == CURLE_OK) {
			$httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE) ;
			switch($httpCode) {
				case 200:
					break ;
				case 403: case 502: case 503: case 999:
				default:
					$e = new HTTPGetException($httpCode) ;
					break ;
			}
		} else {
			$e = new HTTPGetException(-1, curl_error($curl)) ;
		}

		curl_close($curl) ;

		if(isset($e)) {
			throw $e ;
		} else {
			return $data ;
		}
	}
	

}

?>