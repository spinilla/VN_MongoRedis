package com.vienext.formacion.eventdriven.service.order;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import com.vienext.formacion.eventdriven.service.common.messages.Order;
import com.vienext.formacion.eventdriven.service.common.messages.OrderStatus;
import com.vienext.formacion.eventdriven.service.common.messages.OrderType;
import com.vienext.formacion.eventdriven.service.common.messages.Product;
import com.vienext.formacion.eventdriven.service.common.messages.Shipment;
import com.vienext.formacion.eventdriven.service.common.messages.ShipmentType;

import lombok.extern.slf4j.Slf4j;

/**
 * order-service com.vienext.formacion.eventdriven.service.order
 *
 * <p>Application</p>
 *
 * @author Sergio Pinilla (Viewnext/IBM)
 * @version 0.1
 * @since 29 jul. 2018
 */
@Slf4j
@SpringBootApplication
@EnableBinding(Source.class)
public class Application {

  private int index = 0;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  /**
   * Method Application.orderSource
   *
   * @return
   */
  @Bean
  @InboundChannelAdapter(
      value = Source.OUTPUT,
      poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
  public MessageSource<Order> orderSource() {
    return () -> {
      Order o =
          new Order(
              index++,
              OrderType.PURCHASE,
              LocalDateTime.now(),
              OrderStatus.NEW,
              new Product("Example#2"),
              new Shipment(ShipmentType.SHIP));
      log.info("Sending order: " + o);
      return new GenericMessage<>(o);
    };
  }

  /**
   * Method Application.defaultSampler
   *
   * @return
   */
  @Bean
  public AlwaysSampler defaultSampler() {
    return new AlwaysSampler();
  }
}
