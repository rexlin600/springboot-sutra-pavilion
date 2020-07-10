package xyz.rexlin600.jpa.config;


import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;

/**
 * Manul insert generator
 *
 * @author hekunlin
 */
@SuppressWarnings("ALL")
public class ManulInsertGenerator extends IdentityGenerator {

	/**
	 * Generate serializable
	 *
	 * @param s   s
	 * @param obj obj
	 * @return the serializable
	 * @throws HibernateException hibernate exception
	 */
	@Override
	public Serializable generate(SharedSessionContractImplementor s, Object obj) throws HibernateException {
		Serializable id = s.getEntityPersister(null, obj).getClassMetadata().getIdentifier(obj, s);

		if (id != null && Integer.valueOf(id.toString()) > 0) {
			return id;
		} else {
			return super.generate(s, obj);
		}
	}
}