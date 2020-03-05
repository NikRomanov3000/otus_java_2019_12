package ru.otus.atm.ATMinterface;

import ru.otus.atm.Banknote;
import ru.otus.atm.Cassette;
import ru.otus.atm.Denomination;
import ru.otus.atm.MoneyChecker;

import java.util.List;

public interface ATM_interface {
    int getATMBalance();

    void addBanknoteToAtm(Banknote banknote);

    List<Banknote> getBanknotesFromATM(int requiredSum);


}
