/**
 * 
 */
package com.temple.model.impl.content;

import com.temple.model.content.NodeKind;

/**
 * @author Florent
 */
public enum DefaultNodeKind implements NodeKind {
	ROOT,
	SECTION,
	CATEGORY,
	ITEM;

	@Override
	public DefaultNodeKind toEnum() {
		return this;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}
}
