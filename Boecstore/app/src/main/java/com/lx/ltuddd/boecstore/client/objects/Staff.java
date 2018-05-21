package com.lx.ltuddd.boecstore.client.objects;

/**
 * Created by DaiPhongPC on 5/21/2018.
 */

public class Staff extends Person {
    private double salary;
    private String position;
    private float bonus;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public float getBonus() {
        return bonus;
    }

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }

    public double totalSalary() {
        return salary + bonus * salary;
    }
}
