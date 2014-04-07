package com.nespresso.exercise.waiter;

public class OrderLine {

    private final Meal meal;
    private final Person owner;

    OrderLine(Meal meal, Person owner) {
        this.meal = meal;
        this.owner = owner;
    }

    String print() {
        return this.meal.print();
    }

    int isMissingCommandsForMeal() {
        return this.meal.isMissingCommands();
    }

    OrderLine changeOwner(Person owner) {
        return new OrderLine(this.meal, owner);
    }

    boolean concerns(OrderLine line) {
        return this.meal.concerns(line.meal) && !this.owner.equals(line.owner);
    }

    Meal commandMeal() {
        return this.meal.beCommanded();
    }

    boolean isItsOwner(Person person) {
        return this.owner.equals(person);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderLine other = (OrderLine) obj;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        return true;
    }

}
