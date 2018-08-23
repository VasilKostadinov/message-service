package com.qaiware.messageservice.core.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ValidationChain {

    private List<ValidationResult> validationResults = new ArrayList<>();
    private boolean isValid;

    public ValidationChain(List<ValidationResult> validationResults){
        Objects.requireNonNull(validationResults,"Validation results can not be null !");
        this.validationResults.addAll(validationResults);
        this.isValid = this.validationResults.stream().allMatch(r-> r.isValid());
    }

    public ValidationChain(boolean isValid){
        this.isValid = isValid;
    }

    public List<ValidationResult> getValidationResults() {
        return new ArrayList<>(validationResults);
    }

    public boolean isValid(){
        return isValid;
    }
}
