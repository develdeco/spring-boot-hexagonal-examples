package org.develdeco.hexagonal.micro_simple.adapter.in.rest;

import lombok.AllArgsConstructor;
import org.develdeco.hexagonal.micro_simple.adapter.in.rest.dto.GetParametersDTO;
import org.develdeco.hexagonal.micro_simple.adapter.in.rest.dto.InputOrderDTO;
import org.develdeco.hexagonal.micro_simple.adapter.in.rest.dto.OrderDTO;
import org.develdeco.hexagonal.micro_simple.application.port.OrderServicePort;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.Order;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.QueryParameters;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@Validated
@AllArgsConstructor
public class OrderControllerAdapter {

    private final OrderServicePort orderServicePort;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> listOrders(GetParametersDTO getParameters) {
        QueryParameters queryParameters = getParameters.toQueryParameters();
        List<Order> orders = orderServicePort.filterOrders(queryParameters);

        List<OrderDTO> orderDTOList = orders.stream().map(OrderDTO::fromModel).collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOList);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long orderId) {
        Order order = orderServicePort.getOrder(orderId);
        return ResponseEntity.ok(OrderDTO.fromModel(order));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody @Valid InputOrderDTO inputOrderDTO) {
        Order order = inputOrderDTO.toModel();
        order = orderServicePort.placeOrder(order);

        return ResponseEntity.ok(OrderDTO.fromModel(order));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> modifyOrder(@PathVariable Long orderId, @RequestBody @Valid InputOrderDTO inputOrderDTO) {
        Order order = inputOrderDTO.toModel(orderId);
        order = orderServicePort.modifyOrder(order);

        return ResponseEntity.ok(OrderDTO.fromModel(order));
    }
}
