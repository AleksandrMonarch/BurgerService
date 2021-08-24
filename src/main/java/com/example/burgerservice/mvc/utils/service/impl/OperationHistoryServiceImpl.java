package com.example.burgerservice.mvc.utils.service.impl;

import com.example.burgerservice.mvc.utils.dao.OperationHistory;
import com.example.burgerservice.mvc.utils.repo.OperationHistoryRepository;
import com.example.burgerservice.mvc.utils.service.OperationHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OperationHistoryServiceImpl implements OperationHistoryService {

    private final OperationHistoryRepository operationHistoryRepository;

    @Autowired
    public OperationHistoryServiceImpl(OperationHistoryRepository operationHistoryRepository) {
        this.operationHistoryRepository = operationHistoryRepository;
    }

    @Override
    public void addRecord(String actionType, String rowDescription, Object object) {
        OperationHistory operationHistory = OperationHistory
                .builder()
                .actionType(actionType)
                .rawDescription(rowDescription)
                .broadDescription(object.toString())
                .build();

        log.info("save record {}", operationHistory);
        operationHistoryRepository.save(operationHistory);
    }


}
