package io.inoa.fleet.registry.hono;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("inoa")
public class InoaConfig {
	private List<String> tenantIds = new ArrayList<>();
}
