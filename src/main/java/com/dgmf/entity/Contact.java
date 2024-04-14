package com.dgmf.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
public class Contact {
    private final String name;
    private final String mobileNum;
    private final String email;
    private final String subject;
    private final String message;
}
