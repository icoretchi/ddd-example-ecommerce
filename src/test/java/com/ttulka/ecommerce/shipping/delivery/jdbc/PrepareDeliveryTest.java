package com.ttulka.ecommerce.shipping.delivery.jdbc;

import java.util.List;

import com.ttulka.ecommerce.common.events.EventPublisher;
import com.ttulka.ecommerce.shipping.FindDeliveries;
import com.ttulka.ecommerce.shipping.PrepareDelivery;
import com.ttulka.ecommerce.shipping.delivery.Address;
import com.ttulka.ecommerce.shipping.delivery.Delivery;
import com.ttulka.ecommerce.shipping.delivery.DeliveryItem;
import com.ttulka.ecommerce.shipping.delivery.OrderId;
import com.ttulka.ecommerce.shipping.delivery.Person;
import com.ttulka.ecommerce.shipping.delivery.Place;
import com.ttulka.ecommerce.shipping.delivery.ProductCode;
import com.ttulka.ecommerce.shipping.delivery.Quantity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
@ContextConfiguration(classes = DeliveryJdbcConfig.class)
class PrepareDeliveryTest {

    @Autowired
    private FindDeliveries findDeliveries;
    @Autowired
    private PrepareDelivery prepareDelivery;

    @MockBean
    private EventPublisher eventPublisher;

    @Test
    void delivery_for_order_is_prepared() {
        prepareDelivery.prepare(
                new OrderId(123),
                List.of(new DeliveryItem(new ProductCode("test"), new Quantity(1))),
                new Address(new Person("test"), new Place("test")));

        Delivery delivery = findDeliveries.byOrderId(new OrderId(123));

        assertAll(
                () -> assertThat(delivery.orderId()).isEqualTo(new OrderId(123)),
                () -> assertThat(delivery.items()).containsExactly(new DeliveryItem(new ProductCode("test"), new Quantity(1))),
                () -> assertThat(delivery.address()).isEqualTo(new Address(new Person("test"), new Place("test")))
        );
    }
}
