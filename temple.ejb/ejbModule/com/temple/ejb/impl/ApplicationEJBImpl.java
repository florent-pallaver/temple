package com.temple.ejb.impl;


/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
// @Stateful
// @Local(ApplicationLocalEJB.class)
// @Remote(ApplicationEJB.class)
// public class ApplicationEJBImpl extends AbstractManagerBean implements ApplicationLocalEJB {
//
// private static final long serialVersionUID = 1L;
// @EJB
// private ApplicationEJBHelper helper;
// @EJB
// private SessionManager sessionEJB;
// @EJB
// private ResourceLocalEJB<AccessEnum, PrivilegeSetImpl> resourceEJB;
// private final Map<Class<? extends ActionType<DefaultAccessType>>, ActionCreator<DefaultAccessType>>
// creatorDelegates = new HashMap<>();
//
// private final Map<Class<? extends ActionType<DefaultAccessType>>, ActionExecuter<DefaultAccessType>>
// executerDelegates = new HashMap<>();
// private final LanguageEnum sessionLanguage = LanguageEnum.DEFAULT;
// @Override
// public void register(Class<? extends ActionType<DefaultAccessType>> c, ActionCreator<DefaultAccessType> ac) {
// this.creatorDelegates.put(c, ac);
// }
//
// @Override
// public void register(Class<? extends ActionType<DefaultAccessType>> c, ActionExecuter<DefaultAccessType> ae) {
// this.executerDelegates.put(c, ae);
// }
// @Override
// public Collection<Class<? extends ActionType<DefaultAccessType>>> getCreatableTypes() {
// return this.creatorDelegates.keySet();
// }
//
// @Override
// public Collection<Class<? extends ActionType<DefaultAccessType>>> getExecutableTypes() {
// return this.executerDelegates.keySet();
// }
//
// @Override
// public <T extends ActionType<DefaultAccessType>> Action<T> createAction(T at) throws LocalizedTempleException {
// final Action<T> a;
// try {
// final Class<?> typeClass = at.getClass();
// final ActionCreator<DefaultAccessType> ac = this.creatorDelegates.get(typeClass);
// if (ac == null) {
// throw new UnsupportedActionTypeException(typeClass);
// }
// switch (at.getAccessType()) {
// case READ:
// // FIXME
// break;
// case WRITE:
// default:
// // FIXME
// break;
// }
// a = ac.createAction(at);
// } catch (final LocaleViewableTempleException e) {
// throw new LocalizedTempleException(e, this.sessionLanguage);
// } catch (final Throwable t) {
// this.logThrowable(t);
// throw new LocalizedTempleException(new UnexpectedThrowableException(t), this.sessionLanguage);
// }
// return a;
// }
//
// @Override
// public View execute(Action<? extends ActionType<DefaultAccessType>> a) throws LocalizedTempleException {
// final View v;
// try {
// final Class<?> typeClass = a.getType().getClass();
// final ActionExecuter<DefaultAccessType> ae = this.executerDelegates.get(typeClass);
// if (ae == null) {
// throw new UnsupportedActionTypeException(typeClass);
// }
// this.helper.checkConstraint(a);
// v = ae.execute(a);
// } catch (final LocaleViewableTempleException e) {
// throw new LocalizedTempleException(e, this.sessionLanguage);
// } catch (final Throwable t) {
// this.logThrowable(t);
// throw new LocalizedTempleException(new UnexpectedThrowableException(t), this.sessionLanguage);
// }
// return v;
// }
// @Override
// public void register(Class<? extends ActionType<DefaultAccessType>> c, ActionCreator<DefaultAccessType> ac) {
// // TODO Auto-generated method stub
// }
//
// @Override
// public void register(Class<? extends ActionType<DefaultAccessType>> c, ActionExecuter<DefaultAccessType> ae) {
// // TODO Auto-generated method stub
// }
// }
