<?php

namespace temple\web\mail;

final class Mail {

    /**
     * @var array
     */
    public $tos = [];

    /**
     * @var array
     */
    public $ccs = [];

    /**
     * @var array
     */
    public $bccs = [];
    
    public $replyToMail = null;
    
    public $replyToAlias = null;
    
    public $usingTemplate = false;
    
    public $subject = '';
    
    public $content = '';

}
