package myATM.ATMinterface;

import myATM.Banknote;

import java.util.List;

public interface ATM_interface {
    int getATMBalance();

    void addBanknoteToAtm(Banknote banknote);

    List<Banknote> getBanknotesFromATM(int requiredSum);


}
