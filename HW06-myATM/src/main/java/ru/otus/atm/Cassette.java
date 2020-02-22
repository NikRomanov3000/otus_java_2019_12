package ru.otus.atm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cassette {
    private int numberOfBanknotes;
    private final int denominationOfBanknotes;
    static final int MAX_NUMBER_OF_BANKNOTES = 2500;
    LinkedList<Banknote> banknotesInCassette;

    public Cassette(int denominationOfBanknotes) {
        numberOfBanknotes = 0;
        this.denominationOfBanknotes = denominationOfBanknotes;
        this.banknotesInCassette = new LinkedList<>();
    }

    public int getNumberOfBanknotes() {
        return numberOfBanknotes;
    }

    public int getDenomination() {
        return denominationOfBanknotes;
    }

    public void refreshCassette(int denomination) {
        banknotesInCassette.clear();
        for (int i = 0; i < 1500; i++) {
            banknotesInCassette.add(new Banknote((denominationOfBanknotes)));
        }
        numberOfBanknotes = 1500;
    }

    public void addBanknote(Banknote banknote) {
        if (numberOfBanknotes < MAX_NUMBER_OF_BANKNOTES && banknote.getDenomination() == denominationOfBanknotes) {
            banknotesInCassette.addLast(banknote);
            numberOfBanknotes++;
        } else throw new RuntimeException("Нет места для купюр данного номинала, обратитесь к сотруднику банка");
    }

    public List<Banknote> getBanknotes(int requiredNumberOfBanknotes) {
        List<Banknote> requiredBanknotes = new ArrayList<>();
        if (requiredNumberOfBanknotes < numberOfBanknotes) {
            for (int i = 1; i < requiredNumberOfBanknotes; i++) {
                requiredBanknotes.add(banknotesInCassette.getLast());
            }
            numberOfBanknotes -= requiredNumberOfBanknotes;
            return requiredBanknotes;
        } else throw new RuntimeException("Недостаточно купюр нужного номинала");
    }
}
