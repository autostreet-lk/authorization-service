package lk.autostreet.services.core.model;

import lombok.Data;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "roles")
public class Role extends AbstractAuditable<User, Long> {

    @Column(nullable = false)
    private String name;
}
