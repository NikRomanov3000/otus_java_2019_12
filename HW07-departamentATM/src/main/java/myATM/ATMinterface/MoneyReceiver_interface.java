package myATM.ATMinterface;

import myATM.Banknote;
import myATM.Cassette;

import java.util.List;

public interface MoneyReceiver_interface {
    void addBanknoteToATM(Banknote banknote, List<Cassette> cassettes);
    List<Banknote> getBanknotesFromATM(int requiredNumberOfBanknotes, List<Cassette> cassettes);
}
