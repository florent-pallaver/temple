<?php

namespace data\model ;

use \persistence\OneToOne ;
use \persistence\ManyToOne ;

class BusinessModel extends AbstractModel {

	public static $insertables = array('name', 'website', 'location', 'phone', 'phone_filter', 'filtered') ;

	public static $updatables = array('user_id', 'name', 'not_interested', 'lead_generated', 'appointment_booked', 'sale', 
		'not_decision_maker', 'remove_from_db', 'sales_agent', 'google_id', 'comment', 'filtered', 'rwd') ;
	
	public static $formatters = array('name' => Model::STRING_FORMATTER, 'website' => Model::STRING_FORMATTER, 
		'location' => Model::STRING_FORMATTER, 'phone' => Model::STRING_FORMATTER, 'google_id' => Model::STRING_FORMATTER, 
		'comment' => Model::STRING_FORMATTER, 'sales_agent' => Model::VALUE_OR_NULL_FORMATTER, 'rwd' => Model::STRING_FORMATTER) ;

	public static $relations ;
		
	public $id ;
	public $user_id ;
	public $name ;
	public $location ;
	public $phone ;
	public $phone_filter ;
	public $website ;
	public $date_time ;
	public $not_interested ;
	public $lead_generated ;
	public $appointment_booked ;
	public $sale ;
	public $not_decision_maker ;
	public $remove_from_db ;
	public $sales_agent ;
	public $google_id ;
	public $comment ;
	public $filtered ;
	public $rwd ;
	
	public $leadTask ;
	public $leadGenerator ;
	public $salesAgent ;

	public function getPrimaryKey() {
		return $this->id ;
	}
	
	private static function _init() {
		self::$relations = array() ;
		self::$relations['leadTask'] = new OneToOne('leadTask', false, true, 'LeadTaskModel', 'id') ;
		self::$relations['leadGenerator'] = new ManyToOne('leadGenerator', true, false, 'UserModel', 'user_id') ;
		self::$relations['salesAgent'] = new ManyToOne('salesAgent', true, true, 'UserModel', 'sales_agent') ;
	}
	
}

?>