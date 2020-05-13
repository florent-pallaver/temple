<?php

namespace temple\web\html\bootstrap;

/**
 * Description of TableVariant
 *
 * @author florent
 */
final class TableVariant extends \temple\Enumeration {

	/**
	 * @var NavbarVariant
	 */
	public static $STRIPED;

	/**
	 * @var NavbarVariant
	 */
	public static $HOVER;

	/**
	 * @var NavbarVariant
	 */
	public static $CONDENSED;

	/**
	 * @var NavbarVariant
	 */
	public static $BORDERED;

	private $string = null;

	public function __toString() {
		if ($this->string === null) {
			$this->string = 'table-' . strtolower($this->name());
		}
		return $this->string;
	}

}
