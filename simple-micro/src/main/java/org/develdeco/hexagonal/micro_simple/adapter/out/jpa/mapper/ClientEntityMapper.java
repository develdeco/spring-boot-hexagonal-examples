package org.develdeco.hexagonal.micro_simple.adapter.out.jpa.mapper;

import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.entity.ClientEntity;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.Client;

public class ClientEntityMapper {

    public static Client buildClient(ClientEntity clientEntity) {
        Client client = new Client();
        client.setId(clientEntity.getId());
        client.setName(clientEntity.getName());
        return client;
    }
}
