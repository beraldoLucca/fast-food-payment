package br.com.fiap.fast_food_payment.controller;

import br.com.fiap.fast_food_payment.service.IPaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PaymentControllerTest {

    @Mock
    private IPaymentService paymentService;

    private PaymentController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new PaymentController(paymentService);
    }

    @Test
    void shouldReturnTrueWhenPaymentIsConfirmed(){

        when(paymentService.validatePayment(anyString(), anyInt())).thenReturn(true);
        Boolean result = controller.validatePayment("payment_approved", 1);

        assertTrue(result);
        verify(paymentService).validatePayment("payment_approved", 1);
    }

    @Test
    void shouldReturnFalseWhenPaymentIsRefused(){

        when(paymentService.validatePayment(anyString(), anyInt())).thenReturn(false);
        Boolean result = controller.validatePayment("payment_refused", 1);

        assertFalse(result);
        verify(paymentService).validatePayment("payment_refused", 1);
    }

}
