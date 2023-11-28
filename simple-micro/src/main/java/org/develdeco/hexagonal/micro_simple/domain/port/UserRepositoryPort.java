package org.develdeco.hexagonal.micro_simple.domain.port;

public interface UserRepositoryPort {

    boolean doesUserExists(Long userId);

    boolean doesUserBelongsToClient(Long userId, Long clientId);
}
