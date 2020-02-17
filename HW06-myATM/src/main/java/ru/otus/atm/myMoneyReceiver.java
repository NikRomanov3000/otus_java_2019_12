package ru.otus.atm;

import ru.otus.atm.ATMinterface.Banknote;
import ru.otus.atm.ATMinterface.MoneyReceiver;

import java.util.List;

public class myMoneyReceiver implements MoneyReceiver {

    @Override
    public Banknote addBanknoteToATM(Banknote banknote) {
        int denomination = banknote.getDenomination();
        //find cassette by denomination,
        return null;
    }

    @Override
    public List<Banknote> getBanknotesFromATM() {
        return null;
    }
}
