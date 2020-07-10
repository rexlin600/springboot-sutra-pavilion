package xyz.rexlin600.flyway.service;


import xyz.rexlin600.flyway.entity.TbFlyway;

/**
 * Flyway service
 *
 * @author hekunlin
 */
public interface FlywayService {

	/**
	 * Save *
	 *
	 * @param tbFlyway tb flyway
	 */
	void save(TbFlyway tbFlyway);

	/**
	 * Find one tb flyway
	 *
	 * @param id id
	 * @return the tb flyway
	 */
	TbFlyway findOne(Long id);

	/**
	 * Find count long
	 *
	 * @return the long
	 */
	Long findCount();

}