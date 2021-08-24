package com.example.burgerservice.mvc.utils.service;

public interface OperationHistoryService {

    void addRecord(String actonType, String rowDescription, Object object);
}
