package DepartmentPatrens.Observer;

import DepartmentPatrens.ObjectPool.PoolAtm;
import myATM.ATMinterface.ATM_interface;

import java.util.List;

public class EventRefreshAll {
    private final List<ATM_interface> atms;

    public EventRefreshAll(List<ATM_interface> pullOfAtm) {
        this.atms = pullOfAtm;
    }

    public void refreshAll() {
        for (ATM_interface atm : atms) {
            atm.refreshATMCassettes();
        }
    }
}
