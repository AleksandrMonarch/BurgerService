package com.example.burgerservice.mvc.utils.repo;

import com.example.burgerservice.mvc.utils.dao.OperationHistory;
import org.springframework.data.repository.CrudRepository;

public interface OperationHistoryRepository extends CrudRepository<OperationHistory, String> {
}
