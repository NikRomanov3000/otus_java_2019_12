package com.departamentATM.DepartmentPatrens.ObjectPool;

import com.departamentATM.DepartmentPatrens.myATM.ATMinterface.ATMInterface;
import com.departamentATM.DepartmentPatrens.myATM.MyFirstATM;

public class FactoryATM {

    public ATMInterface create() {
        return new MyFirstATM();
    }
}
