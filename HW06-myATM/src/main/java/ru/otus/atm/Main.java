package ru.otus.atm;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        System.out.println("Изначальный баланс: " + atm.getATMBalance());
        Banknote myBanknote = new Banknote(5000);
        atm.addBanknoteToAtm(myBanknote);
        System.out.println("Баланс после пополнения: " + atm.getATMBalance());
        List<Banknote> myBanknotes = atm.getBanknotesFromATM(11800);
        for(Banknote banknote : myBanknotes){
            System.out.println(banknote.getDenomination());
        }
        System.out.println("Баланс после снятия: " + atm.getATMBalance());
    }


}
