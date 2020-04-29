package myContext.ru.otus.services;

import myContext.ru.otus.model.Equation;

import java.util.List;

public interface EquationPreparer {
    List<Equation> prepareEquationsFor(int base);
}
