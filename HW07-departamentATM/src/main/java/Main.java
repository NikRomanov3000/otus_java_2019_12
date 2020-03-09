import DepartmentPatrens.Memento.Originator;
import DepartmentPatrens.ObjectPool.FactoryATM;
import DepartmentPatrens.ObjectPool.PoolAtm;
import DepartmentPatrens.Observer.EventGetAllBalance;
import DepartmentPatrens.Observer.EventRefreshAll;

public class Main {
    public static void main(String[] args) {
        Facade_DepartamentATM departamentATM = new Facade_DepartamentATM();
        System.out.println(departamentATM.getBalance()); //начальный баланс
        departamentATM.getATM().getBanknotesFromATM(99900);
        System.out.println(departamentATM.getBalance()); //баланс после снятия
        departamentATM.RefreshAll();
        System.out.println(departamentATM.getBalance()); // баланс после рефреша
        departamentATM.showAllATM();
    }
}
