package com.senai.projeto_catraca.mqtt;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.senai.projeto_catraca.controller.OcorrenciaController;
public class MqttSubscriber {
    private static final String BROKER = "tcp://localhost:1883";
    private static final String CLIENT_ID = "ServidorJava";
    private static final String TOPICO = "catraca/rfid";
    private static final OcorrenciaController controller = new OcorrenciaController();

    public static void iniciarMqtt() {
        try {
            MqttClient client = new MqttClient(BROKER, CLIENT_ID);
            client.connect();
            client.subscribe(TOPICO, (topic, msg) -> {
                String payload = new String(msg.getPayload());
            });
            System.out.println("Inscrito no tópico MQTT: " + TOPICO);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}