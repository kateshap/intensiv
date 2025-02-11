package ru.aston.intensiv;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class CreditAccountTest {

    public final static BigDecimal INIT_BALANCE = new BigDecimal("1000");
    public final static BigDecimal TRANS_LIMIT = new BigDecimal("5000");
    public final static BigDecimal CREDIT_LIMIT = new BigDecimal("-5000");

    CreditAccount creditAccount;
    BigDecimal withdrawalAmount;

    @BeforeEach
    void prepareData() {
        creditAccount = DataGenerator.getCreditAccount(INIT_BALANCE);
    }

    @Test
    void When_TransactionMoreLimit_Then_ValidationFail() {
        final BigDecimal amountMoreLimit = new BigDecimal("6000");

        boolean isTransactionValidate = creditAccount.validate(amountMoreLimit);

        assertFalse(isTransactionValidate);
    }

    @Test
    void When_TransactionLessLimit_Then_ValidationSuccess() {
        final BigDecimal amountLessLimit = new BigDecimal("4000");

        boolean isTransactionValidate = creditAccount.validate(amountLessLimit);

        assertTrue(isTransactionValidate);
    }

    @Test
    void When_TransactionEqualLimit_Then_ValidationSuccess() {
        boolean isTransactionValidate = creditAccount.validate(TRANS_LIMIT);

        assertTrue(isTransactionValidate);
    }

    @Test
    void When_WithdrawMoreTransactionLimit_Then_ThrowException() {
        withdrawalAmount = new BigDecimal("9000");

        Assertions.assertThrows(IllegalStateException.class, () -> {
            creditAccount.withdraw(withdrawalAmount);
        }, "Your amount " + withdrawalAmount + " is more, than transaction limit " + TRANS_LIMIT);
    }

    @Test
    void When_BalanceAfterWithdrawLessCreditLimit_Then_ThrowException() {
        withdrawalAmount = new BigDecimal("7000");

        Assertions.assertThrows(IllegalStateException.class, () -> {
            creditAccount.withdraw(withdrawalAmount);
        }, "Money withdrawal is not possible, " +
                "because the withdrawal limit " + CREDIT_LIMIT + " has been exceeded");
    }

    @Test
    void When_BalanceAfterWithdrawEqualCreditLimit_Then_Success() {
        creditAccount = DataGenerator.getCreditAccount(new BigDecimal("-1000"));
        withdrawalAmount = new BigDecimal("4000");

        creditAccount.withdraw(withdrawalAmount);

        assertEquals(CREDIT_LIMIT, creditAccount.balance);
    }

    @Test
    void When_BalanceAfterWithdrawMoreCreditLimit_Then_Success() {
        final BigDecimal expectedBalanceAfterWithdraw = new BigDecimal("-4000");
        withdrawalAmount = new BigDecimal("5000");

        creditAccount.withdraw(withdrawalAmount);

        assertEquals(expectedBalanceAfterWithdraw, creditAccount.balance);
    }

    @Test
    void When_ApplyFee_Then_Success() {
        final BigDecimal expectedBalanceAfterApplyFee = new BigDecimal("900.00");
        final BigDecimal initAmount = new BigDecimal("1000");

        BigDecimal actualAmountAfterApplyFee = creditAccount.applyFee(initAmount);

        assertEquals(expectedBalanceAfterApplyFee,actualAmountAfterApplyFee);
    }
}