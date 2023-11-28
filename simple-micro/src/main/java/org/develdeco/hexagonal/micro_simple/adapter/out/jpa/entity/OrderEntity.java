package org.develdeco.hexagonal.micro_simple.adapter.out.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.mapper.OrderEntityMapper;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.Order;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "ORDER")
public class OrderEntity {

    @Id
    Long id;

    @Column(name = "CLIENT_ID")
    Long clientId;

    @Column(name = "USER_ID")
    Long userId;

    @Column(name = "STATUS")
    String status;

    @Column(name = "PAYMENT_STATUS")
    String paymentStatus;

    @Column(name = "DELIVERY_TYPE")
    String deliveryType;

    @Column(name = "DELIVERY_DATE")
    LocalDate deliveryDate;

    @Column(name = "TOTAL")
    Double total;

    @Column(name = "CREATION_DATE")
    LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "LAST_UPDATE_DATE")
    LocalDateTime lastUpdateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    ClientEntity client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    UserEntity user;

    @OneToMany(mappedBy = "orderId")
    List<OrderProductEntity> products;

    public Order toModel() {
        return OrderEntityMapper.buildOrder(this);
    }
}
