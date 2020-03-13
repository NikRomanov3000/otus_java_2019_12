package com.departamentATM.DepartmentPatrens.myATM.ATMinterface;

import com.departamentATM.DepartmentPatrens.myATM.Banknote;
import com.departamentATM.DepartmentPatrens.myATM.Cassette;

import java.util.List;

public interface MoneyReceiverInterface {
    void addBanknoteToATM(Banknote banknote, List<Cassette> cassettes);
    List<Banknote> getBanknotesFromATM(int requiredNumberOfBanknotes, List<Cassette> cassettes);
}
