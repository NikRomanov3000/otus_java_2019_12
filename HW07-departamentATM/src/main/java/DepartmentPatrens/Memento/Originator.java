package DepartmentPatrens.Memento;

import DepartmentPatrens.ObjectPool.PoolAtm;

import java.util.ArrayDeque;
import java.util.Deque;

public class Originator {
    private final Deque<Memento> stack = new ArrayDeque<Memento>();

    public void saveState(PoolAtm state) {
        stack.push(new Memento(state));
    }

    public PoolAtm restoreState() {
        return stack.pop().getState();
    }
}
