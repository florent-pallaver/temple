<?php

namespace temple\web\html;

use temple\web\html\HTMLElement ;

/**
 *
 * @author florent
 */
interface RenderObserver {
	
	function preRender(HTMLElement $rendered) ;

}
