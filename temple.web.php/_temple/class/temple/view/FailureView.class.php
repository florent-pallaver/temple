<?php

namespace temple\view;

use temple\web\html\HTMLNode as HN;
use temple\web\html\HTMLString as HS;

/**
 * Description of FailureView
 *
 * @author florent
 */
class FailureView extends \temple\web\html\HTML5Document implements View {

	/**
	 * @var boolean set to true if you want to show stack trace
	 */
	public static $SHOW_STACKTRACE = false;

	public function __construct(\Exception $e) {
		parent::__construct('Temple - Failure');
		$h1 = new HN('h3');
		$this->getBody()->addChild($h1);
		$h1->addChild(new HS('Failure: '))->addChild(new HS($e->getMessage()));
		if (self::$SHOW_STACKTRACE) {
			$pre = new HN('pre');
			$this->getBody()
					->addNode($pre)
					->addChild(new HS($e->getTraceAsString()));
		}
	}

	public function toRenderable() {
		return $this;
	}

}
