package myATM;

import myATM.ATMinterface.ATM_interface;

import java.util.ArrayList;
import java.util.List;


public class MyFirstATM implements ATM_interface {
    private List<Cassette> cassettes = new ArrayList<>();
    private MoneyReceiver moneyReceiver;

    public MyFirstATM() {
        moneyReceiver = new MoneyReceiver();
        refreshATMCassettes();
    }

    public void refreshATMCassettes() {
        List<Integer> denominationsList = Denominations.getListOfDenominations();
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

    @Override
    public String toString() {
        return "My First ATM";
    }
}
