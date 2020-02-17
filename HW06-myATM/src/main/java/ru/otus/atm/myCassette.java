package ru.otus.atm;

import ru.otus.atm.ATMinterface.Banknote;
import ru.otus.atm.ATMinterface.Cassette;

import java.util.ArrayList;
import java.util.List;

public class myCassette implements Cassette {
    int numberOfBanknotes;
    int denominationOfBanknote;
    List<Banknote> banknotesInCassette;

    public myCassette(int denominationOfBanknote) {
        numberOfBanknotes = 0;
        this.denominationOfBanknote=denominationOfBanknote;
        this.banknotesInCassette = new ArrayList<>();
    }

    @Override
    public void addBanknotes(int denominationOfBanknote) {
        banknotesInCassette.clear();
        for(int i=0; i<2500; i++){
            banknotesInCassette.add(new myBanknote((denominationOfBanknote)));
            numberOfBanknotes++;
        }
    }

    @Override
    public List<Banknote> getBanknotes(int requiredNumberOfBanknotes) {
        List<Banknote> requiredBanknotes = new ArrayList<>();
        if(requiredNumberOfBanknotes < numberOfBanknotes) {
            for (int i = 0; i < requiredNumberOfBanknotes; i++) {
                requiredBanknotes.add(banknotesInCassette.get(i));
            }
            return requiredBanknotes;
        } else
            return null;
    }

    @Override
    public int getNumberOfBanknotes() {
        return numberOfBanknotes;
    }
}
