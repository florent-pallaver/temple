<?php

namespace temple\web\html\bootstrap;

/**
 * Description of Table
 *
 * @author florent
 */
class Table extends AbstractComponent {

	private static $headKey = '_thead|';
	private static $bodyKey = '_tbody|';
	private static $footKey = '_tfoot|';

	/**
	 * @var \temple\web\html\HTMLNode
	 */
	private $table ;
	
	/**
	 * @var array
	 */
	private $cellCssClasses ;
	
	public function __construct($cssClass = null) {
		parent::__construct('div', 'table-responsive');
		$this->table = ComponentFactory::createComponent('table', 'table')
				->addCssClass($cssClass)
				->addChild(ComponentFactory::createComponent('tbody'), self::$bodyKey);
		$this->addChild($this->table);
		$this->cellCssClasses = [] ;
	}

	/**
	 * 
	 * @param array $headers
	 * @return \temple\web\html\bootstrap\Table
	 */
	public function setHeaders(array $headers) {
		return $this->setHeadOrFoot($headers, true) ;
	}

	/**
	 * 
	 * @param array $footers
	 * @return \temple\web\html\bootstrap\Table
	 */
	public function setFooters(array $footers) {
		return $this->setHeadOrFoot($footers, false) ;
	}
	
	private function setHeadOrFoot(array $ths, $head) {
		$tr = ComponentFactory::createComponent('tr');
		foreach ($ths as $i => $t) {
			$tr->addChild(ComponentFactory::createComponent('th', _iod($this->cellCssClasses, $i))
							->addChild(ComponentFactory::toHTMLElement($t)));
		}
		$this->table->addChild(ComponentFactory::createComponent($head ? 'thead' : 'tfoot')
						->addChild($tr), $head ? self::$headKey : self::$footKey);
		return $this ;
	}

	/**
	 * 
	 * @param array $cellsContent
	 * @param type $cssClass
	 * @return \temple\web\html\bootstrap\Table
	 */
	public function addRow(array $cellsContent, $cssClass = null, &$tr = null) {
		$tr = ComponentFactory::createComponent('tr', $cssClass);
		foreach ($cellsContent as $i => $cc) {
			$tr->addChild(ComponentFactory::createComponent('td', _iod($this->cellCssClasses, $i))
							->addChild(ComponentFactory::toHTMLElement($cc)));
		}
		$this->table->getChild(self::$bodyKey)->addChild($tr) ;
		return $this ;
	}

	/**
	 * 
	 * @param array $cssClasses
	 * @return \temple\web\html\bootstrap\Table
	 */
	public function setCellCssClasses(array $cssClasses) {
		$this->cellCssClasses = $cssClasses ;
		return $this ;
	}
	
	/**
	 * 
	 * @return \temple\web\html\bootstrap\Table
	 */
	public static function create(array $tableVariants = [], $cssClass = null) {
		$t = new Table($cssClass) ;
		foreach($tableVariants as $tv) {
			$t->table->addCssClass($tv) ;
		}
		return $t ;
	}
	
}
