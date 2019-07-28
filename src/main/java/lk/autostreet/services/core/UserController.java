package lk.autostreet.services.core;

import lk.autostreet.services.core.exception.UserNotCreatedException;
import lk.autostreet.services.core.model.User;
import lk.autostreet.services.core.model.UserType;
import lk.autostreet.services.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User create() throws UserNotCreatedException {

        User user = new User();
        user.setUsername("chathuranga");
        user.setPassword("123456");
        user.setSeller(1L);
        user.setUserType(UserType.SYSTEM_ADMIN);
        return userService.create(user);
    }


    @RolesAllowed({"ROLE_SYSTEM_ADMIN"})
    @GetMapping("/test")
    public String test() {

        return "Admin user";
    }
}
