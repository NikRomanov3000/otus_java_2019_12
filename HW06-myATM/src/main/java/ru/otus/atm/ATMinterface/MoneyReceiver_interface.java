package ru.otus.atm.ATMinterface;

import ru.otus.atm.Banknote;
import ru.otus.atm.Cassette;

import java.util.List;

public interface MoneyReceiver_interface {
    void addBanknoteToATM(Banknote banknote, List<Cassette> cassettes);
    List<Banknote> getBanknotesFromATM(int requiredNumberOfBanknotes, List<Cassette> cassettes);
}
