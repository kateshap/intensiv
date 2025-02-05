package ru.aston.intensiv;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CreditAccount extends BankAccount implements TransactionFee, TransactionValidator {
    public static final BigDecimal TRANS_LIMIT = new BigDecimal("5000");

    public CreditAccount(String accountNumber, BigDecimal balance, String accountHolder) {
        super(accountNumber, balance, accountHolder);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        final BigDecimal creditLimit = new BigDecimal("-5000");
        if(validate(amount)) {
            if(balance.subtract(amount).compareTo(creditLimit) >= 0) {
                balance=balance.subtract(amount);
            }
            else {
                throw new IllegalStateException("Money withdrawal is not possible, " +
                        "because the withdrawal limit " + creditLimit + "has been exceeded");
            }
        }
        else {
            throw new IllegalStateException("Your amount " + amount + " is more, " +
                    " than transaction limit " + TRANS_LIMIT);
        }
    }

    @Override
    public BigDecimal applyFee(BigDecimal amount) {
        final BigDecimal fullPercent = new BigDecimal("100");
        final BigDecimal commission = new BigDecimal("10");
        BigDecimal amountPercentWithCommission = fullPercent.subtract(commission);
        BigDecimal fractionWithCommission = amountPercentWithCommission.divide(fullPercent, 2, RoundingMode.HALF_UP);
        BigDecimal amountWithApplyFee = amount.multiply(fractionWithCommission).setScale(2, RoundingMode.HALF_UP);
        return amountWithApplyFee;
    }

    @Override
    public boolean validate(BigDecimal amount) {
        return amount.compareTo(TRANS_LIMIT) <= 0;
    }
}
