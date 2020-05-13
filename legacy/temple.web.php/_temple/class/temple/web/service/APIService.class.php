<?php

namespace temple\web\service ;

final class APIService extends \temple\WithLogger {

	use \temple\Singleton;

	public function createResponse() {
		print_r($_GET) ;
		return $_REQUEST['_PATH'] ;
	}

}
