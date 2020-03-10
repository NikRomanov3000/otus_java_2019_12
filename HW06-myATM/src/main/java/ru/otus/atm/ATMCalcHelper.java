package ru.otus.atm;

import java.util.HashMap;
import java.util.Map;

public class ATMCalcHelper {

    public static Map<Integer, Integer> calcNumberOfRequiredBanknote(int sum) {


        if (sum % Denominations.denomination100.getValueOfDenomination()!= 0) {
            throw new RuntimeException("Введите сумму кратную " + Denominations.denomination100.getValueOfDenomination());
        } else {
            Map<Integer, Integer> denominationAndBanknotes = new HashMap<>();

            int rem1 = sum % (Denominations.denomination5000.getValueOfDenomination());
            denominationAndBanknotes.put(Denominations.denomination5000.getValueOfDenomination(), (sum - rem1) / Denominations.denomination5000.getValueOfDenomination());
            int rem2 = rem1 % Denominations.denomination1000.getValueOfDenomination();
            denominationAndBanknotes.put(Denominations.denomination1000.getValueOfDenomination(), (rem1 - rem2) / Denominations.denomination1000.getValueOfDenomination());
            int rem3 = rem2 % Denominations.denomination500.getValueOfDenomination();
            denominationAndBanknotes.put(Denominations.denomination500.getValueOfDenomination(), (rem2 - rem3) / Denominations.denomination500.getValueOfDenomination());
            int rem4 = rem3 % Denominations.denomination100.getValueOfDenomination();
            denominationAndBanknotes.put(Denominations.denomination100.getValueOfDenomination(), (rem3 - rem4) / Denominations.denomination100.getValueOfDenomination());

            return denominationAndBanknotes;
        }
    }
}
