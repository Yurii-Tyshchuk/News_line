package ru.tyshchuk.newsline.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tyshchuk.newsline.domain.embeddings.UserRoleKey;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_role")
public class UserRole {
    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    @EmbeddedId
    UserRoleKey id;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    Role role;
}
