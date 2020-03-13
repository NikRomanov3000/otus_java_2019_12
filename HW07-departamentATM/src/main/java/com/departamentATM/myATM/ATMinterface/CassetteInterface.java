package com.departamentATM.myATM.ATMinterface;
import com.departamentATM.myATM.Banknote;

public interface CassetteInterface {
    int getNumberOfBanknotes();

    int getDenomination();

    void refreshCassette(int denomination);

    void addBanknote(Banknote banknote);

    Banknote getBanknote();
}
