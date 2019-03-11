<?php

namespace temple\util\human;

/**
 * Description of Gender
 *
 * @author florent
 */
final class Gender extends \temple\Enumeration implements \temple\util\Iconable {

	/**
	 * @var Gender
	 */
	public static $FEMALE;

	/**
	 * @var Gender
	 */
	public static $MALE;

	/**
	 * @var Gender
	 */
	public static $TRANSEXUAL;

	/**
	 * @var Gender
	 */
	public static $OTHER;

	private static $_icons = ['venus', 'mars', 'transgender', 'genderless'];

	public final function getIcon() {
		return _iod(self::$_icons, $this->ordinal(), 'question');
	}

	public function __toString() {
		return GenderLocale::$all[$this->ordinal()];
	}

}
