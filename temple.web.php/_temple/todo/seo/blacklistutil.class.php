<?php

final class BlackListUtil {

	private function __construct() {}
	
	public static function filter(array $websites, array $blackLists = null) {
		$blackLists = $blackLists == null ? Descriptor::getInstance('BlackListModel')->getAll() : $blackLists ;
		foreach($blackLists as $bl) {
			$accepted = array() ;
			foreach($websites as $w) {
				if(self::accept($bl, $w)) {
					$accepted[] = $w ;
				}
			}
			$websites = $accepted ;
		}
		return $websites ;
	}

	private static function accept(BlackListModel $bl, WebsiteModel $w) {
		$domain = $w->domain ;
		$toFind = $bl->pattern ;
		$found = $bl->regex ? preg_match($toFind, $domain) === 1 : strpos($domain, $toFind) !== false ;
		return !$found ;
	}

}



?>