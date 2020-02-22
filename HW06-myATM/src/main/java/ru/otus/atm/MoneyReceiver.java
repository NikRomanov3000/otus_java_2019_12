package ru.otus.atm;

import java.util.List;
import java.util.Map;

public class MoneyReceiver {

    public void addBanknoteToATM(Banknote banknote, List<Cassette> cassettes) {
        MoneyChecker MoneyChecker = new MoneyChecker();
        Map<Integer, Cassette> banknoteMap = MoneyChecker.calcBanknoteByDenomination(cassettes);

        for (Map.Entry<Integer, Cassette> map : banknoteMap.entrySet()) {
            if (banknote.getDenomination() == map.getKey()) {
                map.getValue().addBanknote(banknote);
            } else throw new RuntimeException("Внутренняя ошибка банкомата, обраитесь к сотруднику банка");
        }
    }

    public List<Banknote> getBanknotesFromATM() {

        return null;
    }
}
