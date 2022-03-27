package com.example.gameoflife.core;

public class CellStateRuleFactory {

    /**
     * If there are more than one implementation of CellStateRule this factory will decide which one to use.
     *
     * @return Implementation of CellStateRule
     */
    public static CellStateRule getCellStateRule(Board board) {
        return new CellStateRuleImpl();
    }
}
