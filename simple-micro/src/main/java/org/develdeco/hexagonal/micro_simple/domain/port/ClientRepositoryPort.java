package org.develdeco.hexagonal.micro_simple.domain.port;

public interface ClientRepositoryPort {

    boolean doesClientExists(Long clientId);
}
