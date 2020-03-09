package myATM;

import myATM.ATMinterface.Cassette_interface;

import java.util.LinkedList;

public class Cassette implements Cassette_interface {
    private int numberOfBanknotes;
    private final int denominationOfBanknotes;
    private static final int MAX_NUMBER_OF_BANKNOTES = 2500;
    LinkedList<Banknote> banknotesInCassette = new LinkedList<>();

    public Cassette(int denominationOfBanknotes) {
        numberOfBanknotes = 0;
        this.denominationOfBanknotes = denominationOfBanknotes;
        refreshCassette(denominationOfBanknotes);
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
        for (int i = 0; i < 1500; i++) {
            banknotesInCassette.add(new Banknote((denominationOfBanknotes)));
        }
        numberOfBanknotes = 1500;
    }

    @Override
    public void addBanknote(Banknote banknote) {
        if (numberOfBanknotes < MAX_NUMBER_OF_BANKNOTES && banknote.getDenomination() == denominationOfBanknotes) {
            banknotesInCassette.addLast(banknote);
            numberOfBanknotes++;
        } else throw new RuntimeException("Нет места для купюр данного номинала, обратитесь к сотруднику банка");
    }

    @Override
    public Banknote getBanknote() {
        if (numberOfBanknotes > 0) {
            numberOfBanknotes--;
            return banknotesInCassette.getLast();
        } else throw new RuntimeException("Недостаточно купюр нужного номинала");
    }
}
