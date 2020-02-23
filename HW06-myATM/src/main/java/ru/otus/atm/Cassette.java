package ru.otus.atm;

import java.util.LinkedList;

public class Cassette {
    private int numberOfBanknotes;
    private final int denominationOfBanknotes;
    private static final int MAX_NUMBER_OF_BANKNOTES = 2500;
    LinkedList<Banknote> banknotesInCassette;

    public Cassette(int denominationOfBanknotes) {
        numberOfBanknotes = 0;
        this.denominationOfBanknotes = denominationOfBanknotes;
        for (int i = 0; i < 1500; i++) {
            banknotesInCassette.add(new Banknote((denominationOfBanknotes)));
        }
        numberOfBanknotes = 1500;
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

    public Banknote getBanknote() {
        if (numberOfBanknotes > 0) {
            numberOfBanknotes--;
            return banknotesInCassette.getLast();
        } else throw new RuntimeException("Недостаточно купюр нужного номинала");
    }
}
