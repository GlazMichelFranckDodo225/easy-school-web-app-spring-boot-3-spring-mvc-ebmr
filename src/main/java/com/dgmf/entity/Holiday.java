package com.dgmf.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @AllArgsConstructor @Builder
public class Holiday {
    private final String day;
    private final String reason;
    private final Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }
}
