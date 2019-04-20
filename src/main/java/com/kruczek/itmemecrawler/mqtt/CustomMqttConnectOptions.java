package com.kruczek.itmemecrawler.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomMqttConnectOptions extends MqttConnectOptions {

    private String login;
    private String pass;

    public CustomMqttConnectOptions(@Value("${mqtt.login}") String login, @Value("${mqtt.pass}") String pass) {
        super();

        this.login = login;
        this.pass = pass;

        setCleanSession(true);
        setAutomaticReconnect(true);
        setConnectionTimeout(10);
        setUserName(login);
        setPassword(pass.toCharArray());
    }
}
