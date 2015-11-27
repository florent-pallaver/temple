<?php

namespace temple\controller;

use Exception;
use temple\view\PagingData ;

/**
 * Description of AbstractPagingController.<br>
 * Base class for controller doing paging.
 *
 * @author florent
 */
abstract class AbstractPagingController extends AbstractRequestController {

	/**
	 * @var PagingData 
	 */
	private $pagingData ;
	
	protected function __construct($paging, $itemsName) {
		parent::__construct();
//		if (!isset($_SESSION[$paging])) {
//			$_SESSION[$paging] = PagingData::DEFAULT_ITEMS_PER_PAGE;
//		}
//		$_SESSION[$paging] = $this->getIntPostParam('ipp', ['default' => $_SESSION[$paging]]);
//		$this->pagingData = new PagingData($_SESSION[$paging], $itemsName) ;
	}

	protected final function getPagingData() {
//		$items = [] ;
//		$page = $this->getIntGetParam('page', ['default' => 1]) - 1;
//		try {
//			$total = $this->getTotalCount();
//			if($total) {
//				$ipp = $this->pagingData->getItemsPerPage() ;
//				$this->pagingData->setTotal($total) ;
//				if (($page * $ipp) >= $total) {
//					$page = 0;
//				}
//				$offset = $page * $ipp ;
//				$rows = $this->execute($this->getSelectQuery() . " LIMIT $offset, $ipp") ;
//				$items = [] ;
//				while($row = $rows->fetch_assoc()) {
//					$items[] = $this->createItem($row) ;
//				}
//				$rows->free() ;
//				$this->pagingData->setRange($offset, count($items)) ;
//			}
//		} catch (Exception $e) {
//			$this->exception($e);
//		}
//		return [$items, $this->pagingData];
	}

	protected abstract function getTotalCount();

	protected abstract function getSelectQuery() ;
	
	protected abstract function createItem(array $row) ;
}
