package com.departamentATM.DepartmentPatrens.myATM.ATMinterface;
import com.departamentATM.DepartmentPatrens.myATM.Banknote;

public interface CassetteInterface {
    int getNumberOfBanknotes();

    int getDenomination();

    void refreshCassette(int denomination);

    void addBanknote(Banknote banknote);

    Banknote getBanknote();
}
