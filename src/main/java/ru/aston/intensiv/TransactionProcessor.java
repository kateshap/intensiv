package ru.aston.intensiv;

import java.math.BigDecimal;
import java.util.List;

public class TransactionProcessor {
    void processTransaction(List<BankAccount> accounts, BigDecimal amount){
        for (BankAccount bankAccount: accounts){
            bankAccount.withdraw(amount);
        }
    }
}
