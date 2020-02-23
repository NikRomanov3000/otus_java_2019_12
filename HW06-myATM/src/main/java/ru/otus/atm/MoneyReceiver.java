package ru.otus.atm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoneyReceiver {

    public void addBanknoteToATM(Banknote banknote, List<Cassette> cassettes) {
        MoneyChecker MoneyChecker = new MoneyChecker();
        Map<Integer, Cassette> banknoteMap = MoneyChecker.calcBanknoteByDenomination(cassettes);

        for (Map.Entry<Integer, Cassette> map : banknoteMap.entrySet()) {
            if (banknote.getDenomination() == map.getKey()) {
                map.getValue().addBanknote(banknote);
            }
        }
    }

    public List<Banknote> getBanknotesFromATM(int requiredNumberOfBanknotes, List<Cassette> cassettes) {
        List<Banknote> banknotes = new ArrayList<>();
        ATMCalcHelper atmCalcHelper = new ATMCalcHelper();
        Denomination denominations = new Denomination();
        Map<Integer, Integer> calcMap = atmCalcHelper.calcNumberOfRequiredBanknote(requiredNumberOfBanknotes, denominations);
        for (Cassette cassette : cassettes) {
            for (Map.Entry<Integer, Integer> map : calcMap.entrySet()) {
                if(cassette.getDenomination()==map.getKey()){
                    for(int i=0; i<map.getValue(); i++){
                       banknotes.add(cassette.getBanknote());
                    }
                }
            }
        }

        return banknotes;
    }
}
