package br.com.fiap.fast_food_payment.service;


import br.com.fiap.fast_food_payment.model.Payment;
import br.com.fiap.fast_food_payment.repository.IPaymentRepository;
import br.com.fiap.fast_food_payment.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PaymentServiceImplTest {

    @Mock
    private IPaymentRepository paymentRepository;

    private IPaymentService paymentService;

    @BeforeEach()
    void setup(){
        MockitoAnnotations.openMocks(this);
        paymentService = new PaymentServiceImpl(paymentRepository);
    }

    @Test
    void shouldValidatePaymentApproved() {
        when(paymentRepository.save(any(Payment.class))).thenReturn(new Payment());

        var result = paymentService.validatePayment("payment_approved", 1);

        assertTrue(result);
        verify(paymentRepository).save(any(Payment.class));
    }

    @Test
    void shouldRejectPaymentWhenEventIsNotApproved() {
        when(paymentRepository.save(any(Payment.class))).thenReturn(new Payment());

        var result = paymentService.validatePayment("payment_refused", 1);

        assertFalse(result);
        verify(paymentRepository).save(any(Payment.class));
    }

    @Test
    void shouldSaveTransactionWithCorrectOrderId() {
        var orderId = 42;
        when(paymentRepository.save(any(Payment.class))).thenReturn(new Payment());

        paymentService.saveTransaction(orderId);

        verify(paymentRepository).save(argThat(payment -> {
            try {
                Field campo = payment.getClass().getDeclaredField("orderId");
                campo.setAccessible(true);
                return campo.get(payment).equals(orderId);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return false;
            }
        }));

    }

    @Test
    void shouldSaveTransactionWithCurrentDate() throws NoSuchFieldException, IllegalAccessException {
        ArgumentCaptor<Payment> paymentCaptor = ArgumentCaptor.forClass(Payment.class);
        LocalDateTime before = LocalDateTime.now().minusDays(1);

        paymentService.saveTransaction(1);

        LocalDateTime after = LocalDateTime.now().plusDays(1);
        verify(paymentRepository).save(paymentCaptor.capture());

        Payment savedPayment = paymentCaptor.getValue();
        Field field = savedPayment.getClass().getDeclaredField("paymentDate");
        field.setAccessible(true);
        LocalDateTime paymentDate = (LocalDateTime) field.get(savedPayment);

        assertTrue(
                !paymentDate.isBefore(before) &&
                        !paymentDate.isAfter(after)
        );
    }
}
