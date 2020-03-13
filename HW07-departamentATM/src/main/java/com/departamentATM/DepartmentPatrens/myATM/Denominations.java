package com.departamentATM.DepartmentPatrens.myATM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Denominations {
    denomination5000(5000),
    denomination1000(1000),
    denomination500(500),
    denomination100(100);

    private int value;
    private static List<Integer> listOfDenominations = new ArrayList<>();

    Denominations(int value) {
        this.value = value;
    }

    public int getValueOfDenomination() {
        return value;
    }

    public static List<Integer> getListOfDenominations() {
        listOfDenominations.add(denomination5000.value);
        listOfDenominations.add(denomination1000.value);
        listOfDenominations.add(denomination500.value);
        listOfDenominations.add(denomination100.value);

        Collections.sort(listOfDenominations);
        return listOfDenominations;
    }

    @Override
    public String toString() {
        return " " + value;
    }
}
