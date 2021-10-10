package com.example.burgerservice.constant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@NoArgsConstructor
//@ConfigurationProperties("log")
@Component
@Getter
@Setter
public class LogProperties {

    private Boolean isLogMethodDetailSignature = true;
    private Boolean isLogMethodExecutionTime = true;
}
