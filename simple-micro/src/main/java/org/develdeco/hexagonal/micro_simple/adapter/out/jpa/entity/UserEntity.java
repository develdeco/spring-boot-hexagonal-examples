package org.develdeco.hexagonal.micro_simple.adapter.out.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.mapper.UserEntityMapper;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Data
@Entity
@Table(name = "USER")
public class UserEntity {

    @Id
    Long id;

    @Column(name = "NAME")
    String name;

    public User toModel() {
        return UserEntityMapper.buildUser(this);
    }
}
