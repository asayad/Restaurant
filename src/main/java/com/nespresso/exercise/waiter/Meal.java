package com.nespresso.exercise.waiter;

public class Meal {

    private static final String FOR = "for";
    private static final String SEPARATOR = " ";
    private static final String MISSING = "MISSING";

    private final String name;
    private final int forPersons;
    private int numberOfCommands;

    Meal(String orderLineName) {
        this.name = orderLineName;
        this.forPersons = 0;
    }

    Meal(String name, int forPersons) {
        this.name = name;
        this.forPersons = forPersons;
        this.numberOfCommands = 1;
    }

    private Meal(String name, int forPersons, int numberOfCommands) {
        this.name = name;
        this.forPersons = forPersons;
        this.numberOfCommands = numberOfCommands;
    }

    Meal beCommanded() {
        if (this.numberOfCommands + 1 <= this.forPersons) {
            numberOfCommands++;
            return this;
        }

        return new Meal(this.name, this.forPersons, 1);
    }

    int isMissingCommands() {
        return this.forPersons - this.numberOfCommands;
    }

    boolean concerns(Meal meal) {
        return this.name.equals(meal.name) && this.forPersons == meal.forPersons && this.numberOfCommands < this.forPersons;
    }

    String print() {
        int missingCommands = isMissingCommands();
        if (missingCommands != 0) {
            return printMissingCommands(missingCommands);
        } else if (isForCommand()) {
            return printForCommand();
        } else {
            return printNormalCommand();
        }
    }

    private boolean isForCommand() {
        return this.forPersons > 0;
    }

    private String printNormalCommand() {
        StringBuilder commandResult = new StringBuilder();
        return commandResult.append(this.name).toString();
    }

    private String printForCommand() {
        StringBuilder commandResult = new StringBuilder();
        return commandResult.append(this.name).append(SEPARATOR).append(FOR).append(SEPARATOR).append(this.forPersons).toString();
    }

    private String printMissingCommands(int missingCommands) {
        StringBuilder commandResult = new StringBuilder();
        return commandResult.append(MISSING).append(SEPARATOR).append(missingCommands).append(SEPARATOR).append(FOR).append(SEPARATOR)
            .append(this.name).append(SEPARATOR).append(FOR).append(SEPARATOR).append(this.forPersons).toString();
    }
}
