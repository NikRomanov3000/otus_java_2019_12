package ru.otus.atm;

import java.util.concurrent.ThreadLocalRandom;

public class Banknote {
    private final int denomination;
    private final int serialNumber;

    public Banknote(int denomination) {
        this.serialNumber = ThreadLocalRandom.current().nextInt(0, 1000000);
        this.denomination = denomination;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getDenomination() {
        return denomination;
    }

}
