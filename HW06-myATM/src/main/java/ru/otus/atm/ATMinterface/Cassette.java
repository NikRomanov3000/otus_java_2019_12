package ru.otus.atm.ATMinterface;

import java.util.List;

public interface Cassette {
    void refreshCassette(int denomination);
    void addBanknote(Banknote banknote);
    List<Banknote> getBanknotes( int requiredNumber) throws Exception;
    int getNumberOfBanknotes();
    int getDenomination();
}
