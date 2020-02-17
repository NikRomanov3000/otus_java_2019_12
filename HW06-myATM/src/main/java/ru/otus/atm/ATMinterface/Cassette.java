package ru.otus.atm.ATMinterface;

import java.util.List;

public interface Cassette {
    void refreshCassette(int denomination);
    void addBanknote();
    List<Banknote> getBanknotes( int requiredNumber) throws Exception;
    int getNumberOfBanknotes();
}
