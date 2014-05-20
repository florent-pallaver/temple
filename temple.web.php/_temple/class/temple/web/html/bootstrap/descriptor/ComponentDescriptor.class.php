<?php

namespace temple\web\html\bootstrap\descriptor;

/**
 *
 * @author florent
 */
interface ComponentDescriptor {
	
	/**
	 * @return \temple\web\html\bootstrap\AbstractComponent
	 */
	function toComponent() ;
		
}
