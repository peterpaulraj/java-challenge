/**
 * 
 */
package jp.co.axa.apidemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.axa.apidemo.entities.Role;
import jp.co.axa.apidemo.entities.RolesEnum;

/**
 * @author Peter
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(RolesEnum name);
}