package com.departamentATM.DepartmentPatrens.myATM.ATMinterface;

import com.departamentATM.DepartmentPatrens.myATM.Banknote;

import java.util.List;

public interface ATMInterface {
    int getATMBalance();
    void refreshATMCassettes();
    void addBanknoteToAtm(Banknote banknote);
    List<Banknote> getBanknotesFromATM(int requiredSum);
}
