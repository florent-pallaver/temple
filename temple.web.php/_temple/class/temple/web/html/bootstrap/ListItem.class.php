<?php

namespace temple\web\html\bootstrap;

use temple\web\html\HTMLElement;

/**
 * Description of ListItem
 *
 * @author florent
 */
final class ListItem extends AbstractComponent {

	/**
	 * @var ListItem
	 */
	public static $DIVIDER;

	public function __construct(HTMLElement $child = null, $cssClass = null) {
		parent::__construct('li', $cssClass);
		if ($child) {
			$this->addChild($child);
		}
	}

	private static function _init() {
		self::$DIVIDER = new ListItem(null, 'divider');
	}

}
