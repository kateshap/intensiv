package ru.aston.intensiv;

import net.datafaker.Faker;
import java.math.BigDecimal;

public final class DataGenerator {

    private DataGenerator(){}

    public static CreditAccount getCreditAccount(BigDecimal bigDecimal) {
        Faker faker = new Faker();
        return new CreditAccount(faker.finance().creditCard(),bigDecimal,faker.name().fullName());
    }

    public static DebitAccount getDebitAccount(BigDecimal bigDecimal) {
        Faker faker = new Faker();
        return new DebitAccount(faker.finance().creditCard(),bigDecimal,faker.name().fullName());
    }

    public static SavingsAccount getSavingsAccount(BigDecimal bigDecimal) {
        Faker faker = new Faker();
        return new SavingsAccount(faker.finance().creditCard(),bigDecimal,faker.name().fullName());
    }
}

