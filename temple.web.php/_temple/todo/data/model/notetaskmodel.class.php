<?php

namespace data\model ;

class NoteTaskModel extends AbstractModel {

	public static $table = 'note_task' ;

	public static $primaryKey = 'id' ;

	public static $insertables = array('userId', 'noteTime', 'clientId', 'sentReport', 'discussedReport', 'updatedReport', 'madeAdWordsChanges', 
		'discussedAdWordsChanges', 'madeRegularContact', 'discussedAccount', 'additional') ;

	public static $updatables = array('done') ;

	public static $formatters = array('additional' => Model::STRING_FORMATTER) ;

	public static $autoFetch = array('client' => array('class' => 'ClientModel'), 
		'managerAccount' => array('class' => 'ManagerAccountModel', 'foreignKey' => 'userId')) ;
	
	public $id ;
	
	public $userId ;

	public $noteTime ;

	public $clientId ;

	public $sentReport ;

	public $discussedReport ;

	public $updatedReport ;

	public $madeAdWordsChanges ;

	public $discussedAdWordsChanges ;

	public $madeRegularContact ;

	public $discussedAccount ;

	public $additional ;
	
	public $done ;
	
	public $managerAccount ;
	
	public $client ;
	
	public function getPrimaryKey() {
		return $this->id ;
	}
	
}

?>