package ru.aston.intensiv;

import java.math.BigDecimal;

public interface TransactionValidator {
    boolean validate(BigDecimal amount);
}
