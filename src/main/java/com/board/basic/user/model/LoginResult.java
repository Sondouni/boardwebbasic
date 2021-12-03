package com.board.basic.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResult {
    private int result;
    private UserEntity loginUser;
}
