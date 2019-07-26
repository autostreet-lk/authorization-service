package lk.autostreet.services.core.repository;

import lk.autostreet.services.core.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
