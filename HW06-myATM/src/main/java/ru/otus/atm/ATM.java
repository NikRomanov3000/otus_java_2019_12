package ru.otus.atm;

import java.util.List;

public class ATM {
    private static final int SERIAL_NUMBER = (int) (Math.random() * 1000000);
    Denomination denominations;
    List<Cassette> cassettes;
    MoneyChecker moneyChecker;
    MoneyReceiver moneyReceiver;

    public ATM(Denomination denominations) {
        List<Integer> denominationsList = denominations.getDenominationsList();
        for (Integer denomination : denominationsList) {
            cassettes.add(new Cassette(denomination));
        }
        moneyChecker = new MoneyChecker();
        moneyReceiver = new MoneyReceiver();
    }

    public int getATMBalance() {
        return moneyChecker.checkATMBalance(cassettes);
    }

    public void addBanknoteToAtm(Banknote banknote) {
        moneyReceiver.addBanknoteToATM(banknote, cassettes);
    }

    public List<Banknote> getBanknotesFromATM(int requiredSum) {
        return moneyReceiver.getBanknotesFromATM(requiredSum, denominations, cassettes);
    }

}
