package org.develdeco.hexagonal.micro_simple.adapter.in.rest.dto;

import lombok.Builder;
import lombok.Data;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.Client;

@Builder
@Data
public class ClientDTO {

    Long id;

    String name;

    public static ClientDTO fromModel(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .build();
    }
}
