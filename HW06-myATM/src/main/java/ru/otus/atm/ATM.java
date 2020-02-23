package ru.otus.atm;

import java.util.ArrayList;
import java.util.List;

public class ATM {
    private static final int SERIAL_NUMBER = (int) (Math.random() * 1000000);
    private Denomination denominations;
    private List<Cassette> cassettes = new ArrayList<>();
    private MoneyChecker moneyChecker;
    private MoneyReceiver moneyReceiver;

    public ATM() {
        Denomination denominations = new Denomination();
        List<Integer> denominationsList = denominations.getDenominationsList();
        for (Integer denomination : denominationsList) {
            cassettes.add(new Cassette(denomination));
        }
        moneyChecker = new MoneyChecker();
    }

    public int getATMBalance() {
        MoneyChecker myMoneyChecker = new MoneyChecker();
        return myMoneyChecker.checkATMBalance(cassettes);
    }

    public void addBanknoteToAtm(Banknote banknote) {
        moneyReceiver.addBanknoteToATM(banknote, cassettes);
    }

    public List<Banknote> getBanknotesFromATM(int requiredSum) {
        return moneyReceiver.getBanknotesFromATM(requiredSum, cassettes);
    }
}
