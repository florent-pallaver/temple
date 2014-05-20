<?php

namespace temple\web\opencart\view ;

use temple\web\html\HTMLNode ;

/**
 * TODOC
 *
 * @author florent
 */
abstract class AbstractView extends HTMLNode implements View {

	public final function toHTMLElements() {
		return $this ;
	}

}

?>