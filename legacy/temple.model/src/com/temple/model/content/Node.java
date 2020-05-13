package com.temple.model.content;

import com.temple.model.Resource;
import com.temple.model.access.AccessRights;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface Node<PS extends AccessRights<?, ?>> extends Resource<PS> {

	/**
	 * Character to use as the path separator.
	 */
	char PATH_SEPARATOR = '/';

	NodeKind getKind();

	/**
	 * @return the path to this {@link Node}
	 */
	String getPath();

	/**
	 * @return the parent {@link Node}, <code>null</code> if none.
	 */
	Node<?> getParent();
	/**
	 * @param i the position
	 * @return the child {@link Node} at the given position, <code>null</code> if none exists.
	 */
	// N getChild(int i);
	/**
	 * @param childPath a path
	 * @return the child {@link Node} at the given path, <code>null</code> if none exists.
	 */
	// N getChild(String childPath);
	/**
	 * @return the children {@link Node}s, {@link #NO_CHILDREN} if none.
	 */
	// N[] getChildren();
}
