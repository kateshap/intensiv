package ru.aston.intensiv;

import java.math.BigDecimal;

public abstract class BankAccount {
    protected final String accountNumber;
    protected BigDecimal balance;
    protected final String accountHolder;

    public BankAccount(String accountNumber, BigDecimal balance, String accountHolder) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountHolder = accountHolder;
    }

    public abstract void withdraw(BigDecimal amount);

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    @Override
    public String toString() {
        return "BankAccount " +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", accountHolder='" + accountHolder + "'";
    }
}
