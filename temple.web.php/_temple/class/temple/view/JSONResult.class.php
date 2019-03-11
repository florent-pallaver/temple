<?php

namespace temple\view;

use temple\data\Status;
use temple\data\Messages;

final class JSONResult implements Renderable, \JsonSerializable {

    private $status;
    private $data;
    private $feedbacks;
    private $messages;
    private $reload;
    private $resetForm;
    private $goTo;

    function __construct(Status $status, $messages = true, array $data = [], array $feedbacks = [], $reload = false, $resetForm = false, $goTo = '') {
        $this->status = $status ? $status->ordinal() : 0;
        $this->data = $data;
        $this->feedbacks = $feedbacks;
        $this->messages = $messages ? Messages::getInstance()->popAll() : [];
        $this->reload = $reload;
        $this->resetForm = $resetForm;
        $this->goTo = $goTo;
    }

    public function jsonSerialize() {
        return ['status' => $this->status, 'data' => $this->data, 'feedbacks' => $this->feedbacks, 'messages' => $this->messages,
            'reload' => $this->reload, 'resetForm' => $this->resetForm, 'goTo' => $this->goTo];
    }

    public function render() {
        header('Content-Type: application/json; charset=UTF-8');
        echo json_encode($this);
    }

}
