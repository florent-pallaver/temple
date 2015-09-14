<?php

namespace temple\util\io ;

use Imagick;
use Exception;

/**
 * Description of Image
 *
 * @author florent
 */
class Image {

	private static $errorMsgs = [
		UPLOAD_ERR_INI_SIZE => 'file is too big',
		UPLOAD_ERR_FORM_SIZE => 'file is too big',
		UPLOAD_ERR_PARTIAL => 'file has been only partially uploaded',
		UPLOAD_ERR_NO_FILE => 'no file has been uploaded',
		0 => 'an unexpected error on the server occured'
	];
	private static $failMsg = "An error occured while processing '%s': %s.";
	public static $COMPRESSION_QUALITY = 95;
	public static $IMAGE_MAX_WIDTH = 1280;
	public static $IMAGE_MAX_HEIGHT = 720;
	public static $THUMBNAIL_DIMENSION = 200;

	/**
	 * @var Imagick
	 */
	private $im ;
	
	private function __construct(Imagick $im) {
		$this->im = $im ;
	}
	
	public function rotate($degrees) {
		// TODO
		// if else throw
		$this->im->rotateimage(0, $degrees) ;
		
	}
	
	public function crop($leftOffset, $topOffset, $width, $height) {
		// TODO
		// if else throw
		$this->im->cropimage($width, $height, $leftOffset, $topOffset) ;
	}
	
	/**
	 * 
	 * @param string $dir
	 * @param string $dest without the .jpg extension
	 * @param int $wid
	 * @param int $hei
	 * @return boolean
	 * @throws ResourceCreationException
	 */
	public function writeTo($dir, $dest, $wid = 0, $hei = 0) {
		$f = $this->getDestFile($dir, $dest) ;
		try {
			$im = $this->im->getimage() ;
			$b = true ;
			if($wid > 0 || $hei > 0) {
				$b = $im->thumbnailimage($wid, $hei, true) ;
			}
			return $b && $im->writeimage($f);
		} catch (Exception $ex) {
			throw new ResourceCreationException($f, $ex->getMessage());
		}
	}
	
	/**
	 * 
	 * @param string $dir
	 * @param string $dest
	 * @param int $dim
	 * @return boolean
	 * @throws ResourceCreationException
	 */
	public function writeThumbnailTo($dir, $dest, $dim) {
		$f = $this->getDestFile($dir, $dest) ;
		try {
			$im = $this->im->getimage() ;
			return $im->cropthumbnailimage($dim, $dim) && $im->writeimage($f);
		} catch (Exception $ex) {
			throw new ResourceCreationException($f, $ex->getMessage());
		}
	}
	
	/**
	 * 
	 * @param \temple\util\io\Directory $d
	 * @param string $dest 
	 * @return \temple\util\io\File
	 * @throws ResourceAccessException
	 */
	private function getDestFile($dir, $dest) {
		$d = new Directory($dir) ;
		$d->create(); // ensures $d exists
		if (!$d->isWritable()) {
			throw new ResourceAccessException($d) ;
		}
		return new File($dest . '.jpg', $d) ;
	}

	public static function createFromFile($ufn) {
		try {
			$im = new Imagick($ufn);
		} catch (Exception $ex) {
			self::failure($ufn, 'its file format is not supported', $ex);
		}
		try {
			$im->setimagecolorspace(255);
			$im->setimageformat('jpeg');
			$im->setimagecompression(Imagick::COMPRESSION_JPEG);
			$im->setimagecompressionquality(self::$COMPRESSION_QUALITY);
			$im->flattenimages();
		} catch (Exception $ex) {
			self::failure($ufn, $ex->getMessage(), $ex);
		}
		return new Image($im) ;
	}
	
	/**
	 * 
	 * @param array $uploadData
	 * @throws Exception
	 */
	public static function createFromUploadFile(array $uploadData) {
		$ufn = &$uploadData['name'];
		if ($uploadData['error']) {
			self::failure($ufn, self::$errorMsgs[intval($uploadData['error'])]);
		}
		if (!is_uploaded_file($uploadData['tmp_name'])) {
			self::failure($ufn, 'it is not an uploaded file');
		}
		try {
			$im = new Imagick($uploadData['tmp_name']);
		} catch (Exception $ex) {
			self::failure($ufn, 'its file format is not supported', $ex);
		}
		try {
			$im->setimagecolorspace(255);
			$im->setimageformat('jpeg');
			$im->setimagecompression(Imagick::COMPRESSION_JPEG);
			$im->setimagecompressionquality(self::$COMPRESSION_QUALITY);
			$im->flattenimages();
		} catch (Exception $ex) {
			self::failure($ufn, $ex->getMessage(), $ex);
		}
		return new Image($im) ;
	}
	
	private static function failure($ufn, $reason, Exception $e = null) {
		// TODO should throw IO exception
		throw new Exception(sprintf(self::$failMsg, $ufn, $reason), 0, $e);
	}

}
