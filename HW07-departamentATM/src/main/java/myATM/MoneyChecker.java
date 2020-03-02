package myATM;

import myATM.ATMinterface.MoneyChecker_interface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoneyChecker implements MoneyChecker_interface {
    private int ATM_Balance;

    public MoneyChecker() {
        this.ATM_Balance = 0;
    }

    public Map<Integer, Cassette> calcBanknoteByDenomination(List<Cassette> cassettes) {
        Map<Integer, Cassette> denominationAndCountBanknote = new HashMap<>();
        for (Cassette cassette : cassettes) {
            denominationAndCountBanknote.put(cassette.getDenomination(), cassette);
        }
        return denominationAndCountBanknote;
    }

    @Override
    public int checkATMBalance(List<Cassette> cassettes) {
        Map<Integer, Cassette> denominationAndCountBanknote = this.calcBanknoteByDenomination(cassettes);
        for (Map.Entry<Integer, Cassette> map : denominationAndCountBanknote.entrySet()) {
            ATM_Balance += map.getKey() * map.getValue().getNumberOfBanknotes();
        }

        return ATM_Balance;
    }
}
