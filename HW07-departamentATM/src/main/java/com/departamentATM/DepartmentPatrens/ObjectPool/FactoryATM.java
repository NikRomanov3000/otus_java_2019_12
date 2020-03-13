package com.departamentATM.DepartmentPatrens.ObjectPool;

import com.departamentATM.myATM.ATMinterface.ATMInterface;
import com.departamentATM.myATM.MyFirstATM;

public class FactoryATM {

    public ATMInterface create() {
        return new MyFirstATM();
    }
}
