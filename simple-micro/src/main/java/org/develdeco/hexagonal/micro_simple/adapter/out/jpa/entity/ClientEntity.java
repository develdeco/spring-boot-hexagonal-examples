package org.develdeco.hexagonal.micro_simple.adapter.out.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.mapper.ClientEntityMapper;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.Client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Data
@Entity
@Table(name = "CLIENT")
public class ClientEntity {

    @Id
    Long id;

    @Column(name = "NAME")
    String name;

    public Client toModel() {
        return ClientEntityMapper.buildClient(this);
    }
}
