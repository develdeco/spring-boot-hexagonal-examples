package org.develdeco.hexagonal.micro_simple.adapter.in.rest.dto;

import lombok.Builder;
import lombok.Data;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.User;

@Builder
@Data
public class UserDTO {

    Long id;

    String name;

    public static UserDTO fromModel(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
