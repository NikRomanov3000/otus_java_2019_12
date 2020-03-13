package com.departamentATM.myATM;

import com.departamentATM.myATM.ATMinterface.MoneyReceiverInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MoneyReceiver implements MoneyReceiverInterface {
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
        Map<Integer, Integer> calcMap = ATMCalcHelper.calcNumberOfRequiredBanknote(requiredNumberOfBanknotes);
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
