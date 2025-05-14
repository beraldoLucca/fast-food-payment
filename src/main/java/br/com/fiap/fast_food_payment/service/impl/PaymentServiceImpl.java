package br.com.fiap.fast_food_payment.service.impl;

import br.com.fiap.fast_food_payment.model.Payment;
import br.com.fiap.fast_food_payment.repository.IPaymentRepository;
import br.com.fiap.fast_food_payment.service.IPaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements IPaymentService {

    private final IPaymentRepository paymentRepository;

    public PaymentServiceImpl(IPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Boolean validatePayment(String event, Integer orderId) {
        saveTransaction(orderId);

        return "payment_approved".equals(event);
    }

    @Override
    public void saveTransaction(Integer orderId) {
        Payment payment = new Payment();
        payment.setOrderId(orderId);

        paymentRepository.save(payment);
    }
}
