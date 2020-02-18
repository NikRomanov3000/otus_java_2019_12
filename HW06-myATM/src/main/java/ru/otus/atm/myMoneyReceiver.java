package ru.otus.atm;

import ru.otus.atm.ATMinterface.Banknote;
import ru.otus.atm.ATMinterface.MoneyChecker;
import ru.otus.atm.ATMinterface.MoneyReceiver;

import java.util.List;
import java.util.Map;

public class myMoneyReceiver implements MoneyReceiver {

    @Override
    public void addBanknoteToATM(Banknote banknote, List<myCassette> cassettes) {
       myMoneyChecker myMoneyChecker = new myMoneyChecker();
        Map<Integer, myCassette> banknoteMap = myMoneyChecker.calcBanknoteByDenomination(cassettes);

        for (Map.Entry<Integer, myCassette> map : banknoteMap.entrySet()){
            if(banknote.getDenomination() == map.getKey()){
                map.getValue().addBanknote(banknote);
            } else System.out.println("Внутренняя ошибка банкомата, обраитесь к сотруднику банка");
        }

    }

    @Override
    public List<Banknote> getBanknotesFromATM() {
        return null;
    }
}
