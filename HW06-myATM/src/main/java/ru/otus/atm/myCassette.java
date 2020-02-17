package ru.otus.atm;

import ru.otus.atm.ATMinterface.Banknote;
import ru.otus.atm.ATMinterface.Cassette;

import java.util.ArrayList;
import java.util.List;

public class myCassette implements Cassette {
    private int numberOfBanknotes;
    private final int denominationOfBanknotes;
    List<Banknote> banknotesInCassette;

    public myCassette(int denominationOfBanknotes) {
        numberOfBanknotes = 0;
        this.denominationOfBanknotes=denominationOfBanknotes;
        this.banknotesInCassette = new ArrayList<>();
    }

    @Override
    public int getNumberOfBanknotes() {

        return numberOfBanknotes;
    }

    @Override
    public int getDenomination() {
        return denominationOfBanknotes;
    }

    @Override
    public void refreshCassette(int denomination) {
        banknotesInCassette.clear();
        for(int i=0; i<2500; i++){ //Про 2500 купюр прочёл в статье про устройство банкомата
            banknotesInCassette.add(new myBanknote((denominationOfBanknotes)));
            numberOfBanknotes++;
        }
    }

    @Override
    public void addBanknote(Banknote banknote) {
        if(numberOfBanknotes<2500 && banknote.getDenomination()==denominationOfBanknotes){
            banknotesInCassette.add(banknote);
        } else System.out.println("Нет места для купюр данного номинала, обратитесь к сотруднику банка");
    }

    @Override
    public List<Banknote> getBanknotes(int requiredNumberOfBanknotes) {
        List<Banknote> requiredBanknotes = new ArrayList<>();
        if(requiredNumberOfBanknotes < numberOfBanknotes) {
            for (int i = 0; i < requiredNumberOfBanknotes; i++) {
                requiredBanknotes.add(banknotesInCassette.get(i));
            }
            return requiredBanknotes;
        } else
            return null;
    }

}
