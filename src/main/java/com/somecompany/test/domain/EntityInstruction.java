package com.somecompany.test.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EntityInstruction {

    BUY("B"),
    SELL("S");

    private String value;
}
