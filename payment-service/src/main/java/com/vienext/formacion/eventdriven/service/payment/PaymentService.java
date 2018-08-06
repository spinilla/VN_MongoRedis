package com.vienext.formacion.eventdriven.service.payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vienext.formacion.eventdriven.service.common.messages.Order;

@Service
public class PaymentService {
	
	private List<Order> orders = new ArrayList<>();
	
	public Order processOrder(Order order) {
		Optional<Order> oo = orders.stream().filter(o -> o.getId().intValue() == order.getId().intValue()).findFirst();
		if (oo.isPresent()) {
			Order o = oo.get();
			if (o.getProduct().getId() != null)
				order.setProduct(o.getProduct());
			else if (o.getShipment().getId() != null)
				order.setShipment(o.getShipment());
			return order;
		} else orders.add(order);
		return null;
	}

}
