package com.cdq.taskprocessing.intakeservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(List<Error> errors) {
}
