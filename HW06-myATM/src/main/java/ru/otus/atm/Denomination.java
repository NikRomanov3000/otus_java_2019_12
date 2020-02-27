package ru.otus.atm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Denomination {
    private Integer denomination1 = 5000;
    private Integer denomination2 = 1000;
    private Integer denomination3 = 500;
    private Integer denomination4 = 100;
    private List<Integer> denominationList = new ArrayList<>();

    public Denomination() {
        denominationList.add(denomination1);
        denominationList.add(denomination2);
        denominationList.add(denomination3);
        denominationList.add(denomination4);
    }

    private void sortListDenomination(List<Integer> denominationList) {
        Collections.sort(denominationList);
    }

    public List<Integer> getDenominationsList() {
        sortListDenomination(denominationList);
        return denominationList;
    }

    public Integer getDenomination1() {
        return denomination1;
    }

    public Integer getDenomination2() {
        return denomination2;
    }

    public Integer getDenomination3() {
        return denomination3;
    }

    public Integer getDenomination4() {
        return denomination4;
    }

}
