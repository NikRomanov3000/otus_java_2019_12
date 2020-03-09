package DepartmentPatrens.ObjectPool;

import myATM.ATMinterface.ATM_interface;
import myATM.MyFirstATM;

public class FactoryATM {

    public ATM_interface create() {
        return new MyFirstATM();
    }
}
