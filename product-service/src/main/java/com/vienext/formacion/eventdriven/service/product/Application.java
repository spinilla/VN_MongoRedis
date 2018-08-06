package com.vienext.formacion.eventdriven.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.SendTo;

import com.vienext.formacion.eventdriven.service.common.messages.Order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableBinding(Processor.class)
public class Application {

	@Autowired
	private ProductService productService;
	
	/**
	 * Method Application.main
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * Method Application.processOrder
	 * @param order
	 * @return
	 */
	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public Order processOrder(Order order) {
		log.info("Processing order: " + order);
		order.setProduct(productService.processOrder(order));
		log.info("Output order: " + order);
		return order;
	}

	/**
	 * Method Application.defaultSampler
	 * @return
	 */
	@Bean
	public AlwaysSampler defaultSampler() {
		return new AlwaysSampler();
	}

}
