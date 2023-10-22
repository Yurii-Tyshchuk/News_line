package ru.tyshchuk.newsline.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tyshchuk.newsline.domain.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUser {
    private String firstName;
    private String secondName;
    private String surname;
    private String username;
    private String password;
    private String email;

    public static User create(RequestUser user) {
        return User.builder()
                .firstName(user.firstName)
                .secondName(user.secondName)
                .username(user.username)
                .surname(user.surname)
                .email(user.email)
                .build();
    }
}
