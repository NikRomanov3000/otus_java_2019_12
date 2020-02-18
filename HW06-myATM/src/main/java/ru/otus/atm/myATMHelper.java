package ru.otus.atm;

import ru.otus.atm.ATMinterface.Banknote;

import java.util.HashMap;
import java.util.Map;

public class myATMHelper { //Map<getDenomination, NumberOfBanknote>
    public Map <Integer, Integer> clacNumberOfRequiredBanknote (int sum){
        if(sum%100!=0){
            System.out.println("Введите сумму кратную 100");
        }else{
            Map<Integer, Integer> denominationAndBanknotes = new HashMap<>();

            int rem5000 = sum%5000;
            denominationAndBanknotes.put(5000, (sum-rem5000)/5000);
            int rem1000 = rem5000%1000;
            denominationAndBanknotes.put(1000, (rem5000-rem1000)/1000);
            int rem500 = rem1000%500;
            denominationAndBanknotes.put(500,(rem1000-rem500));
            int rem100=rem500%100;
            denominationAndBanknotes.put(100,(rem500-rem100)/100);

            return denominationAndBanknotes;
        }
        return null;
    }
}
