package ru.otus.atm;

import ru.otus.atm.ATMinterface.MoneyChecker;

import java.util.List;

public class myMoneyChecker implements MoneyChecker {
    int ATM_Balance;

    public myMoneyChecker() {
        this.ATM_Balance = 0;
    }

    public int checkATMBalance(List<myCassette> cassettes)  {

        for (myCassette cassette : cassettes) {
            ATM_Balance = cassette.getNumberOfBanknotes() * cassette.denominationOfBanknote;
        }
        return ATM_Balance;
    }
}
