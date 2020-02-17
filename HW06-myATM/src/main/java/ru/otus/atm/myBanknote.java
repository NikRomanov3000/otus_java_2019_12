package ru.otus.atm;

import ru.otus.atm.ATMinterface.Banknote;

import java.util.concurrent.ThreadLocalRandom;

public class myBanknote implements ru.otus.atm.ATMinterface.Banknote {
    private final int denomination;
    private final int serialNumber;

    public myBanknote(int denomination ) {
        this.serialNumber = ThreadLocalRandom.current().nextInt(0, 1000000 );
        this.denomination = denomination;
    }

    @Override
    public int getSerialNumber() {
        return serialNumber;
    }

    @Override
    public int getDenomination() {
        return denomination;
    }

}
