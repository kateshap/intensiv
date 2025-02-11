package ru.aston.intensiv;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class DebitAccountTest {

    public final static BigDecimal INIT_BALANCE = new BigDecimal("1000");
    public final static BigDecimal TRANS_LIMIT = new BigDecimal("10000");

    DebitAccount debitAccount;
    BigDecimal withdrawalAmount;

    @BeforeEach
    void prepareData() {
        debitAccount = DataGenerator.getDebitAccount(INIT_BALANCE);
    }

    @Test
    void When_TransactionMoreLimit_Then_ValidationFail() {
        final BigDecimal amountMoreLimit = new BigDecimal("11000");

        boolean isTransactionValidate = debitAccount.validate(amountMoreLimit);

        assertFalse(isTransactionValidate);
    }

    @Test
    void When_TransactionLessLimit_Then_ValidationSuccess() {
        final BigDecimal amountLessLimit = new BigDecimal("7000");

        boolean isTransactionValidate = debitAccount.validate(amountLessLimit);

        assertTrue(isTransactionValidate);
    }

    @Test
    void When_TransactionEqualLimit_Then_ValidationSuccess() {
        boolean isTransactionValidate = debitAccount.validate(DebitAccount.TRANS_LIMIT);

        assertTrue(isTransactionValidate);
    }

    @Test
    void When_WithdrawMoreTransactionLimit_Then_ThrowException() {
        withdrawalAmount = new BigDecimal("12000");

        Assertions.assertThrows(IllegalStateException.class, () -> {
            debitAccount.withdraw(withdrawalAmount);
        }, "Your amount " + withdrawalAmount + " is more, than transaction limit " + TRANS_LIMIT);
    }

    @Test
    void When_BalanceAfterWithdrawIsNegative_Then_ThrowException() {
        withdrawalAmount = new BigDecimal("7000");

        Assertions.assertThrows(IllegalStateException.class, () -> {
            debitAccount.withdraw(withdrawalAmount);
        }, "Money withdrawal is not possible, " +
                " because the amount " + withdrawalAmount + " is more than the balance " + debitAccount.balance);
    }

    @Test
    void When_BalanceAfterWithdrawIsZero_Then_Success() {
        final BigDecimal expectedBalanceAfterWithdraw = new BigDecimal("0");
        withdrawalAmount = new BigDecimal("1000");

        debitAccount.withdraw(withdrawalAmount);

        assertEquals(expectedBalanceAfterWithdraw, debitAccount.balance);
    }

    @Test
    void When_BalanceAfterWithdrawMoreZero_Then_Success() {
        final BigDecimal expectedBalanceAfterWithdraw = new BigDecimal("500");
        withdrawalAmount = new BigDecimal("500");

        debitAccount.withdraw(withdrawalAmount);

        assertEquals(expectedBalanceAfterWithdraw, debitAccount.balance);
    }
}