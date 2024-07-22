package com.dgmf.annotation;

import com.dgmf.validation.FieldsValueMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Mention the Class which Holds the Custom Logic Details of the Annotation
@Constraint(validatedBy = FieldsValueMatchValidator.class)
// On Top Of which Types this Should Be Used
@Target({ElementType.TYPE})
// This Annotation will Be Executed at Runtime (Not at Compile Time)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {
    // Each Method Represents an Input Parameter to the
    // Annotation or Annotation's Attributes
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String message() default "Fields values don't match!";
    String field();
    String fieldMatch();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldsValueMatch[] value();
    }
}
