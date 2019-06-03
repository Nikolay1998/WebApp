package com.kraynov.calculator.calc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "task")
@XmlRootElement
public class PowerCalculationTask {

    @XmlElement(name = "power")
    private double power;
    @XmlElement(name = "base")
    private double base;
    @XmlElement(name = "result")
    private double result;
    @XmlElement(name = "error")
    private String errorMessage;

    public PowerCalculationTask() {
    }

    public PowerCalculationTask(double base, double power) {
        this.power = power;
        this.base = base;
    }

    public void run() {
        try {
            result = Math.pow(base, power);
            //throw new RuntimeException("Тестовый exception");
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    public double getPower() {
        return power;
    }

    public double getBase() {
        return base;
    }

    public double getResult() {
        if (errorMessage == null) {
            return result;
        } else {
            throw new IllegalStateException("Невозможно получить результат вычислений.");
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "PowerCalculationTask{" +
                "power=" + power +
                ", base=" + base +
                ", result=" + result +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
