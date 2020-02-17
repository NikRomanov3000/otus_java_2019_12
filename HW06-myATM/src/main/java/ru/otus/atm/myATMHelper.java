package ru.otus.atm;

import ru.otus.atm.ATMinterface.Banknote;
import java.util.Map;

public class myATMHelper {//Map<denomination, NumberOfBanknotes>
    public Map<Integer ,Integer> findMoney(Integer requiredSum) {
        if (requiredSum % 50 != 0) {
            System.out.println("Введите сумму кратную 50");
        } else {
            //Погуглить код разбиения на числа (5000, 1000, 500, 100, 50}
            return null;
        }

        return null;
    }
}
