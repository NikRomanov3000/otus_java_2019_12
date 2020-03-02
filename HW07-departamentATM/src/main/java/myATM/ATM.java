package myATM;

import myATM.ATMinterface.ATM_interface;

import java.util.ArrayList;
import java.util.List;

public class ATM implements ATM_interface {
    private static final int SERIAL_NUMBER = (int) (Math.random() * 1000000);
    private Denomination denominations;
    private List<Cassette> cassettes = new ArrayList<>();
    private MoneyReceiver moneyReceiver;

    public ATM() {
        moneyReceiver = new MoneyReceiver();
        refreshATMCassettes();
    }

    public void refreshATMCassettes() {
        denominations = new Denomination();
        List<Integer> denominationsList = denominations.getDenominationsList();
        for (Integer denomination : denominationsList) {
            cassettes.add(new Cassette(denomination));
        }

    }

    @Override
    public int getATMBalance() {
        MoneyChecker myMoneyChecker = new MoneyChecker();
        return myMoneyChecker.checkATMBalance(cassettes);
    }

    @Override
    public void addBanknoteToAtm(Banknote banknote) {
        moneyReceiver.addBanknoteToATM(banknote, cassettes);
    }

    @Override
    public List<Banknote> getBanknotesFromATM(int requiredSum) {
        return moneyReceiver.getBanknotesFromATM(requiredSum, cassettes);
    }
}
