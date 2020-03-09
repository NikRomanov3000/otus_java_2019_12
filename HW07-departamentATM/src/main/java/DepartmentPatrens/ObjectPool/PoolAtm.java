package DepartmentPatrens.ObjectPool;

import myATM.ATMinterface.ATM_interface;

import java.util.ArrayList;
import java.util.List;

public class PoolAtm{
    private final List<ATM_interface> atmPool;
    private int current = 0;
    private final int saveSize;
    private final FactoryATM saveFactory;

    public PoolAtm(int size, FactoryATM factory) {
        this.saveSize=size;
        this.saveFactory=factory;
        atmPool = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            ATM_interface atm = factory.create();
            atmPool.add(atm);
        }
    }

    public PoolAtm (PoolAtm pool) { //всё ради Memento! ГЛУБОКОЕ КОПИРВАНИЕ ОБЪЕКТОВ
        this(pool.getSize(), pool.getSaveFactory());
       // this.atmPool=poolOfAtms.getAll();
      //  this.current=poolOfAtms.getCurrent();
    }

    public ATM_interface get() {
        if (atmPool.size() == current) {
            throw new RuntimeException("all ATM are used!");
        }
        return atmPool.get(current++);
    }

    public List<ATM_interface> getAll() {
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
