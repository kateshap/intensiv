package ru.aston.intensiv;

import java.math.BigDecimal;

public class DebitAccount extends BankAccount implements TransactionValidator{
    public static final BigDecimal TRANS_LIMIT = new BigDecimal("10000");

    public DebitAccount(String accountNumber, BigDecimal balance, String accountHolder) {
        super(accountNumber, balance, accountHolder);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if(validate(amount)) {
            if(balance.compareTo(amount) >= 0) {
                balance = balance.subtract(amount);
            }
            else {
                throw new IllegalStateException("Money withdrawal is not possible, " +
                        "because the amount " + amount + " is more than the balance " + balance);
            }
        }
        else {
            throw new IllegalStateException("Your amount " + amount + " is more, than limit " + TRANS_LIMIT);
        }
    }

    @Override
    public boolean validate(BigDecimal amount) {
        return amount.compareTo(TRANS_LIMIT) <= 0;
    }
}
