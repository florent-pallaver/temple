<?php

namespace temple\web\html\bootstrap;

/**
 * Description of MediaList
 *
 * @author florent
 */
class MediaList extends ItemList {

	/**
	 * 
	 * @param type $nodeType	
     * @param type $cssClass
	 */
	public function __construct($cssClass = null) {
		parent::__construct(false, 'media-list');
		$this->addCssClass($cssClass);
	}

	/**
	 * 
	 * @param \temple\web\html\HTMLElement $item
	 * @param type $cssClass
	 * @return ItemList
	 */
	public final function addMedia($href, $src, $heading, $body, $alt = null) {
		$this->addChild(Media::createItemImage($href, $src, _dif($alt, $heading), $heading, $body));
		return $this;
	}

	/**
	 * 
	 * @param type $cssClass
	 * @return MediaList
	 */
	public static function create($cssClass = null) {
		return new MediaList($cssClass);
	}

}
