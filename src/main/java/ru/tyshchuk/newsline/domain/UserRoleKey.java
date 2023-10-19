package ru.tyshchuk.newsline.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserRoleKey implements Serializable {
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "role_id", nullable = false)
    private Long roleId;
}
