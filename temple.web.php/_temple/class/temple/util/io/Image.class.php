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
        self::ensureDirExists($dest->getDirectory()) ;
        $dstImg = $this->gdImage;
        $dstOK = true;
        if ($wid > 0 || $hei > 0) {
            if($hei == 0) {
                $hei = $wid;
            }
            if($wid == 0) {
                $wid = $hei;
            }
            if($wid < $this->width || $hei < $this->height) {
                $ratio = $this->width / $this->height;
                if($this->width > $this->height) {
                    $hei = round($wid / $ratio);
                } else {
                    $wid = round($hei / $ratio);
                }
                $dstImg = \imagecreatetruecolor($wid, $hei);
                $dstOK = \imagecopyresampled($dstImg, $this->gdImage, 0, 0, 0, 0, $wid, $hei, $this->width, $this->height);
            }
        }
        if($dstOK && \imagejpeg($dstImg, $dest->getAbsolutePath(), self::$COMPRESSION_QUALITY)) {
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
        self::ensureDirExists($dest->getDirectory()) ;
        if($this->width > $this->height) {
            $min = $this->height;
            $src_x = round(($this->width - $min) / 2);
            $src_y = 0;
        }  else {
            $min = $this->width;
            $src_x = 0;
            $src_y = round(($this->height - $min) / 2);
        }
        $dstImg = \imagecreatetruecolor($dim, $dim);
        if(\imagecopyresampled($dstImg, $this->gdImage, 0, 0, $src_x, $src_y, $dim, $dim, $min, $min)
                && \imagejpeg($dstImg, $dest->getAbsolutePath(), self::$COMPRESSION_QUALITY)) {
            return true;
        }
        throw new ResourceCreationException($dest);
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
