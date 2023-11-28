package org.develdeco.hexagonal.micro_simple.adapter.out.jpa;

import org.develdeco.hexagonal.micro_simple.domain.port.ClientRepositoryPort;

public class JpaClientRepositoryAdapter implements ClientRepositoryPort {

    @Override
    public boolean doesClientExists(Long clientId) {
        return false;
    }
}
