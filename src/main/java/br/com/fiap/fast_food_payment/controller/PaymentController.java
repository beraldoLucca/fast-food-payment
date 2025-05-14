package br.com.fiap.fast_food_payment.controller;

import br.com.fiap.fast_food_payment.service.IPaymentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final IPaymentService paymentService;

    public PaymentController(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("{event}/order/{id}")
    @Operation(summary = "Webhook para confirmação/recusa de pagamento")
    public Boolean validatePayment(@PathVariable String event, @PathVariable Integer id) {
        return paymentService.validatePayment(event, id);
    }
}
