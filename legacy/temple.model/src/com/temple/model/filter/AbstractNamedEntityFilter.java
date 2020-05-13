/**
 *
 */
package com.temple.model.filter;

import javax.persistence.metamodel.SingularAttribute;

import com.temple.model.TempleEntity;

/**
 * TODOC
 *
 * @author flominou
 */
public abstract class AbstractNamedEntityFilter<E extends TempleEntity>
		extends AbstractDynamicPageableFilter<E, NamedEntity> {

	private static final long serialVersionUID = 1L;

	private static final SingularAttribute<?, ?>[] selectedAttributes = { null, null };

	protected AbstractNamedEntityFilter() {
		super();
	}

	protected AbstractNamedEntityFilter(short perPageCount) {
		super(perPageCount);
	}

	@Override
	public Class<NamedEntity> getResultClass() {
		return NamedEntity.class;
	}

	protected abstract SingularAttribute<? super E, ?> getIdField();

	protected abstract SingularAttribute<? super E, ?> getNameField();

	@Override
	protected SingularAttribute<? super E, ?> getCountedField() {
		return this.getIdField();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected SingularAttribute<? super E, ?>[] getSelectedAttributes() {
		if (AbstractNamedEntityFilter.selectedAttributes[0] == null) {
			AbstractNamedEntityFilter.selectedAttributes[0] = this.getIdField();
			AbstractNamedEntityFilter.selectedAttributes[1] = this.getNameField();
		}
		return (SingularAttribute<? super E, ?>[]) AbstractNamedEntityFilter.selectedAttributes;
	}

}
