<?php

namespace temple;

/**
 * 
 */
trait Reflection {

    private static $_class;

    /**
     * @var \ReflectionClass TODOC
     */
    public static function _class() {
        if (self::$_class === null) {
            self::$_class = new \ReflectionClass(__CLASS__);
        }
        return self::$_class;
    }

    /**
     * @return \ReflectionProperty
     */
    private static function _property($propertyName) {
        return self::_class()->getProperty($propertyName);
    }

}
