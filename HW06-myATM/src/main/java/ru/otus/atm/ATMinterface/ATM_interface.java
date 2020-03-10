package ru.otus.atm.ATMinterface;

import ru.otus.atm.Banknote;

import java.util.List;

public interface ATM_interface {
    int getATMBalance();

    void addBanknoteToAtm(Banknote banknote);

    List<Banknote> getBanknotesFromATM(int requiredSum);


}
