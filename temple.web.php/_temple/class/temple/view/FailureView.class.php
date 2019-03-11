<?php

namespace temple\view;

use temple\web\html\HTMLNode as HN;
use temple\web\html\HTMLString as HS;
use temple\web\html\HTMLNodeFactory as HNF ;
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

   	/**
	 * @var boolean set to true if you want to show stack trace of what caused the failure
	 */
	public static $SHOW_CAUSE_STACKTRACE = false;

	public function __construct(\Error $e) {
		parent::__construct('Temple - Failure');
		$h1 = new HN('h1');
		$this->getBody()->addChild($h1
				->addChild(new HS('Failure: '))
				->addChild(new HS($e->getMessage(), true)));
		if (self::$SHOW_STACKTRACE) {
			$this->getBody()->addChild(HNF::createNode('pre')
					->addChild(new HS($e->getTraceAsString())));
            if (self::$SHOW_CAUSE_STACKTRACE) {
                for($ee = $e->getPrevious() ; $ee ; $ee = $ee->getPrevious()) {
                    $h2 = new HN('h2') ;
                    $this->getBody()->addChild($h2
                            ->addChild(new HS('Caused by: '))
                            ->addChild(new HS($ee->getMessage(), true))) 
                        ->addChild(HNF::createNode('pre')
                                ->addChild(new HS($ee->getTraceAsString())));  
                } 
            }
		}
	}

	public function toRenderable() {
		return $this;
	}

}
