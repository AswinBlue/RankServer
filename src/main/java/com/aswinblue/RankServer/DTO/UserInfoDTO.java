package com.aswinblue.RankServer.DTO;

import com.aswinblue.RankServer.Entity.UserInfoEntity;

public class UserInfoDTO {
    private String name;
    
    public UserInfoDTO(String name) {
        this.name = name;
    }
    public UserInfoEntity toEntity() {
        return new UserInfoEntity(null, this.name, 0); // id에 null을 넣는다. @GeneratedValue에 의해 자동으로 생성된다.
    }
}
