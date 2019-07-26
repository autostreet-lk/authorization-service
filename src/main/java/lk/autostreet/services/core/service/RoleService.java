package lk.autostreet.services.core.service;


import lk.autostreet.services.core.exception.UserRoleNotCreatedException;
import lk.autostreet.services.core.exception.UserRoleNotFoundException;
import lk.autostreet.services.core.exception.UserTypeNotSupportedException;
import lk.autostreet.services.core.model.Role;
import lk.autostreet.services.core.model.UserType;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Role create(Role role) throws UserRoleNotCreatedException;

    Optional<Role> findByName(String name);

    Role getRoleByName(String name) throws UserRoleNotFoundException;

    List<String> getRoleNamesByUserType(UserType userType) throws UserTypeNotSupportedException;
}
