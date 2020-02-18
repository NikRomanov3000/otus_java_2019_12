package ru.otus.atm;

import ru.otus.atm.ATMinterface.MoneyChecker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class myMoneyChecker implements MoneyChecker {
    private int ATM_Balance;

    public myMoneyChecker() {
        this.ATM_Balance = 0;
    }

    public Map<Integer , myCassette > calcBanknoteByDenomination (List<myCassette> cassettes){
        Map<Integer , myCassette > denominationAndCountBanknote = new HashMap<>();
        for (myCassette cassette : cassettes) {
            denominationAndCountBanknote.put(cassette.getDenomination(), cassette);
        }
        return denominationAndCountBanknote;
    }

    public int checkATMBalance(List<myCassette> cassettes)  {
        Map<Integer, myCassette> denominationAndCountBanknote = this.calcBanknoteByDenomination(cassettes);
        for (Map.Entry<Integer, myCassette> map : denominationAndCountBanknote.entrySet()){
            ATM_Balance += map.getKey() * map.getValue().getNumberOfBanknotes();
        }

        return ATM_Balance;
    }
}
