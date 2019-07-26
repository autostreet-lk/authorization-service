package lk.autostreet.services.core.service;

import lk.autostreet.services.core.model.User;
import lk.autostreet.services.core.exception.UserNotCreatedException;
import lk.autostreet.services.core.exception.UserNotFoundException;

import java.util.Optional;

public interface UserService {

    User create(User user) throws UserNotCreatedException;

    Optional<User> findByUsername(String username);

    User getByUsername(String username) throws UserNotFoundException;
}
