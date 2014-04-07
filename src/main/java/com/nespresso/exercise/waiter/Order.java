package com.nespresso.exercise.waiter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Order {

    private static final String SPACE = " ";
    private static final String FOR_COMMAND = " for ";
    private static final String SAME_COMMAND = "Same";

    private List<OrderLine> orderLines;

    Order() {
        this.orderLines = new ArrayList<OrderLine>();
    }

    void addLine(String orderLineName, String ownerName) {
        if (SAME_COMMAND.equals(orderLineName)) {
            inscribeSameCommand(ownerName);
        } else if (isForCommand(orderLineName)) {
            inscribeForCommand(orderLineName, ownerName);
        } else {
            OrderLine newLine = new OrderLine(new Meal(orderLineName), new Person(ownerName));
            inscribeNewCommand(newLine);
        }

    }

    private void inscribeForCommand(String orderLineName, String ownerName) {
        String[] forCommand = orderLineName.split(SPACE);
        String mealName = forCommand[0];
        int numberOfPersons = Integer.parseInt(forCommand[2]);

        Meal meal = new Meal(mealName, numberOfPersons);
        OrderLine line = new OrderLine(meal, new Person(ownerName));
        OrderLine newLine = commandForMealAlreadyCommandedByOtherPersons(ownerName, line);
        inscribeNewCommand(newLine);
    }

    private OrderLine commandForMealAlreadyCommandedByOtherPersons(String ownerName, OrderLine line) {
        for (OrderLine currentLine : this.orderLines) {
            if (currentLine.concerns(line)) {
                line = new OrderLine(currentLine.commandMeal(), new Person(ownerName));
                break;
            }
        }
        return line;
    }

    private boolean isForCommand(String orderLineName) {
        return orderLineName.contains(FOR_COMMAND);
    }

    private void inscribeNewCommand(OrderLine newLine) {
        int commandNumber = this.orderLines.indexOf(newLine);
        if (hasPersonChangeItsCommand(commandNumber)) {
            this.orderLines.set(commandNumber, newLine);
        } else {
            this.orderLines.add(newLine);
        }
    }

    private void inscribeSameCommand(String ownerName) {
        OrderLine lastCommand = orderLines.get(orderLines.size() - 1);
        if (lastCommand.isItsOwner(new Person(ownerName))) {
            lastCommand = orderLines.get(orderLines.size() - 2);
        }

        inscribeNewCommand(lastCommand.changeOwner(new Person(ownerName)));
    }

    private boolean hasPersonChangeItsCommand(int commandNumber) {
        return commandNumber != -1;
    }

    String showLines() {

        StringBuilder order = new StringBuilder();

        Iterator<OrderLine> linesIterator = orderLines.iterator();
        for (; linesIterator.hasNext();) {
            OrderLine line = linesIterator.next();
            if (forCommandsNotCompleted(line)) {
                return line.print();
            }
            order.append(line.print());
            if (linesIterator.hasNext()) {
                order.append(", ");
            }
        }

        return order.toString();
    }

    private boolean forCommandsNotCompleted(OrderLine line) {
        return line.isMissingCommandsForMeal() != 0;
    }

    int numberOfOrderLines() {
        return orderLines.size();
    }
}
