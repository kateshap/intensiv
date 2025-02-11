package ru.aston.intensiv;

import java.math.BigDecimal;

public interface TransactionFee {
    BigDecimal applyFee(BigDecimal amount);

}
