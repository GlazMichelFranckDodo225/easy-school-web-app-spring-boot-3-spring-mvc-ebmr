package com.dgmf.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Contact {
    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;
}
