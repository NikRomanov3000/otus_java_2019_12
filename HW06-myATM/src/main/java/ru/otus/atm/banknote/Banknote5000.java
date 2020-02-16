package ru.otus.atm.banknote;

import ru.otus.atm.ATMinterface.Banknote;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Banknote5000 implements Banknote {
    private final int denomination=5000 ;
    private final int serialNumber;

    public Banknote5000() {
        this.serialNumber = ThreadLocalRandom.current().nextInt(0, 1000000 );
    }

    @Override
    public int getSerialNumber() {
        return serialNumber;
    }

    @Override
    public int getDenomination() {
        return denomination;
    }

    @Override
    public Object getBanknote() {
        return new  Banknote5000();
    }
}
