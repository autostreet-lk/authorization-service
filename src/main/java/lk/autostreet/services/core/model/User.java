package lk.autostreet.services.core.model;

import lombok.Data;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User extends AbstractAuditable<User, Long> {

    private String username;
    private String password;

    @Column(name = "company_id")
    private Long company;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type",nullable = false)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
