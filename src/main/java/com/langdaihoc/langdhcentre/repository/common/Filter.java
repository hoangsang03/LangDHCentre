package com.langdaihoc.langdhcentre.repository.common;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Filter {
    private String field;
    private QueryOperator operator;
    private String value;
    private List<String> values;//Used in case of IN operator


    @Override
    public String toString() {
        return "Filter{" +
                "field='" + field + '\'' +
                ", operator=" + operator +
                ", value='" + value + '\'' +
                ", values=" + values +
                '}';
    }
}
