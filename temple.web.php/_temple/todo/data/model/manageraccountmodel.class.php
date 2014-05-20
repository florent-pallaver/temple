<?php

namespace data\model ;

class ManagerAccountModel extends AbstractModel {

	public static $primaryKey = 'userId' ;

	public static $insertables = array('userId', 'gid', 'gwd', 'rid', 'rwd', 'salt') ;

	public static $formatters = array('gid' => Model::STRING_FORMATTER, 'gwd' => Model::STRING_FORMATTER, 
		'rid' => Model::STRING_FORMATTER, 'rwd' => Model::STRING_FORMATTER, 'salt' => Model::STRING_FORMATTER) ;

	public $userId ;
	
	public $gid ;
	
	public $gwd ;
	
	public $rid ;
	
	public $rwd ;
	
	public $salt ;
	
	public function getPrimaryKey() {
		return $this->userId ;
	}
	
	public function getDecryptedGwd() {
		return $this->decrypt($this->gwd) ;
	}
	
	public function setEncryptedGwd($gwd) {
		$this->gwd = $this->encrypt($gwd) ;
	}

	public function getDecryptedRwd() {
		return $this->decrypt($this->rwd) ;
	}
	
	public function setEncryptedRwd($rwd) {
		$this->rwd = $this->encrypt($rwd) ;
	}
	
	private function encrypt($pwd) {
		return Util::encrypt($pwd, $this->salt);
	}

	private function decrypt($pwd) {
		return Util::decrypt($pwd, $this->salt);
	}
	
}

?>