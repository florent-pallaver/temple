package com.temple.ejb.impl.resource;

// @Stateless
// @Local(ResourceEJBLocal.class)
// public abstract class AbstractResourceEJB extends AbstractManagerBean implements ResourceEJB<DefaultAccessType> {
//
// private static final long serialVersionUID = 1L;
// @PersistenceContext(unitName = "com.jvp5.ejb.persistenceUnit.default")
// private EntityManager em;
// @Override
// public <AR extends AccessRights<DefaultAccessType, ?>> boolean isAllowedOn(User<AR, ?> u, DefaultAccessType a,
// Resource<AR> r) {
// final boolean allowed;
// if (r == null) {
// // with null read is always allowed !
// allowed = DefaultAccessType.READ.equals(a);
// } else {
// final AR privileges = r.getAccessRights();
// if (privileges.allowsForAll(a)) {
// allowed = true;
// } else {
// final DefaultRealm m;
// if (u.equals(r.getOwner())) {
// m = DefaultRealm.OWNER;
// } else if (this.haveOneGroupInCommon(r, u)) {
// m = DefaultRealm.GROUP;
// } else if (!this.sessionHelper.isGuestUser(u)) {
// m = DefaultRealm.SIGNED;
// } else {
// m = DefaultRealm.OTHER;
// }
// allowed = privileges.allows(m, a);
// }
// }
// return allowed;
// }
//
// private boolean haveOneGroupInCommon(Resource<?> r1, Resource<?> r2) {
// // FIXME
// return false;
// }
//
// @Override
// public <R extends Resource<?>> R find(Class<R> clazz, Serializable id) throws AccessDeniedException {
// final R r = this.em.find(clazz, id);
// this.check(DefaultAccessType.READ, r);
// return r;
// }
//
// private void check(DefaultAccessType a, Resource<?> r) throws AccessDeniedException {
// // if (!this.isAllowedOn(a, r)) {
// // throw new AccessNotAllowedException(a);
// // }
// }
// }
