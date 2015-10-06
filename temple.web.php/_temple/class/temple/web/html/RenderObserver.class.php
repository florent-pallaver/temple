<?php

namespace temple\web\html;

use temple\web\html\HTMLElement ;

/**
 * TODOC
 * @author florent
 */
interface RenderObserver {
	
	function preRender(HTMLElement $rendered) ;

}
