package com.vienext.formacion.eventdriven.service.shipment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vienext.formacion.eventdriven.service.common.messages.Order;
import com.vienext.formacion.eventdriven.service.common.messages.Shipment;
import com.vienext.formacion.eventdriven.service.common.messages.ShipmentType;

/**
 * shipment-service com.vienext.formacion.eventdriven.service.shipment
 *
 * <p>ShipmentService
 *
 * @author Sergio Pinilla (Viewnext/IBM)
 * @version 0.1
 * @since 29 jul. 2018
 */
@Service
public class ShipmentService {

  private List<Shipment> shipments;

  public ShipmentService() {
    shipments = new ArrayList<>();
    shipments.add(new Shipment(1, ShipmentType.CAR, LocalDate.now(), 50));
    shipments.add(new Shipment(2, ShipmentType.PLANE, LocalDate.now(), 200));
    shipments.add(new Shipment(3, ShipmentType.SHIP, LocalDate.now(), 100));
    shipments.add(new Shipment(4, ShipmentType.TRAIN, LocalDate.now(), 20));
  }

  public Shipment processOrder(Order order) {
    return shipments
        .stream()
        .filter(s -> s.getType().equals(order.getShipment().getType()))
        .findAny()
        .get();
  }
}
