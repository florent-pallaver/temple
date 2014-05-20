<?php

namespace web ;

use data\RegistrationType ;

class ForumRegistrationTypeFinder implements RegistrationTypeFinder {

	public function findRegistrationURL(HTMLDocument $html) {
		$url = null ;
		$xpaths = array() ;
		$xpaths[] = "//a[. = 'register']/@href" ;
		$xpaths[] = "//a[. = 'Register']/@href" ;
		foreach($xpaths as $xp) {
			$nodes = $html->xpathQuery($xp) ;
			if($nodes->length > 0) {
				$href = urldecode($nodes->item(0)->nodeValue) ;
				$url = strpos($href, 'http://') === false 
							? substr($html->getURL(), 0, strrpos($html->getURL(), '/') + 1) . $href 
							: $href ;
				$url = explode('?', $url)[0] ;
				break ;
			}
		}
		return $url ;
	}

	public function findRegistrationType(HTMLDocument $html) {
		$ra = '//*[. = "Random Question:"] | //*[. = "Random Question"]' ;
		$bd0 = '//label[. = "Please Enter Your Date of Birth:"] | //p[. = "Step 1 of 2"]' ;
		$bd1 = '//legend[. = "Please Enter Your Date of Birth"] | //input[@name="year"]' ;
		$bd2 = '//label[. = "Enter your birthday:"] | //p[. = "Step 1 of 2"]' ;
		$ag0 = '//legend[. = "Forum Rules"] | //*[@id="cb_rules_agree"]' ;
		$ag1 = '//legend[. = "Forum Rules"] | //*[@id="cb_rules_agree"]' ;
		$ba = '//h1[. = "Registration"] | //*[@value="Complete Registration"]' ;
		$imp0 = "//*[. = 'Sorry, registration has been disabled by the administrator.']" ;
		$imp1 = '//*[. = "Due to an overwhelming number of SPAM account registrations we have disabled registration for the moment. You may use the \'contact us\' link at the bottom of the page to request membership."]' ;
		if($html->xpathCount($ra) == 1) {
			$rt = 8 ;
		} elseif($html->xpathCount($bd0) == 2 || $html->xpathCount($bd1) == 2 || $html->xpathCount($bd2) == 2) {
			$rt = 5 ;
		} elseif($html->xpathCount($ag0) == 2 || $html->xpathCount($ag1) == 2) {
			$rt = 6 ;
		} elseif($html->xpathCount($ba) == 2) {
			$rt = 7 ;
		} elseif($html->xpathExist($imp0) || $html->xpathExist($imp1)) {
			$rt = RegistrationType::IMPOSSIBLE ;
		} else {
			$rt = RegistrationType::UNABLE_TO_RESOLVE ;
		}
		return $rt ;
	}

}

?>