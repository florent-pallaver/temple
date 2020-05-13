<?php

namespace temple\web;

/**
 * Base contract for an object to be accessible through an URL
 *
 * @author flominou
 */
interface URLAccessible {

    
    /**
     * @return \temple\URL the absolute URL to access this object
     */
    function toURL() ;
    
}
