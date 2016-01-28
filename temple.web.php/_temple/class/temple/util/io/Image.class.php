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
		return $this->createFile($this->getDestFile($dir, $dest), $wid, $hei) ;
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
		return $this->createThumbnailFile($this->getDestFile($dir, $dest), $dim) ;
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

	/**
	 * 
	 * @param string $dest
	 * @param int $wid
	 * @param int $hei
	 * @return boolean
	 * @throws ResourceCreationException
	 */
	public function createFile($dest, $wid = 0, $hei = 0) {
		try {
			$im = $this->im->getImage() ;
			$b = true ;
			if($wid > 0 || $hei > 0) {
				$b = $im->thumbnailImage($wid, $hei, true) ;
			}
			return $b && $im->writeImage($dest);
		} catch (Exception $ex) {
			throw new ResourceCreationException($dest, $ex->getMessage());
		}
	}
	
	/**
	 * 
	 * @param string $dest
	 * @param int $dim
	 * @return boolean
	 * @throws ResourceCreationException
	 */
	public function createThumbnailFile($dest, $dim) {
		try {
			$im = $this->im->getImage() ;
			return $im->cropThumbnailImage($dim, $dim) && $im->writeImage($dest);
		} catch (Exception $ex) {
			throw new ResourceCreationException($dest, $ex->getMessage());
		}
	}
	
	/**
	 * 
	 * @param File $file
	 * @return Image
	 * @throws ImageProcessingException
	 */
	public static function createFromFile(File $file) {
		try {
			$im = new Imagick() ;
			if($file->getMimeType() == 'application/pdf') {
				$im->setResolution(300,300);
			}
			$im->readimage($file->getAbsolutePath()); 
		} catch (Exception $ex) {
			throw new UnsupportedImageFormatException($ex) ;
		}
		try {
//			$im->setimagecolorspace(255);
			$im->setimageformat('jpeg');
			$im->setimagecompression(Imagick::COMPRESSION_JPEG);
			$im->setimagecompressionquality(self::$COMPRESSION_QUALITY);
			$im->flattenimages();
		} catch (Exception $ex) {
			throw new ImageProcessingException($ex) ;
		}
		return new Image($im) ;
	}
	
}
