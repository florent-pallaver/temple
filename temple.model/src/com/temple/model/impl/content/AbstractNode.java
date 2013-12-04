package com.temple.model.impl.content;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.temple.model.content.Node;
import com.temple.model.impl.AbstractGroup;
import com.temple.model.impl.AbstractIntegerIdResource;
import com.temple.model.impl.AbstractUser;
import com.temple.model.impl.access.DefaultMod;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractNode extends AbstractIntegerIdResource implements Node<DefaultMod> {

	public static final int RADIX_MAX_LENGTH = 32;

	private static final long serialVersionUID = 1L;

	@Enumerated
	@Column(updatable = false, nullable = false)
	private DefaultNodeKind kind;

	@Column(length = AbstractNode.RADIX_MAX_LENGTH, nullable = false)
	private String radix;

	@ManyToOne(optional = false)
	@JoinColumn(name = "parentId", nullable = false)
	private AbstractNode parent;

	@Transient
	private transient String path;

	/**
	 * Constructor.
	 * TODOC
	 */
	protected AbstractNode() {
		super();
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param accessRights
	 * @param owner
	 * @param primaryGroup
	 * @param kind
	 * @param radix
	 * @param parent
	 * @param path
	 */
	protected AbstractNode(DefaultMod accessRights, AbstractUser owner, AbstractGroup primaryGroup, DefaultNodeKind kind, String radix, AbstractNode parent,
			String path) {
		super(accessRights, owner, primaryGroup);
		this.kind = kind;
		this.radix = radix;
		this.parent = parent == null ? this : parent;
		this.path = path;
	}

	@Override
	public DefaultNodeKind getKind() {
		return this.kind;
	}

	@Override
	public String getPath() {
		if (this.path == null) {
			this.path = new StringBuilder(this.parent == null ? "" : this.parent.getPath()).append(Node.PATH_SEPARATOR).append(this.radix).toString();
		}
		return this.path;
	}

	@Override
	public AbstractNode getParent() {
		return this.parent;
	}
}
