package com.departamentATM.myATM.ATMinterface;

import com.departamentATM.myATM.Banknote;

import java.util.List;

public interface ATMInterface {
    int getATMBalance();
    void refreshATMCassettes();
    void addBanknoteToAtm(Banknote banknote);
    List<Banknote> getBanknotesFromATM(int requiredSum);
}
