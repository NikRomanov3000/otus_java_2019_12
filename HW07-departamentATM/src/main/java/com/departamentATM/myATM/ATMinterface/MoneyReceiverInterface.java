package com.departamentATM.myATM.ATMinterface;

import com.departamentATM.myATM.Banknote;
import com.departamentATM.myATM.Cassette;

import java.util.List;

public interface MoneyReceiverInterface {
    void addBanknoteToATM(Banknote banknote, List<Cassette> cassettes);
    List<Banknote> getBanknotesFromATM(int requiredNumberOfBanknotes, List<Cassette> cassettes);
}
