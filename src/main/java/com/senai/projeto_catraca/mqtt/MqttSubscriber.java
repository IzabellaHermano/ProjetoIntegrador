package com.senai.projeto_catraca.mqtt;

import com.senai.projeto_catraca.controller.OcorrenciaController;
import com.senai.projeto_catraca.websocket.WebSocketSender;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MqttSubscriber {
    private static final String BROKER = "tcp://localhost:1883";
    private static final String CLIENT_ID = "ServidorJava";
    private static final String TOPICO_ENTRADA = "catraca/rfid/entrada";
    private static final String TOPICO_SAIDA = "catraca/rfid/saida";
    private static final String TOPICO_STATUS = "catraca/status";

    private static final OcorrenciaController controller = new OcorrenciaController();
    private static MqttClient client;
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void iniciarMqtt() {
        try {
            // Configurar opções de conexão
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            options.setKeepAliveInterval(20);
            options.setAutomaticReconnect(true);

            // Criar cliente MQTT
            client = new MqttClient(BROKER, CLIENT_ID, new MemoryPersistence());

            // Configurar callback para eventos
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.err.println("Conexão MQTT perdida: " + cause.getMessage());
                    WebSocketSender.enviarMensagem("[MQTT] Conexão perdida - tentando reconectar...");
                    tentarReconectar();
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    String payload = new String(message.getPayload());
                    System.out.println("Mensagem recebida no tópico '" + topic + "': " + payload);
                    processarMensagem(topic, payload);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Não usado para subscriber
                }
            });

            // Conectar
            client.connect(options);

            // Inscrever nos tópicos
            client.subscribe(TOPICO_ENTRADA, 1);
            client.subscribe(TOPICO_SAIDA, 1);
            client.subscribe(TOPICO_STATUS, 0);

            System.out.println("MQTT Subscriber iniciado com sucesso!");
            System.out.println("Inscrito nos tópicos:");
            System.out.println("- " + TOPICO_ENTRADA);
            System.out.println("- " + TOPICO_SAIDA);
            System.out.println("- " + TOPICO_STATUS);

            // Enviar status inicial
            WebSocketSender.enviarMensagem("[MQTT] Servidor conectado e monitorando catracas");

        } catch (MqttException e) {
            System.err.println("Erro ao iniciar MQTT: " + e.getMessage());
            WebSocketSender.enviarMensagem("[ERRO MQTT] Falha na conexão: " + e.getMessage());
        }
    }

    private static void processarMensagem(String topic, String payload) {
        try {
            switch (topic) {
                case TOPICO_ENTRADA -> {
                    String resposta = controller.gerarOcorrenciaAtraso(payload.trim());
                    System.out.println("[ENTRADA] " + resposta);

                    // Log adicional no WebSocket
                    WebSocketSender.enviarMensagem("[RFID ENTRADA] " + payload + " - " + resposta);
                }

                case TOPICO_SAIDA -> {
                    String resposta = controller.gerarOcorrenciaSaida(payload.trim());
                    System.out.println("[SAÍDA] " + resposta);

                    // Log adicional no WebSocket
                    WebSocketSender.enviarMensagem("[RFID SAÍDA] " + payload + " - " + resposta);
                }

                case TOPICO_STATUS -> {
                    processarStatusCatraca(payload.trim());
                }

                default -> {
                    System.out.println("[MQTT] Tópico não reconhecido: " + topic);
                }
            }
        } catch (Exception e) {
            String mensagemErro = "[ERRO] Falha ao processar mensagem MQTT: " + e.getMessage();
            System.err.println(mensagemErro);
            WebSocketSender.enviarMensagem(mensagemErro);
        }
    }

    private static void processarStatusCatraca(String status) {
        String mensagem;

        switch (status.toLowerCase()) {
            case "online" -> mensagem = "[STATUS] Catraca online e operacional";
            case "offline" -> mensagem = "[ALERTA] Catraca offline - verificar conexão";
            case "erro" -> mensagem = "[ERRO] Catraca reportou erro - verificar hardware";
            case "manutencao" -> mensagem = "[INFO] Catraca em modo manutenção";
            default -> mensagem = "[STATUS] Catraca reportou: " + status;
        }

        System.out.println(mensagem);
        WebSocketSender.enviarMensagem(mensagem);
    }

    private static void tentarReconectar() {
        scheduler.schedule(() -> {
            try {
                if (client != null && !client.isConnected()) {
                    System.out.println("Tentando reconectar ao MQTT...");
                    client.reconnect();

                    // Re-inscrever nos tópicos após reconexão
                    client.subscribe(TOPICO_ENTRADA, 1);
                    client.subscribe(TOPICO_SAIDA, 1);
                    client.subscribe(TOPICO_STATUS, 0);

                    System.out.println("Reconectado ao MQTT com sucesso!");
                    WebSocketSender.enviarMensagem("[MQTT] Reconectado com sucesso");
                }
            } catch (MqttException e) {
                System.err.println("Falha na reconexão: " + e.getMessage());
                // Tentar novamente em 30 segundos
                tentarReconectar();
            }
        }, 30, TimeUnit.SECONDS);
    }

    public static void publicarMensagem(String topico, String mensagem) {
        try {
            if (client != null && client.isConnected()) {
                MqttMessage mqttMessage = new MqttMessage(mensagem.getBytes());
                mqttMessage.setQos(1);
                client.publish(topico, mqttMessage);
                System.out.println("Mensagem publicada no tópico '" + topico + "': " + mensagem);
            } else {
                System.err.println("Cliente MQTT não conectado - não é possível publicar mensagem");
            }
        } catch (MqttException e) {
            System.err.println("Erro ao publicar mensagem MQTT: " + e.getMessage());
        }
    }

    public static void pararMqtt() {
        try {
            if (client != null && client.isConnected()) {
                client.unsubscribe(TOPICO_ENTRADA);
                client.unsubscribe(TOPICO_SAIDA);
                client.unsubscribe(TOPICO_STATUS);
                client.disconnect();
                client.close();
                System.out.println("Cliente MQTT desconectado");
            }

            scheduler.shutdown();

        } catch (MqttException e) {
            System.err.println("Erro ao parar cliente MQTT: " + e.getMessage());
        }
    }

    public static boolean isConectado() {
        return client != null && client.isConnected();
    }
}