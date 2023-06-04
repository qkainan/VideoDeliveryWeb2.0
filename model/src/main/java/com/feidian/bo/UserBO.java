package com.feidian.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBO {

    private long id;
    private String username;
    private String password;

    private String nickname;
    private String emailAddress;

    public UserBO( String username, String password, String nickname, String emailAddress) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.emailAddress = emailAddress;
    }

}
