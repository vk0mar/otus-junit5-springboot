package ru.otus.qa.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.qa.service.CalculatorService;

@RestController
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/calc")
    public Integer calc(@RequestParam("operation") String operation, @RequestParam("a") Integer a, @RequestParam("b") Integer b) {

        switch (operation) {
            case "plus":
                return calculatorService.plus(a, b);
            case "minus":
                return calculatorService.minus(a, b);
            case "multiply":
                return calculatorService.multiply(a, b);
            case "divide":
                return calculatorService.divide(a, b);
            default:
                throw new IllegalArgumentException("Unknown operation: " + operation);
        }
    }
}
