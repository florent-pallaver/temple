<?php

namespace temple\util\io;

use Exception;

class Image {

    public static $COMPRESSION_QUALITY = 95;
    public static $IMAGE_MAX_WIDTH = 1280;
    public static $IMAGE_MAX_HEIGHT = 720;
    public static $THUMBNAIL_DIMENSION = 200;
    
    private $gdImage;

    private $width ;
    
    private $height ;
    
    private function __construct($gdImage) {
        $this->gdImage = $gdImage;
        $this->width = \imagesx($gdImage) ;
        $this->height = \imagesy($gdImage) ;
    }

    public function rotate($degrees) {
        // TODO
        // if else throw
        // $this->im->rotateimage(0, $degrees);
    }

    public function crop($leftOffset, $topOffset, $width, $height) {
        // TODO
        // if else throw
        // $this->im->cropimage($width, $height, $leftOffset, $topOffset);
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
        return $this->createFile($this->getDestFile($dir, $dest), $wid, $hei);
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
        return $this->createThumbnailFile($this->getDestFile($dir, $dest), $dim);
    }

    /**
     * 
     * @param \temple\util\io\Directory $d
     * @param string $dest 
     * @return \temple\util\io\File
     * @throws ResourceAccessException
     */
    private function getDestFile($dir, $dest) {
        $d = new Directory($dir);
        return new File($dest . '.jpg', $d);
    }

    /**
     * 
     * @param string $dest
     * @param int $wid
     * @param int $hei
     * @return boolean
     * @throws ResourceCreationException
     */
    public function createFile(File $dest, $wid = 0, $hei = 0) {
        $ok = true;
        if ($wid > 0 || $hei > 0) {
//            $ok = $im->thumbnailImage($wid, $hei, true);
        }
        self::ensureDirExists($dest->getDirectory()) ;
        if(\imagejpeg($this->gdImage, $dest->getAbsolutePath(), self::$COMPRESSION_QUALITY)) {
            return true ;
        }
        throw new ResourceCreationException($dest);
    }

    /**
     * 
     * @param string $dest
     * @param int $dim
     * @return boolean
     * @throws ResourceCreationException
     */
    public function createThumbnailFile(File $dest, $dim) {
//        try {
//            $im = $this->im->getImage();
//            return $im->cropThumbnailImage($dim, $dim) && $im->writeImage($dest);
//        } catch (Exception $ex) {
//            throw new ResourceCreationException($dest, $ex->getMessage());
//        }
    }
    
    private static function ensureDirExists(Directory $d) {
        $d->create(); // ensures $d exists
        if (!$d->isWritable()) {
            throw new ResourceAccessException($d);
        }
    }
    
    /**
     * 
     * @param File $file
     * @return Image
     * @throws ImageProcessingException
     */
    public static function createFromFile(File $file) {
        $resource = false;
        $mimeType = $file->getMimeType();
        if ($mimeType == 'image/jpeg') {
            $resource = \imagecreatefromjpeg($file);
        } else {
            $source = false;
            if ($mimeType == 'image/png') {
                $source = \imagecreatefrompng($file);
            } else if ($mimeType == 'image/gif') {
                $source = \imagecreatefromgif($file);
            }
            $imageInfo = getimagesize($file);
            if ($source && $imageInfo) {
                $width = $imageInfo[0];
                $height = $imageInfo[1];
                $resource = \imagecreatetruecolor($width, $height);
                if (!($resource && \imagecopyresampled($resource, $source, 0, 0, 0, 0, $width, $height, $width, $height))) {
                    throw new ImageProcessingException();
                }
            }
        }
        if (!$resource) {
            throw new UnsupportedImageFormatException();
        }
        return new Image($resource);
    }

}
