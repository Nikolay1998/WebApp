package com.kraynov.calculator.validation;

public class CalculatorValidator {
    private final static String BASE_VALIDATION_MESSAGE  = "Основание должно быть неотрицательным числом!";
    private final static String POWER_VALIDATION_MESSAGE = "Степень должна быть числом!";

    private double base;
    private double power;
    private boolean isValid;
    private String baseValidationMessage = "";
    private String powerValidationMessage = "";

    /**
     * Validation of basement - base should be non negative number
     */
    public void runValidation(String baseStr, String powerStr) {
        isValid = false;
        try {
            base = Double.valueOf(baseStr);
            if (base < 0) baseValidationMessage = BASE_VALIDATION_MESSAGE;
        } catch (NumberFormatException ex) {
            baseValidationMessage = BASE_VALIDATION_MESSAGE;
        }

        try {
            power = Double.valueOf(powerStr);
        } catch (NumberFormatException ex) {
            powerValidationMessage = POWER_VALIDATION_MESSAGE;
        }

        if ("".equals(baseValidationMessage) && "".equals(powerValidationMessage)) {
            isValid = true;
        }
    }

    public String getBaseValidationMessage() {
        return baseValidationMessage;
    }

    public String getPowerValidationMessage() {
        return powerValidationMessage;
    }

    public boolean isValid() {
        return isValid;
    }

    public double getBase() {
        if (isValid) {
            return base;
        }
        throw new IllegalStateException("Data were not validated successfully");
    }

    public double getPower() {
        if (isValid) {
            return power;
        }
        throw new IllegalStateException("Data were not validated successfully");
    }

    @Override
    public String toString() {
        return "CalculatorValidator{" +
                "base=" + base +
                ", power=" + power +
                ", isValid=" + isValid +
                ", baseValidationMessage='" + baseValidationMessage + '\'' +
                ", powerValidationMessage='" + powerValidationMessage + '\'' +
                '}';
    }
}
