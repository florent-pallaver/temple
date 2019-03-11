package com.temple.util.security;

import java.security.Principal;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 */
public interface SystemUser extends Principal {}

class SysUser implements SystemUser {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
