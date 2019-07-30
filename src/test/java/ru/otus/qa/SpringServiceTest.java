package ru.otus.qa;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.qa.service.CalculatorService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CalculatorService.class})

public class SpringServiceTest {

    @Autowired
    private CalculatorService service;

    @Test
    public void testServicePlus() {
        assertEquals(8, service.plus(5, 3));
    }

    @Test
    public void testServiceDivide() {
        assertEquals(1, service.divide(5, 3));
    }

    @Test
    public void testServiceDivideZero() {
        try {
            assertEquals(0, service.divide(5, 0));
        } catch (ArithmeticException e) {

        }
    }

    @Test
    public void testServiceMultiply() {
        assertEquals(15, service.multiply(5, 3));
    }

    @Test
    public void testServiceMinus() {
        assertEquals(2, service.minus(5, 3));
    }


}
