package lk.autostreet.services.core.service.impl;

import lk.autostreet.services.core.exception.UserAlreadyRegisteredException;
import lk.autostreet.services.core.exception.UserNotCreatedException;
import lk.autostreet.services.core.exception.UserNotFoundException;
import lk.autostreet.services.core.model.Role;
import lk.autostreet.services.core.model.User;
import lk.autostreet.services.core.model.UserStatus;
import lk.autostreet.services.core.repository.UserRepository;
import lk.autostreet.services.core.service.RoleService;
import lk.autostreet.services.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User create(User user) throws UserNotCreatedException {
        this.validateForCreate(user);

        Optional<User> userOptional = this.findByUsername(user.getUsername());
        if (userOptional.isPresent()) {
            log.debug("username [{}] is already registered", user.getUsername());
            throw new UserNotCreatedException("username '" + user.getUsername() + "' is already registered");
        }

        try {
            List<String> roleNames = roleService.getRoleNamesByUserType(user.getUserType());
            log.info(" [{}] roles were found for user type [{}]", roleNames.size(), user.getUserType().getValue());
            for (String roleName : roleNames) {
                Role role = roleService.getRoleByName(roleName);
                user.addRole(role);
            }

            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            user.setStatus(UserStatus.ACTIVE);
            return userRepository.save(user);

        } catch (Exception ex) {
            log.error(" error while creating new user [{}] ", ex.getMessage());
            throw new UserNotCreatedException(ex.getMessage());
        }
    }

    private void validateForCreate(User user) throws UserNotCreatedException {
        if (user == null) {
            log.debug(" user is not set ");
            throw new UserNotCreatedException("User is not set");
        } else if (user.getSeller() == null) {
            log.debug(" seller is not set ");
            throw new UserNotCreatedException("Seller is not set");
        } else if (user.getUserType() == null) {
            log.debug(" user type is not set ");
            throw new UserNotCreatedException("User type is not set");
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getByUsername(String username) throws UserNotFoundException {
        if (username == null) {
            throw new UserNotFoundException("username is required");
        }
        Optional<User> userOptional = findByUsername(username);
        return userOptional.orElseThrow(() -> new UserNotFoundException("No user found with username [" + username + "] "));
    }
}
