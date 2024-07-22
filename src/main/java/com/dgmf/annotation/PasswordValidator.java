package com.dgmf.annotation;

import com.dgmf.validation.PasswordStrengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

// JavaDoc wil Be Carried Over
@Documented
// Mention the Class which Holds the Custom Logic Details of the Annotation
@Constraint(validatedBy = PasswordStrengthValidator.class)
// On Top Of which Types this Should Be Used
@Target({ElementType.METHOD, ElementType.FIELD})
// This Annotation will Be Executed at Runtime (Not at Compile Time)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    // Each Method Represents an Input Parameter to the
    // Annotation or Annotation's Attributes
    String message() default "Please choose a strong password";
    Class<?>[] groups() default {}; // To Group Annotations into Various Groups
    Class<? extends Payload>[] payload() default {}; // Extra Metadata Related
    // to the annotation
}
