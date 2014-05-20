<?php

define('RANDOM_PROXY_BATCH_SIZE', 10) ;

define('TEST_CONNECTION_TIMEOUT', 3) ;

define('RANDOM_WORDS_MIN_COUNT', 2) ;

final class Random {

	private static $words = array() ;

	private function __construct() {}

	public static function getRandomText($wordsCount = 4) {
		$count = count(self::$words) - 1 ;
		$ws = array() ;
		for($i = mt_rand(RANDOM_WORDS_MIN_COUNT, $wordsCount) ; $i --> 0 ; ) {
			$ws[] .= self::$words[mt_rand(0, $count)] ;
		}
		return implode(' ', $ws) ;
	}
	
	public static function getRandomProxy(array $condition = null, $batchSize = RANDOM_PROXY_BATCH_SIZE, $timeout = TEST_CONNECTION_TIMEOUT) {
		$condition = $condition == null ? array('status' => array('cmp' => '& 3 =', 'val' => 3)) : $condition ;
		if($timeout == 0 || $batchSize == 1) {
			return Descriptor::getInstance('ProxyModel')->getRandom($condition, 1) ;
		} else {
			$proxies = Descriptor::getInstance('ProxyModel')->getRandom($condition, $batchSize) ;
			foreach($proxies as $proxy) {
				if($proxy->testConnection($timeout)) {
					return $proxy ;
				}
			}
			throw new Exception('Connection test failed for ' . count($proxies) . ' random proxies. Try again.') ;
		}
	}	
	
	public static function _init() {
		self::$words = array('still', 'remember', 'forever', 'hard', 'knock', 'life', 'how', 'both', 'dreamer', 'savior', 'give', 
			'mine', 'waiting', 'everlasting', 'sun', 'live', 'always', 'like', 'searching', 'one', 'moment', 'for', 'cherry', 
			'blossom', 'true', 'grill', 'part', 'day', 'away', 'drive', 'watch', 'shadow', 'poison', 'mouth', 'then', 'look', 
			'ever', 'take', 'throw', 'stone', 'soul', 'phone', 'down', 'request', 'understand', 'update', 'country', 'sparkle', 
			'diamond', 'ring', 'together', 'heart', 'secret', 'head', 'alright', 'believe', 'first', 'sight', 'open', 'future', 
			'feel', 'better', 'confusion', 'check', 'illusion', 'all', 'other', 'faster', 'crowd', 'flash', 'picture', 'leather', 
			'jeans', 'mean', 'price', 'ready', 'follow', 'need', 'way', 'feeling', 'what', 'around', 'certain', 'night', 'day',
			'people', 'know', 'real', 'room', 'view', 'within', 'flaw', 'afford', 'miss', 'pretend', 'thousand', 'make', 'downtown',
			'through', 'fall', 'into', 'just', 'think', 'memory', 'later', 'home', 'go', 'perfect', 'animal', 'eye', 'golden', 
			'radio', 'good', 'time', 'radio', 'smash', 'before', 'dirty', 'light', 'dark', 'silver', 'screen', 'shower', 'wood',
			'floor', 'alone', 'only', 'know', 'who', 'nowhere', 'swing', 'century', 'bee', 'top', 'bottom', 'worldwide', 'Rio', 
			'Tokyo', 'New York', 'Paris', 'Berlin', 'London', 'club', 'Sydney', 'Buenos Aires', 'Cape Town', 'knee', 'tea'
			) ;
	}

}

?>