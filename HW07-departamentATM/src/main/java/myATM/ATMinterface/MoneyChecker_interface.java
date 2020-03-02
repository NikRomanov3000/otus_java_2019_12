package myATM.ATMinterface;

import myATM.Cassette;

import java.util.List;

public interface MoneyChecker_interface {
    int checkATMBalance(List<Cassette> cassettes);
}
