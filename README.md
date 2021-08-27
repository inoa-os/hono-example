# Hono Test Project

This project contains 2 projects a mqtt client, and a cloud service which connects to kafka.
To connect to our Kafka cluster we use telepresence. To install it you can have a look here: https://www.telepresence.io/docs/latest/howtos/intercepts/ .

run 
```bash
telepresence connect
```

to connect to the cluster.

for the mqtt client app you have to add the following env variables:

```
MQTT_HOST=
HONO_GATEWAY_ID=
HONO_TENANT=
HONO_GATEWAY_PASSWORD=
```

after that you can start  both application, and you will see something like:

for the client:
```
12:04:03.201 [MQTT Call: test] INFO  i.k.f.registry.hono.HonoMqttClient - messageArrived command///req/22405199907-889b-40bf-839d-a1fb3962827f/setBrightness
12:04:03.201 [MQTT Call: test] INFO  i.k.f.registry.hono.HonoMqttClient - payload {
  "brightness" : 18
}
```

for the cloud service:
```
11:58:56.247 [vert.x-eventloop-thread-0] DEBUG i.k.f.r.h.HonoExampleApplicationBase - Sending command [setBrightness] to [f9bdfa0b-5591-4ac7-ba65-7d4cbb3bd28e/8b1d7df8-1365-4612-94c4-74c6cbb5a005].
11:58:56.375 [vert.x-eventloop-thread-0] DEBUG i.k.f.r.h.HonoExampleApplicationBase - Successfully sent command payload: [{
  "brightness" : 18
}].
```
