package com.temple.ejb.model;

import javax.ejb.Local;

import com.temple.ejb.TempleManager;
import com.temple.model.IdentifierGenerator;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Local
public interface IdentifierManager extends IdentifierGenerator, TempleManager {}
