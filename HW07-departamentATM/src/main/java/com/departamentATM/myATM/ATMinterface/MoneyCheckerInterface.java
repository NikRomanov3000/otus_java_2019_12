package com.departamentATM.myATM.ATMinterface;

import com.departamentATM.myATM.Cassette;

import java.util.List;

public interface MoneyCheckerInterface {
    int checkATMBalance(List<Cassette> cassettes);
}
