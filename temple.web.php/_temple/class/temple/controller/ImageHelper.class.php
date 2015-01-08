<?php

namespace temple\controller;

use Imagick;
use Exception;
use temple\util\io\Directory;

/**
 * Description of ImageHelper
 *
 * @author florent
 */
class ImageHelper {

	private static $errorMsgs = [
		UPLOAD_ERR_INI_SIZE => 'file is too big',
		UPLOAD_ERR_FORM_SIZE => 'file is too big',
		UPLOAD_ERR_PARTIAL => 'file has been only partially uploaded',
		UPLOAD_ERR_NO_FILE => 'no file has been uploaded',
		0 => 'an unexpected error on the server occured'
	];
	private static $failMsg = 'Upload of file &quot;%s&quot; failed, because %s.';
	public static $COMPRESSION_QUALITY = 90;
	public static $IMAGE_MAX_WIDTH = 1280;
	public static $IMAGE_MAX_HEIGHT = 720;
	public static $THUMBNAIL_DIMENSION = 200;

	/**
	 * 
	 * @param array $photoInfo
	 * @param \temple\util\io\Directory $d
	 * @param type $filename
	 * @param type $thumbnailPrefix
	 * @throws ActionException
	 */
	public static function processPhoto(array $photoInfo, Directory $d, $filename, $thumbnailPrefix = '_t') {
		$ufn = &$photoInfo['name'];
		if ($photoInfo['error']) {
			self::failure($ufn, self::$errorMsgs[intval($photoInfo['error'])]);
		}
		if (!is_uploaded_file($photoInfo['tmp_name'])) {
			self::failure($ufn, 'it is not an uploaded file');
		}
		$d->create(); // ensures $d exists
		if (!$d->isWritable()) {
			\temple\Logger::getInstance()->severe($d . ' is not writable!');
			self::failure($ufn, self::$errorMsgs[0]);
		}
		try {
			$im = new Imagick($photoInfo['tmp_name']);
		} catch (Exception $ex) {
			self::failure($ufn, 'its file format is not supported');
		}
		try {
			$im->setimagecolorspace(255);
			$im->setimageformat('jpeg');
			$im->setimagecompression(Imagick::COMPRESSION_JPEG);
			$im->setimagecompressionquality(self::$COMPRESSION_QUALITY);
			$im->flattenimages();
			$im->thumbnailimage(self::$IMAGE_MAX_WIDTH, self::$IMAGE_MAX_HEIGHT, true) && $im->writeimage($d . $filename . '.jpg');
			if ($thumbnailPrefix) {
				$im->cropthumbnailimage(self::$THUMBNAIL_DIMENSION, self::$THUMBNAIL_DIMENSION) && $im->writeimage($d . $filename . $thumbnailPrefix . '.jpg');
			}
		} catch (Exception $ex) {
			self::failure($ufn, $ex->getMessage());
		}
	}

	private static function failure($ufn, $reason, Exception $e = null) {
		throw new ActionException('process image', sprintf(self::$failMsg, $ufn, $reason), null, $e);
	}

}
