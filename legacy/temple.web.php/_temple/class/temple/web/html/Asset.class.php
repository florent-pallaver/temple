<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace temple\web\html;

/**
 *
 * @author flominou
 */
interface Asset {

	public function getVersion() ;
	
	public function getBasePath() ;
	
	public function getJsFile() ;
	
	public function getCssFile() ;
	
	public function getCredits() ;
	
}
