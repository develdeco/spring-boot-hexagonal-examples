package org.develdeco.hexagonal.micro_simple.adapter.out.jpa;

import org.develdeco.hexagonal.micro_simple.domain.port.UserRepositoryPort;

public class JpaUserRepositoryAdapter implements UserRepositoryPort {

    @Override
    public boolean doesUserExists(Long userId) {
        return false;
    }

    @Override
    public boolean doesUserBelongsToClient(Long userId, Long clientId) {
        return false;
    }
}
