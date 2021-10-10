package com.example.burgerservice.constant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
@Getter
@Setter
public class LogProperties {

    private Boolean isLogMethodDetailSignature = true;
    private Boolean isLogMethodExecutionTime = true;
}
