package com.cdq.taskprocessing.intakeservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTaskRequest(
        @NotBlank(message = "Input cannot be blank")
        @NotNull(message = "Input cannot be null")
        String input,
        @NotBlank(message = "Pattern cannot be blank")
        @NotNull(message = "Pattern cannot be null")
        String pattern) {
}
