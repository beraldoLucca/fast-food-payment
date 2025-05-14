package br.com.fiap.fast_food_payment.service;

import java.util.Map;

public interface IPaymentService {

    Boolean validatePayment (String event, Integer id);

    void saveTransaction (Integer id);
}
