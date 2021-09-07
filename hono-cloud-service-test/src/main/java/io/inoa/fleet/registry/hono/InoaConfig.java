package io.inoa.fleet.registry.hono;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("inoa")
public class InoaConfig {
	private List<String> tenantIds = new ArrayList<>();
}
