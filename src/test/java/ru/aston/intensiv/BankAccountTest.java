package ru.aston.intensiv;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class BankAccountTest {

    public final static BigDecimal INIT_BALANCE = new BigDecimal("1000");
    public final static BigDecimal DEPOSIT_AMOUNT = new BigDecimal("100");
    public final static BigDecimal EXPECTED_BALANCE = new BigDecimal("1100");

    @Test
    void When_DepositCreditAccount_Then_True() {
        BankAccount creditAccount = DataGenerator.getCreditAccount(INIT_BALANCE);

        creditAccount.deposit(DEPOSIT_AMOUNT);

        assertEquals(EXPECTED_BALANCE,creditAccount.balance);
    }

    @Test
    void When_DepositDebitAccount_Then_True() {
        BankAccount debitAccount = DataGenerator.getDebitAccount(INIT_BALANCE);

        debitAccount.deposit(DEPOSIT_AMOUNT);

        assertEquals(EXPECTED_BALANCE,debitAccount.balance);
    }

    @Test
    void When_DepositSavingsAccount_Then_True() {
        BankAccount savingsAccount = DataGenerator.getSavingsAccount(INIT_BALANCE);

        savingsAccount.deposit(DEPOSIT_AMOUNT);

        assertEquals(EXPECTED_BALANCE,savingsAccount.balance);
    }
}