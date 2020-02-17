package ru.otus.atm.ATMinterface;

import ru.otus.atm.myCassette;

import java.util.List;

public interface MoneyReceiver {
    void addBanknoteToATM(Banknote banknote, List<myCassette> cassettes);
    List<Banknote> getBanknotesFromATM();
}
