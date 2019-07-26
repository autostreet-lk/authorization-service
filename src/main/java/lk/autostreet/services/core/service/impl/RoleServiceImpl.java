package lk.autostreet.services.core.service.impl;

import lk.autostreet.services.core.exception.UserRoleNotCreatedException;
import lk.autostreet.services.core.exception.UserRoleNotFoundException;
import lk.autostreet.services.core.exception.UserTypeNotSupportedException;
import lk.autostreet.services.core.model.AccessRole;
import lk.autostreet.services.core.model.Role;
import lk.autostreet.services.core.model.UserType;
import lk.autostreet.services.core.repository.RoleRepository;
import lk.autostreet.services.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository repository;

    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role create(Role role) throws UserRoleNotCreatedException {
        if (role == null) {
            throw new UserRoleNotCreatedException("user role is not set");
        }
        try {
            return repository.save(role);
        } catch (Exception ex) {
            throw new UserRoleNotCreatedException(ex.getMessage());
        }
    }

    @Override
    public Optional<Role> findByName(String name) {

        if (name == null) {
            return Optional.empty();
        }
        Role role = this.repository.findByName(name);
        return Optional.ofNullable(role);
    }

    @Override
    public Role getRoleByName(String name) throws UserRoleNotFoundException {
        Optional<Role> optionalRole = this.findByName(name);

        if (!optionalRole.isPresent()) {
            throw new UserRoleNotFoundException("role [" + name + "] does not found");
        }
        return optionalRole.get();
    }

    @Override
    public List<String> getRoleNamesByUserType(UserType userType) throws UserTypeNotSupportedException {

        if (userType == null) {
            throw new UserTypeNotSupportedException("A valid user type is required");
        }

        List<String> roleNames = new ArrayList<>();

        if (userType.equals(UserType.SYSTEM_ADMIN)) {
            roleNames.add(AccessRole.ROLE_SYSTEM_ADMIN);
            roleNames.add(AccessRole.ROLE_COMPANY_ADMIN);
            roleNames.add(AccessRole.ROLE_COMPANY_USER);
        } else if (userType.equals(UserType.COMPANY_ADMIN)) {
            roleNames.add(AccessRole.ROLE_COMPANY_ADMIN);
            roleNames.add(AccessRole.ROLE_COMPANY_USER);
        } else if (userType.equals(UserType.COMPANY_USER)) {
            roleNames.add(AccessRole.ROLE_COMPANY_USER);
        }

        if (roleNames.isEmpty()) {
            throw new UserTypeNotSupportedException("There is no role found for user type [" + userType.getValue() + "] ");
        }
        return roleNames;
    }
}
