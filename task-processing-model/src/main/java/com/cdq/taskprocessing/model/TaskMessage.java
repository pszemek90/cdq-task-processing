package com.cdq.taskprocessing.model;

import java.util.UUID;

public record TaskMessage(UUID uuid, String input, String pattern) {
}
