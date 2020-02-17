package ru.otus.atm.ATMinterface;

import ru.otus.atm.myCassette;

import java.util.List;

public interface MoneyChecker {
    int checkATMBalance(List<myCassette> cassettes);
}
