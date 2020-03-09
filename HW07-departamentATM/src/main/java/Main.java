import DepartmentPatrens.Memento.Originator;
import DepartmentPatrens.ObjectPool.FactoryATM;
import DepartmentPatrens.ObjectPool.PoolAtm;
import DepartmentPatrens.Observer.EventGetAllBalance;
import DepartmentPatrens.Observer.EventRefreshAll;

public class Main {
    public static void main(String[] args) {
        PoolAtm poolAtm = new PoolAtm(5, new FactoryATM());
        Originator originator = new Originator();
        originator.saveState(poolAtm);

        System.out.println(poolAtm.get().getATMBalance());
        EventGetAllBalance balanceOfAtms = new EventGetAllBalance(poolAtm.getAll());
        System.out.println(balanceOfAtms.getBalanceOfAllAtms());

        poolAtm.get().getBanknotesFromATM(999900);
        System.out.println("new balance " + balanceOfAtms.getBalanceOfAllAtms());
        System.out.println(poolAtm);

        poolAtm = originator.restoreState();
        EventGetAllBalance balanceOfAtms2 = new EventGetAllBalance(poolAtm.getAll());
        System.out.println("refresh balance memento: " + balanceOfAtms.getBalanceOfAllAtms());

   }
}
