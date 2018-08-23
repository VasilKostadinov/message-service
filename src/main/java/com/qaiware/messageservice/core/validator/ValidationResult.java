package com.qaiware.messageservice.core.validator;

public class ValidationResult {

    private boolean isValid;

    private String errorMessage;

    private ValidationResult(boolean isValid, String errorMessage){
        this.isValid = isValid;
        this.errorMessage = errorMessage;
    }

    public boolean isValid(){
        return isValid;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public static ValidationResult valid(){
        return new ValidationResult(true,"");
    }

    public static ValidationResult inValid(String errorMessage){
        return new ValidationResult(false,errorMessage);
    }
}
