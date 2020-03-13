package com.departamentATM.DepartmentPatrens.ObjectPool;

import com.departamentATM.myATM.ATMinterface.ATMInterface;

import java.util.ArrayList;
import java.util.List;

public class PoolAtm {
    private final List<ATMInterface> atmPool;
    private int current = 0;
    private int saveSize;
    private FactoryATM saveFactory;

    public PoolAtm(int size, FactoryATM factory) {
        this.saveSize = size;
        this.saveFactory = factory;
        atmPool = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            ATMInterface atm = factory.create();
            atmPool.add(atm);
        }
    }

    public PoolAtm(PoolAtm pool) {
        this.atmPool = new ArrayList<>();
        for (int i = 0; i < pool.saveSize; i++) {
            ATMInterface atm = pool.saveFactory.create();
            atmPool.add(atm);
        }
    }

    public ATMInterface get() {
        if (atmPool.size() == current) {
            throw new RuntimeException("all ATM are used!");
        }
        return atmPool.get(current++);
    }

    public void addATM(ATMInterface atm) {
        atmPool.add(atm);
    }

    public void removeATM(ATMInterface atm) {
        atmPool.remove(atm);
    }

    public List<ATMInterface> getAll() {
        return atmPool;
    }

    public int getCurrent() {
        return current;
    }

    public FactoryATM getSaveFactory() {
        return saveFactory;
    }

    public int getSize() {
        return saveSize;
    }

}
