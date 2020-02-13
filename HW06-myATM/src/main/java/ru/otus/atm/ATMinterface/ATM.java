package ru.otus.atm.ATMinterface;

import java.util.List;

public interface ATM {
    public List giveMoney();
    public void addBanknotesToATM();
    public void showCardBalance();
    public void addMoneyToBalance();
    public void checkCardHolder();

}
