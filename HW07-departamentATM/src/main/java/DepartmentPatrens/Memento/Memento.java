package DepartmentPatrens.Memento;

import DepartmentPatrens.ObjectPool.PoolAtm;

public class Memento {
    private final PoolAtm state;

    public Memento(PoolAtm state) {
        this.state = new PoolAtm(state);
    }

    public PoolAtm getState() {
        return state;
    }
}
