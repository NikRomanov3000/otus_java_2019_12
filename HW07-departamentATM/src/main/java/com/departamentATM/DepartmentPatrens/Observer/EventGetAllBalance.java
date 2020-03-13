package com.departamentATM.DepartmentPatrens.Observer;

import com.departamentATM.DepartmentPatrens.myATM.ATMinterface.ATMInterface;
import java.util.List;

public class EventGetAllBalance {
    private final List<ATMInterface> atms;

    public EventGetAllBalance(List<ATMInterface> pullOfAtm) {
        this.atms = pullOfAtm;
    }

    public int getBalanceOfAllAtms() {
        int balance=0;
        for (ATMInterface atm : atms) {
            balance +=atm.getATMBalance();
        }
        return balance;
    }
}
