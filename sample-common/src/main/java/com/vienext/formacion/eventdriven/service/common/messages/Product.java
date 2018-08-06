package com.vienext.formacion.eventdriven.service.common.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * sample-common com.vienext.formacion.eventdriven.service.common.messages
 *
 * <p>Product
 *
 * @author Sergio Pinilla (Viewnext/IBM)
 * @version 0.1
 * @since 29 jul. 2018
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Product {

  private Integer id;
  @NonNull
  private String name;
  private int price;
}
