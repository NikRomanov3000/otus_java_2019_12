package ru.otus.atm;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ATM myATM = new ATM();
        System.out.println("Изначальный баланс: " + myATM.getATMBalance());
        Banknote myBanknote = new Banknote(5000);
        myATM.addBanknoteToAtm(myBanknote);
        System.out.println("Баланс после пополнения: " + myATM.getATMBalance());
        List<Banknote> myBanknotes = myATM.getBanknotesFromATM(11800);
        for(Banknote banknote : myBanknotes){
            System.out.println(banknote.getDenomination());
        }
        System.out.println("Баланс после снятия: " + myATM.getATMBalance());
    }


}
