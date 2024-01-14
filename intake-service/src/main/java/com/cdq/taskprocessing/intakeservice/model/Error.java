package com.cdq.taskprocessing.intakeservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Error(String field, String message) {
}
