package DepartmentPatrens.Observer;

import myATM.ATMinterface.ATM_interface;
import java.util.List;

public class EventGetAllBalance {
    private final List<ATM_interface> atms;

    public EventGetAllBalance(List<ATM_interface> pullOfAtm) {
        this.atms = pullOfAtm;
    }

    public int getBalanceOfAllAtms() {
        int balance=0;
        for (ATM_interface atm : atms) {
            balance +=atm.getATMBalance();
        }
        return balance;
    }
}
