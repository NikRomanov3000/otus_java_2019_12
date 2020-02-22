package ru.otus.atm;

import java.util.HashMap;
import java.util.Map;

public class ATMHelper {

    public static Map <Integer, Integer> calcNumberOfRequiredBanknote (int sum, Denomination denomination){

        if(sum%denomination.getDenomination4()!=0){
            throw new RuntimeException("Введите сумму кратную "+ denomination.getDenomination4().toString());
        }else{
            Map<Integer, Integer> denominationAndBanknotes = new HashMap<>();

            int rem1 = sum%(denomination.getDenomination1());
            denominationAndBanknotes.put(denomination.getDenomination1(), (sum-rem1)/denomination.getDenomination1());
            int rem2 = rem1%denomination.getDenomination2();
            denominationAndBanknotes.put(denomination.getDenomination2(), (rem1-rem2)/denomination.getDenomination2());
            int rem3 = rem2%denomination.getDenomination3();
            denominationAndBanknotes.put(denomination.getDenomination3(),(rem2-rem3)/denomination.getDenomination3());
            int rem4=rem3%denomination.getDenomination4();
            denominationAndBanknotes.put(denomination.getDenomination4(),(rem4-rem3)/denomination.getDenomination4());

            return denominationAndBanknotes;
        }
    }
}
