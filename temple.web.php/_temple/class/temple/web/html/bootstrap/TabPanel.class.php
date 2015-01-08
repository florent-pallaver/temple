<?php

namespace temple\web\html\bootstrap;

/**
 * Description of TabPanel
 *
 * @author florent
 */
class TabPanel extends AbstractComponent implements \temple\web\html\RenderObserver {

	/**
	 * @var ItemList
	 */
	private $tabs;

	/**
	 * @var \temple\web\html\HTMLNode
	 */
	private $contents;

	/**
	 *
	 * @var boolean
	 */
	private $fade;
	private $active;
	private $count;

	public function __construct($tabs = true, $fade = true, $cssClass = null) {
		parent::__construct('div', $cssClass);
		$this->tabs = ItemList::createUnordered('nav nav-' . ($tabs ? 'tabs' : 'pills'))
			->setAttribute('role', 'tablist');
		$this->contents = $this->createHTMLNode('div', 'tab-content');
		$this->addChild($this->tabs)
			->addChild($this->contents);
		$this->fade = $fade;
		$this->active = null;
		$this->count = 0;
		$this->addRenderListener($this);
	}

	/**
	 * 
	 * @param type $title
	 * @param type $content
	 * @param boolean $active
	 * @return TabPanel
	 */
	public function addTab($title, $content, $active = false) {
		if ($active && $this->active === null) {
			$this->active = $this->count;
		}
		$this->count++;
		$div = $this->createHTMLNode('div', 'tab-pane')
			->addCssClass($this->fade ? 'fade' : null) ;

		$this->contents->addChild($div->addChild($this->toHTMLElement($content)));
		$this->tabs->addItem(AnchorLink::create($div, $title)
				->setAttribute('role', 'tab')
				->setData(['toggle' => 'tab']));
		return $this;
	}

	public function preRender(\temple\web\html\HTMLElement $rendered) {
		if ($this->count) {
			if ($this->active === null) {
				$this->active = 0;
			}
			$this->tabs->getChild($this->active)
				->addCssClass('active');
			$this->contents->getChild($this->active)
				->addCssClass($this->fade ? 'in' : null)
				->addCssClass('active');
		}
	}

	/**
	 * 
	 * @param type $fade
	 * @param type $cssClass
	 * @return TabPanel
	 */
	public static function createTabs($fade = true, $cssClass = null) {
		return new TabPanel(true, $fade, $cssClass);
	}

	/**
	 * 
	 * @param type $fade
	 * @param type $cssClass
	 * @return TabPanel
	 */
	public static function createPills($fade = true, $cssClass = null) {
		return new TabPanel(false, $fade, $cssClass);
	}

}
