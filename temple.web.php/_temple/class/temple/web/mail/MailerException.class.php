<?php

namespace temple\web\mail;

/**
 * Description of MailerException
 *
 * @author florent
 */
final class MailerException extends \temple\TempleException {

	const MSG_FORMAT = "Unable to send email.\nCause: %s" ;
	
	/**
	 * 
	 * @param string $cause
	 */
	public function __construct($cause) {
		parent::__construct(sprintf(self::MSG_FORMAT, $cause));
	}
	
}
