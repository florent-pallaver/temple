<?php

namespace temple\web\mail\phpmailer {

	use PHPMailer;

	/**
	 * Description of Mailer
	 *
	 * @author florent
	 */
	final class MailerImpl extends \temple\web\mail\Mailer {

		use \temple\Singleton;

		public function mailTemplate($subject, $content, $email, $nickname = '') {
			$tpl = file_get_contents(self::$templatePath);
			$newContent = str_replace(['{{title}}', '{{content}}'], [$subject, $content], $tpl);
			$this->mail($subject, $newContent, $email, $nickname);
		}

		public function mail($subject, $content, $email, $nickname = '') {
			$mail = $this->createDelegate();
			//Set who the message is to be sent to
			$mail->addAddress($email, $nickname);
			//Set the subject line
			$mail->Subject = self::$subjectPrefix . $subject;
			//convert HTML into a basic plain-text alternative body
			$mail->msgHTML($content);
			if (!$mail->send()) {
				throw new \temple\web\mail\MailerException($mail->ErrorInfo);
			}
		}
		
		public function mailMany($subject, $content, array $tos, array $ccs = [], array $bccs = []) {
			$mail = $this->createDelegate();
			//Set who the message is to be sent to
			foreach ($tos as $to) {
				$mail->addAddress($to);
			}
			foreach ($ccs as $cc) {
				$mail->addCC($cc);
			}
			foreach ($bccs as $bcc) {
				$mail->addBCC($bcc);
			}
			//Set the subject line
			$mail->Subject = self::$subjectPrefix . $subject;
			//convert HTML into a basic plain-text alternative body
			$mail->msgHTML($content);
			if (!$mail->send()) {
				throw new \temple\web\mail\MailerException($mail->ErrorInfo);
			}
			
		}

		public function mailManyTemplate($subject, $content, array $tos, array $ccs = [], array $bccs = []) {
			$tpl = file_get_contents(self::$templatePath);
			$newContent = str_replace(['{{title}}', '{{content}}'], [$subject, $content], $tpl);
			$this->mailMany($subject, $newContent, $tos, $ccs, $bccs);
		}

		/**
		 * @return PHPMailer
		 */
		private function createDelegate() {
			//Create a new PHPMailer instance
			$mail = new PHPMailer();
			//Tell PHPMailer to use SMTP
			$mail->isSMTP();
			//Enable SMTP debugging
			// 0 = off (for production use)
			// 1 = client messages
			// 2 = client and server messages
			// 4 = verbose
			$mail->SMTPDebug = 0;
			//Ask for HTML-friendly debug output
			$mail->Debugoutput = 'html';
			//Set the hostname of the mail server
			$mail->Host = self::$host;
			//Set the SMTP port number - likely to be 25, 465 or 587
			$mail->Port = self::$port;
			// Whether to use SMTP authentication
			$mail->SMTPAuth = true;
			$mail->SMTPSecure = 'ssl';
			//Username to use for SMTP authentication
			$mail->Username = self::$user;
			//Password to use for SMTP authentication
			$mail->Password = self::$password;
			//Set who the message is to be sent from
			$mail->setFrom(self::$user, self::$userName);
			$mail->CharSet = 'utf-8';
			//Set an alternative reply-to address
			//$mail->addReplyTo('replyto@example.com', 'First Last');
			return $mail;
		}

		private static function _init() {
			\temple\ClassLoader::add(ROOT_PATH . '/_temple/resources/phpmailer/', true, '.php', 'class.');
		}

	}

}

// hack to fool PHPMailer ...

namespace {

	function PHPMailerAutoload($classname) {
		
	}

	spl_autoload_register('PHPMailerAutoload');
}

