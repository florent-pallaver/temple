<?php

namespace temple\web\html;

/**
 * TODOC
 *
 * @author florent
 */
class HTMLString extends AbstractHTMLElement {

	/**
	 * @var HTMLString
	 */
    public static $NBSP;
	
    private $string;
    private $formattedString;
    private $nl2br;

    /**
     * Constructor.
     *
     * @param string $string - TODOC
     * @param boolean $formatted - TODOC
     * @param boolean $nl2br TODOC
     */
    public function __construct($string, $formatted = false, $nl2br = true) {
        $this->string = strval($string);
        $this->formattedString = $formatted ? $string : NULL;
        $this->nl2br = $nl2br;
    }

    protected function _toString() {
        if ($this->formattedString === NULL) {
            $this->formattedString = $this->format($this->string);
        }
        return $this->formattedString;
    }

    protected function _render() {
        echo $this->toString();
    }

    /**
     * Format the given string.
     *
     * @param string $string the string to format
     * @return the formatted string.
     */
    protected function format($string) {
        $s = htmlspecialchars($string) ;
        if($this->nl2br) {
            $s = nl2br($s) ;
        }
        return $s ;
    }

    private static function _init() {
        self::$NBSP = new HTMLString('&nbsp;', true);
    }

}
