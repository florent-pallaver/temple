<?php

namespace temple\web\html\bootstrap;

/**
 *
 * @author florent
 */
interface FormComponent extends Component {

	/**
	 * @param string $formId the id of the form this component is part of
	 */
	public function setForm($formId) ;
	
}
