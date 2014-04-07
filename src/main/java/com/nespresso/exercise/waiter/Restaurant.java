package com.nespresso.exercise.waiter;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private List<Table> tables = new ArrayList<Table>();

    public int initTable(int sizeOfTable) {
        this.tables.add(new Table(sizeOfTable));
        return this.tables.size() - 1;
    }

    public void customerSays(int tableId, String command) {

        try {
            this.tables.get(tableId).constructOrder(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String createOrder(int tableId) {
        Table table = this.tables.get(tableId);

        return table.reportOrder();
    }
}
