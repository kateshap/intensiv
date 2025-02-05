package ru.aston.intensiv;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;

class TransactionProcessorTest {

    @Test
    void testProcessTransaction() {
        List<BankAccount> accounts = List.of(
                DataGenerator.getCreditAccount(new BigDecimal("4000")),
                DataGenerator.getCreditAccount(new BigDecimal("10000")),
                DataGenerator.getCreditAccount(new BigDecimal("2000"))
        );
        TransactionProcessor transactionProcessor = new TransactionProcessor();
        transactionProcessor.processTransaction(accounts, new BigDecimal("1000"));
        BigDecimal[] balancesArr = new BigDecimal[accounts.size()];

        for (int i = 0; i < accounts.size(); i++) {
            balancesArr[i] = accounts.get(i).balance;
        }
        BigDecimal[] expectedArray = new BigDecimal[]{new BigDecimal("3000"), new BigDecimal("9000"), new BigDecimal("1000")};

        assertArrayEquals(expectedArray, balancesArr);
    }
}