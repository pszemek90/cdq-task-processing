package com.cdq.taskprocessing.intakeservice.database.dao;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskDao {

    public UUID createTask(String input, String pattern) {
        //todo mock implementation
        return UUID.randomUUID();
    }
}
