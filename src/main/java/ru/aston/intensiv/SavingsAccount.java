package ru.aston.intensiv;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SavingsAccount extends DebitAccount implements InterestBearing {

    public SavingsAccount(String accountNumber, BigDecimal balance, String accountHolder) {
        super(accountNumber, balance, accountHolder);
    }

    @Override
    public BigDecimal applyInterest() {
        final BigDecimal fullPercent = new BigDecimal("100");
        final BigDecimal savingPercent = new BigDecimal("15");
        final BigDecimal monthsCount = new BigDecimal("12");

        final BigDecimal savingPercentPerMonth = savingPercent.divide(monthsCount, 2, RoundingMode.HALF_UP);

        final BigDecimal amountPercentWithSavingPerMonth = fullPercent.add(savingPercentPerMonth);
        final BigDecimal fractionAmountWithSavingPerMonth = amountPercentWithSavingPerMonth.divide(fullPercent, 4, RoundingMode.HALF_UP);

        balance = balance.multiply(fractionAmountWithSavingPerMonth).setScale(2, RoundingMode.HALF_UP);
        return balance;
    }
}
