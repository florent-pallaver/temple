<?php

namespace web ;

interface RegistrationTypeFinder {

	function findRegistrationURL(HTMLDocument $html) ;

	// 
	function findRegistrationType(HTMLDocument $html) ;

}

?>