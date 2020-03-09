import DepartmentPatrens.Memento.Originator;
import DepartmentPatrens.ObjectPool.FactoryATM;
import DepartmentPatrens.ObjectPool.PoolAtm;
import DepartmentPatrens.Observer.EventGetAllBalance;
import DepartmentPatrens.Observer.EventRefreshAll;
import myATM.ATMinterface.ATM_interface;
import myATM.DepartamentATM_interface;

//Facade_DepartamentATM Реализует упращённые интерфейс работы со всеми банокматами, аля 3 кнопки для сложноый системы АТМ-ов
public class Facade_DepartamentATM implements DepartamentATM_interface {
    private PoolAtm poolAtm;
    private final int numberOfAtm = 5;
    private final Originator originator;

    public Facade_DepartamentATM() {
        poolAtm = new PoolAtm(numberOfAtm, new FactoryATM());
        originator = new Originator();
        originator.saveState(poolAtm);
    }

    @Override
    public int getBalance() { //Observer balance of all atms
        EventGetAllBalance balance = new EventGetAllBalance(poolAtm.getAll());
        return balance.getBalanceOfAllAtms();
    }

    @Override
    public void RefreshAll() { //Memento
        poolAtm = originator.restoreState();
    }

    public void RefreshAllObserver() { //Observer refresh
        EventRefreshAll refresher = new EventRefreshAll(poolAtm.getAll());
        refresher.refreshAll();
    }

    @Override
    public void showAllATM() {
        for (ATM_interface atm : poolAtm.getAll()) {
            System.out.println(atm);
        }
    }

    public ATM_interface getATM() {
        return poolAtm.get();
    }
}
