package io.inoa.fleet.registry.hono;

import org.eclipse.paho.client.mqttv3.MqttException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {
	public static void main(String[] args) throws MqttException {
		String url = System.getenv("MQTT_HOST");
		String deviceId = System.getenv("HONO_GATEWAY_ID");
		String tenantId = System.getenv("HONO_TENANT");
		String password = System.getenv("HONO_GATEWAY_PASSWORD");
		HonoMqttClient honoMqttClient = new HonoMqttClient(url, deviceId, tenantId, password);
		honoMqttClient.start();
	}
}
