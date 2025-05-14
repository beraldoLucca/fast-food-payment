package br.com.fiap.fast_food_payment.repository;

import br.com.fiap.fast_food_payment.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPaymentRepository extends MongoRepository<Payment, String> {
}
