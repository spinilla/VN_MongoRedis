package com.vienext.formacion.eventdriven.service.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;

import com.vienext.formacion.eventdriven.service.common.messages.Order;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * payment-service
 * com.vienext.formacion.eventdriven.service.payment
 * 
 * Application
 * TODO
 * 
 * @author Sergio Pinilla (Viewnext/IBM)
 * @version 0.1
 * @since 29 jul. 2018
 *
 */
@SpringBootApplication
@EnableBinding(Sink.class)
@Slf4j
public class Application {

  @Autowired private PaymentService paymentService;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @StreamListener(Sink.INPUT)
  public void processOrder(Order order) {
    log.info("Processing order: " + order);
    Order o = paymentService.processOrder(order);
    if (o != null)
      log.info("Final response: " + (o.getProduct().getPrice() + o.getShipment().getPrice()));
  }

  @Bean
  public AlwaysSampler defaultSampler() {
    return new AlwaysSampler();
  }
}
