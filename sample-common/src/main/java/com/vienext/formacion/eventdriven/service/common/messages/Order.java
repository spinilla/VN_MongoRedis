package com.vienext.formacion.eventdriven.service.common.messages;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  private Integer id;
  private OrderType type;
  private LocalDateTime createdAt;
  private OrderStatus status;
  private Product product;
  private Shipment shipment;
}
