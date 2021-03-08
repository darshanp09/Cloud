package com.edureka.demo.kafka.entity;

import com.edureka.demo.kafka.json.CustomLocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    @JsonProperty("employee_id")
    private String employeeId;
    private String name;

    @JsonProperty("birth_date")
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    private LocalDate birthdate;

}
