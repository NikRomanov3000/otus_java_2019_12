package ru.otus.atm.ATMinterface;

import java.util.List;

public interface MoneyReceiver {
    Banknote addBanknoteToATM(Banknote banknote);
    List<Banknote> getBanknotesFromATM();
}
