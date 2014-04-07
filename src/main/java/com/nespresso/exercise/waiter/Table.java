package com.nespresso.exercise.waiter;

public class Table {

    private static final String MISSING = "MISSING";
    private static final String SEPARATOR = " ";

    private static final String COMMAND_SEPARATOR = ": ";

    private Order order;
    private int numberOfPersons;

    Table(int sizeOfTable) {
        this.numberOfPersons = sizeOfTable;
        this.order = new Order();
    }

    void constructOrder(String command) throws Exception {
        String[] personAndItsOrderLine = command.split(COMMAND_SEPARATOR);
        if (personAndItsOrderLine == null) {
            return;
        }

        String personName = personAndItsOrderLine[0];
        String orderLineName = personAndItsOrderLine[1];

        this.order.addLine(orderLineName, personName);
    }

    String reportOrder() {

        if (notAllPersonsHaveCommand()) {
            return sayMissingCommands();
        }

        return sayCompleteCommands();
    }

    private String sayCompleteCommands() {
        return this.order.showLines();
    }

    private String sayMissingCommands() {
        StringBuilder orders = new StringBuilder();
        int numberOfMissingCommands = this.numberOfPersons - this.order.numberOfOrderLines();
        orders.append(MISSING).append(SEPARATOR).append(numberOfMissingCommands);
        return orders.toString();
    }

    private boolean notAllPersonsHaveCommand() {
        return this.order.numberOfOrderLines() < this.numberOfPersons;
    }
}
