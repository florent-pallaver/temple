<?php

namespace data\model ;

class ProxyModel extends AbstractModel {

	const CONNECTION_TEST_MASK = 0b1 ;
	
	public static $insertables = array('ip', 'port', 'country', 'sourceDomain') ;

	public static $updatables = array('checkTime', 'checkCount', 'status') ;

	public static $formatters = array('checkTime' => Model::STRING_FORMATTER, 'status' => Model::VALUE_OR_NULL_FORMATTER) ;

	public $id ;

	public $ip ;

	public $port ;

	public $country ;
	
	public $sourceDomain ;

	public $checkTime ;
	
	public $checkCount ;

	public $status ;
	
	public function getPrimaryKey() {	
		return $this->id ;
	}

	public function getIPAsString() {
		return long2ip($this->ip) ;
	}
	
	public function setIPFromString($sip) {
		$this->ip = ip2long($sip == 'localhost' ? '127.0.0.1' : $sip) ;
	}
	
	public function testConnection($timeout = 5) {
		$s = @fsockopen($this->getIPAsString(), $this->port, $errno, $errormsg, $timeout) ;
		if($s) {
			$b = true ;
			fclose($s) ;
		} else {
			$b = false ;
			logThat('Unable to connect to ' . $this . ', error ' . $errno . ': ' . $errormsg) ;
		}
		return $b ;
	}
	
	public function isStatusOK($mask) {
		return ($this->status & $mask) == $mask ;
	}

	public function setStatusOK($mask, $status) {
		$this->status = $status ? $this->status | $mask : $this->status & ~$mask ;
	}
	
	public function __toString() {
		return $this->getIPAsString() . ':' . $this->port ;
	}
	
}

?>
