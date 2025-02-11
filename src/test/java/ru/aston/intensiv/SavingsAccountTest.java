package ru.aston.intensiv;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class SavingsAccountTest {

    public final static BigDecimal INIT_BALANCE = new BigDecimal("1000");

    SavingsAccount savingsAccount;
    BigDecimal withdrawalAmount;

    @BeforeEach
    void prepareData() {
        savingsAccount = DataGenerator.getSavingsAccount(INIT_BALANCE);
    }

    @Test
    void When_ApplyInterestBalance_Then_Success() {
        final BigDecimal expectedBalanceAfterApplyInterest = new BigDecimal("1012.50");

        BigDecimal actualBalanceAfterApplyInterest = savingsAccount.applyInterest();

        assertEquals(expectedBalanceAfterApplyInterest, actualBalanceAfterApplyInterest);
    }
}