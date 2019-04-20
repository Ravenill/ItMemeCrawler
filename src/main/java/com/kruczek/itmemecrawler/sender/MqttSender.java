package com.kruczek.itmemecrawler.sender;

import com.kruczek.itmemecrawler.mqtt.CustomMqttConnectOptions;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqttSender implements Sender {

    private static final String CLIENT_ID = "MemeIT";

    @Value("${mqtt.topic}")
    private String topic;

    @Value("${mqtt.broker.url}")
    private String broker;

    private MqttConnectOptions connectionOptions;

    public MqttSender(CustomMqttConnectOptions connectionOptions) {
        this.connectionOptions = connectionOptions;
    }

    @Override
    public void send(String message) {
        try {
            //TODO: Make connect and disconnect once per sending
            MqttClient sampleClient = new MqttClient(broker, CLIENT_ID);
            connectToBroker(sampleClient);

            log.debug("Publishing message: " + message);
            MqttMessage mqttMessage = buildMqttMessage(message);
            sampleClient.publish(topic, mqttMessage);
            log.debug("Message published");

            disconnect(sampleClient);
        } catch (MqttException exception) {
            logMqttException(exception);
            exception.printStackTrace();
        }
    }

    private void connectToBroker(MqttClient sampleClient) throws MqttException {
        log.debug("Connecting to broker: " + broker);
        sampleClient.connect(connectionOptions);
        log.debug("Connected");
    }

    private MqttMessage buildMqttMessage(String message) {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttMessage.setQos(2);
        return mqttMessage;
    }

    private void disconnect(MqttClient sampleClient) throws MqttException {
        sampleClient.disconnect();
        log.debug("Disconnected");
    }

    private void logMqttException(MqttException exception) {
        log.warn("Reason: " + exception.getReasonCode());
        log.warn("Message: " + exception.getMessage());
        log.warn("Localized location: " + exception.getLocalizedMessage());
        log.warn("Cause: " + exception.getCause());
        log.warn("Exception: " + exception);
    }
}
