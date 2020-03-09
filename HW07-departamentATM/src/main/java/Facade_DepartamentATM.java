import DepartmentPatrens.ObjectPool.FactoryATM;
import DepartmentPatrens.ObjectPool.PoolAtm;
import myATM.DepartamentATM_interface;
//Facade_DepartamentATM Реализует упращённые интерфейс работы со всеми банокматами, 3 кнопки для сложноый системы АТМ-ов
public class Facade_DepartamentATM implements DepartamentATM_interface {
    private PoolAtm poolAtm;
    private int numberOfAtm;

    public Facade_DepartamentATM(int numberOfAtm){
        this.numberOfAtm=numberOfAtm;
        poolAtm = new PoolAtm(numberOfAtm, new FactoryATM());
    }

    @Override
    public int getBalance() {
        return 0;
    }

    @Override
    public void RefreshAll() {

    }

    @Override
    public void showAllATM() {

    }
}
