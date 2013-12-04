package com.temple.view;

import java.io.Serializable;

/**
 * Base contract for an object to be viewed in another way.
 * <p>
 * If the view or the detailed view are lazily initialized, they should be initialized prior to any serialization. <br>
 * That is because a Viewable should be "translated" by the sending tier, so that nothing is required for the receiving tier to get the "translation".
 * 
 * @author cr9z1k
 * @version 1.0
 * @see View
 */
public interface Viewable extends Serializable {}
