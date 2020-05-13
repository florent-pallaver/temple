<?php

namespace temple\data\persistence\model;

/**
 * Description of Entity
 * <br>
 * TODOC
 * <br>
 * @see $PK_FIELDS
 * @author florent
 */
trait Entity {

    use EntityCommon;

    /**
     * @var string
     */
    public static $TABLE;

    /**
     * @var Key
     */
    private static $PK;

    /**
     * Default to <code>[id]</code>
     * @var array
     */
    private static $PK_FIELDS = ['id'];

    /**
     * 
     * @return Key
     */
    public static function getPK() {
        if (!self::$PK) {
            self::$PK = new Key(self::_class(), self::$PK_FIELDS, true);
        }
        return self::$PK;
    }

    private static function _initEntity() {
        $class = self::_class();
        ProxyGenerator::getInstance()->generate($class);
        self::$TABLE = Config::$TABLE_PREFIX . strtolower($class->getShortName());
    }

}
