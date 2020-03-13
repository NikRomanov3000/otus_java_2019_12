package com.departamentATM.DepartmentPatrens;

import com.departamentATM.DepartmentPatrens.Memento.Originator;
import com.departamentATM.DepartmentPatrens.ObjectPool.FactoryATM;
import com.departamentATM.DepartmentPatrens.ObjectPool.PoolAtm;
import com.departamentATM.DepartmentPatrens.Observer.EventGetAllBalance;
import com.departamentATM.DepartmentPatrens.myATM.ATMinterface.ATMInterface;
import com.departamentATM.DepartmentPatrens.myATM.DepartamentATMInterface;

//Facade_DepartamentATM Реализует упращённые интерфейс работы со всеми банокматами, аля 3 кнопки для сложноый системы АТМ-ов
public class FacadeDepartamentATM implements DepartamentATMInterface {
    private PoolAtm poolAtm;
    private final int numberOfAtm;
    private final Originator originator;

    public FacadeDepartamentATM(int numberOfAtm) {
        poolAtm = new PoolAtm(numberOfAtm, new FactoryATM());
        originator = new Originator();
        originator.saveState(poolAtm);
        this.numberOfAtm = numberOfAtm;
    }

    @Override
    public int getBalance() {
        EventGetAllBalance balance = new EventGetAllBalance(poolAtm.getAll());
        return balance.getBalanceOfAllAtms();
    }

    @Override
    public void RefreshAll() { //Memento
        poolAtm = originator.restoreState();
    }

    @Override
    public void showAllATM() {
        for (ATMInterface atm : poolAtm.getAll()) {
            System.out.println(atm);
        }
    }

    public ATMInterface getATM() {
        return poolAtm.get();
    }
}
