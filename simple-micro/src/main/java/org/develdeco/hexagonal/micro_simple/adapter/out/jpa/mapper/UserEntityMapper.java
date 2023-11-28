package org.develdeco.hexagonal.micro_simple.adapter.out.jpa.mapper;

import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.entity.UserEntity;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.User;

public class UserEntityMapper {

    public static User buildUser(UserEntity clientEntity) {
        User user = new User();
        user.setId(clientEntity.getId());
        user.setName(clientEntity.getName());
        return user;
    }
}
