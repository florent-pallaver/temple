package com.temple.model.access;

import com.temple.model.Resource;
import com.temple.model.User;
import com.temple.util.Enumerable;
import com.temple.view.LocaleViewable;

/**
 * Allows permissions distribution.
 * <p>
 * Realms allow permissions distribution on a {@link Resource} according to the existing relationship between a
 * {@link User} and that {@link Resource}. So that for any resource, there exists a set of permissions for each Realm.
 */
public interface Realm extends LocaleViewable, Enumerable {}
