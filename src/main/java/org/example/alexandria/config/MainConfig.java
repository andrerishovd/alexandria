package org.example.alexandria.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "alexandria")
@Data
public class MainConfig {
    private int initialbooks;

}
