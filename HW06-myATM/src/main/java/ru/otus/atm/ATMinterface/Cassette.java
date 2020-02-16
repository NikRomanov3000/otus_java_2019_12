package ru.otus.atm.ATMinterface;

import java.util.List;

public interface Cassette {
    void addBanknotes(List<? extends Banknote> banknotes);
}
