package ru.otus.atm;

import ru.otus.atm.ATMinterface.MoneyReceiver_interface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.otus.atm.ATMCalcHelper.calcNumberOfRequiredBanknote;

public class MoneyReceiver implements MoneyReceiver_interface {
    @Override
    public void addBanknoteToATM(Banknote banknote, List<Cassette> cassettes) {
        MoneyChecker MoneyChecker = new MoneyChecker();
        Map<Integer, Cassette> banknoteMap = MoneyChecker.calcBanknoteByDenomination(cassettes);

        for (Map.Entry<Integer, Cassette> map : banknoteMap.entrySet()) {
            if (banknote.getDenomination() == map.getKey()) {
                map.getValue().addBanknote(banknote);
            }
        }
    }

    @Override
    public List<Banknote> getBanknotesFromATM(int requiredNumberOfBanknotes, List<Cassette> cassettes) {
        List<Banknote> banknotes = new ArrayList<>();
        Map<Integer, Integer> calcMap = calcNumberOfRequiredBanknote(requiredNumberOfBanknotes);
        for (Cassette cassette : cassettes) {
            for (Map.Entry<Integer, Integer> map : calcMap.entrySet()) {
                if (cassette.getDenomination() == map.getKey()) {
                    for (int i = 0; i < map.getValue(); i++) {
                        banknotes.add(cassette.getBanknote());
                    }
                }
            }
        }
        return banknotes;
    }
}
