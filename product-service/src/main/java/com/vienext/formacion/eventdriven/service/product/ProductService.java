package com.vienext.formacion.eventdriven.service.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.esotericsoftware.minlog.Log;
import com.vienext.formacion.eventdriven.service.common.messages.Order;
import com.vienext.formacion.eventdriven.service.common.messages.Product;

@Service
public class ProductService {

  private List<Product> products;

  /** Public Contructor ProductService */
  public ProductService() {
    products = new ArrayList<>();
    products.add(new Product(1, "Example#1", 500));
    products.add(new Product(2, "Example#2", 100));
    products.add(new Product(3, "Example#3", 1000));
    products.add(new Product(4, "Example#4", 200));
  }

  /**
   * Method ProductService.processOrder
   *
   * @param order
   * @return
   */
  public Product processOrder(Order order) {
    return products
        .stream()
        .filter(
            p -> {
                Log.info("-->" + p + "<--");
              return p.getName().equals(order.getProduct().getName());
            })
        .findAny()
        .get();
  }
}
