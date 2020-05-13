package com.temple.service.ejb.model;

import javax.ejb.Local;

import com.temple.model.IdentifierGenerator;
import com.temple.service.TempleManager;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Local
public interface IdentifierManager extends IdentifierGenerator, TempleManager {}
