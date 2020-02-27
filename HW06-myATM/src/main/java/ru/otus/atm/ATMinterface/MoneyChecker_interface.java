package ru.otus.atm.ATMinterface;

import ru.otus.atm.Cassette;
import java.util.List;

public interface MoneyChecker_interface {
    int checkATMBalance(List<Cassette> cassettes);
}
