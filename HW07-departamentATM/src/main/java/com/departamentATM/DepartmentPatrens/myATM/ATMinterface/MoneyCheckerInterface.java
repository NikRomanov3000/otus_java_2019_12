package com.departamentATM.DepartmentPatrens.myATM.ATMinterface;

import com.departamentATM.DepartmentPatrens.myATM.Cassette;

import java.util.List;

public interface MoneyCheckerInterface {
    int checkATMBalance(List<Cassette> cassettes);
}
