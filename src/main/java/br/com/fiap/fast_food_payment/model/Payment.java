package br.com.fiap.fast_food_payment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "payments")
public class Payment {

    @Id
    private String id;

    private Integer orderId;

    private final LocalDateTime paymentDate = LocalDateTime.now();

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
