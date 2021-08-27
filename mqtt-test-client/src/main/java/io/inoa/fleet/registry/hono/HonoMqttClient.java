package io.inoa.fleet.registry.hono;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class HonoMqttClient implements MqttCallback {

	private final String url;
	private final String deviceId;
	private final String tenantId;
	private final String password;
	private IMqttClient client;

	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public void start() throws MqttException {
		client = new MqttClient(url, String.format("docker-%s", deviceId));
		MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
		mqttConnectOptions.setUserName(String.format("%s@%s", deviceId, tenantId));
		mqttConnectOptions.setPassword(password.toCharArray());
		client.connect(mqttConnectOptions);
		client.setCallback(this);

		client.subscribe("command///req/#", 1);

		scheduler.scheduleAtFixedRate(() -> {
			try {
				String payload = buildCommandPayload();
				log.info("send payload {}", payload);
				client.publish("t", payload.getBytes(), 1, false);
			} catch (MqttException e) {
				log.error(e.getMessage());
			}
		}, 2000, 500, TimeUnit.MILLISECONDS);
	}

	@Override
	public void connectionLost(Throwable throwable) {
		log.info("connectionLost");
	}

	@Override
	public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
		log.info("messageArrived {}", topic);
		log.info("payload {}", new String(mqttMessage.getPayload()));
		var splits = topic.split("/");
		var requestId = splits[4];
		var responseTopic = String.format("command/%s/%s/res/%s/200", tenantId, deviceId, requestId);
		client.publish(responseTopic, "{\"status\": \"ok\"}".getBytes(), 1, false);
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

	}

	private String buildCommandPayload() {
		return String.format("{\"random\": %d}", (int) (Math.random() * 100));
	}
}
