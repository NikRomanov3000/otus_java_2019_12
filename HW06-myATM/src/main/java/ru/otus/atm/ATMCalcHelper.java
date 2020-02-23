package ru.otus.atm;

import java.util.HashMap;
import java.util.Map;

public class ATMCalcHelper {

    public Map<Integer, Integer> calcNumberOfRequiredBanknote(int sum, Denomination denominations) {

        if (sum % denominations.getDenomination4()!= 0) {
            throw new RuntimeException("Введите сумму кратную " + denominations.getDenomination4().toString());
        } else {
            Map<Integer, Integer> denominationAndBanknotes = new HashMap<>();

            int rem1 = sum % (denominations.getDenomination1());
            denominationAndBanknotes.put(denominations.getDenomination1(), (sum - rem1) / denominations.getDenomination1());
            int rem2 = rem1 % denominations.getDenomination2();
            denominationAndBanknotes.put(denominations.getDenomination2(), (rem1 - rem2) / denominations.getDenomination2());
            int rem3 = rem2 % denominations.getDenomination3();
            denominationAndBanknotes.put(denominations.getDenomination3(), (rem2 - rem3) / denominations.getDenomination3());
            int rem4 = rem3 % denominations.getDenomination4();
            denominationAndBanknotes.put(denominations.getDenomination4(), (rem3 - rem4) / denominations.getDenomination4());

            return denominationAndBanknotes;
        }
    }
}
