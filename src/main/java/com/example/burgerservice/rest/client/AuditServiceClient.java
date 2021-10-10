package com.example.burgerservice.rest.client;

import com.example.burgerservice.rest.dto.BaseResponseDto;
import com.example.burgerservice.rest.dto.DataRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Audit-Service-Client", url = "${client.audit.url}/api/")
public interface AuditServiceClient {

    @PostMapping()
    BaseResponseDto sendMethodLogData(@RequestBody DataRequestDto<String> dataRequestDto);
}
