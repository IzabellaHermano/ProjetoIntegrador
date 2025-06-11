package com.senai.projeto_catraca.view;

import com.senai.projeto_catraca.mqtt.MqttSubscriber;
import com.senai.projeto_catraca.websocket.WebSocketSender;
import com.senai.projeto_catraca.websocket.WebSocketClienteConsole;
import com.senai.projeto_catraca.view.OcorrenciaView;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class MainApplication {
    private static final CountDownLatch shutdownLatch = new CountDownLatch(1);
    private static boolean servicosIniciados = false;

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE CONTROLE DE CATRACA SENAI ===");
        System.out.println("Iniciando serviços...\n");

        // Configurar shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nFinalizando serviços...");
            pararServicos();
        }));

        // Mostrar menu principal
        mostrarMenu();
    }

    private static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        String opcao;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Iniciar Todos os Serviços");
            System.out.println("2. Parar Todos os Serviços");
            System.out.println("3. Status dos Serviços");
            System.out.println("4. Gerenciar Ocorrências");
            System.out.println("5. Teste de Conectividade");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> iniciarServicos();
                case "2" -> pararServicos();
                case "3" -> mostrarStatus();
                case "4" -> gerenciarOcorrencias();
                case "5" -> testarConectividade();
                case "0" -> {
                    System.out.println("Finalizando aplicação...");
                    pararServicos();
                }
                default -> System.out.println("Opção inválida!");
            }

        } while (!opcao.equals("0"));
    }

    private static void iniciarServicos() {
        if (servicosIniciados) {
            System.out.println("Serviços já estão em execução!");
            return;
        }

        System.out.println("Iniciando serviços do sistema...");

        try {
            // 1. Iniciar WebSocket Server
            System.out.print("Iniciando WebSocket Server... ");
            WebSocketSender.iniciarWebSocket();
            System.out.println("✓ WebSocket Server iniciado");

            // Aguardar um pouco para o servidor inicializar
            Thread.sleep(2000);

            // 2. Iniciar cliente WebSocket (para monitoramento)
            System.out.print("Conectando cliente WebSocket... ");
            WebSocketClienteConsole.conectar();
            System.out.println("✓ Cliente WebSocket conectado");

            // 3. Iniciar MQTT Subscriber
            System.out.print("Iniciando MQTT Subscriber... ");
            MqttSubscriber.iniciarMqtt();
            System.out.println("✓ MQTT Subscriber iniciado");

            servicosIniciados = true;
            System.out.println("\n✅ Todos os serviços foram iniciados com sucesso!");
            System.out.println("O sistema está pronto para receber dados das catracas.");

        } catch (Exception e) {
            System.err.println("❌ Erro ao iniciar serviços: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void pararServicos() {
        if (!servicosIniciados) {
            System.out.println("Serviços não estão em execução.");
            return;
        }

        System.out.println("Parando serviços...");

        try {
            // Parar MQTT
            System.out.print("Parando MQTT Subscriber... ");
            MqttSubscriber.pararMqtt();
            System.out.println("✓");

            // Desconectar WebSocket Cliente
            System.out.print("Desconectando WebSocket Cliente... ");
            WebSocketClienteConsole.desconectar();
            System.out.println("✓");

            servicosIniciados = false;
            System.out.println("✅ Serviços parados com sucesso!");

        } catch (Exception e) {
            System.err.println("❌ Erro ao parar serviços: " + e.getMessage());
        }
    }

    private static void mostrarStatus() {
        System.out.println("\n=== STATUS DOS SERVIÇOS ===");

        // Status do MQTT
        if (MqttSubscriber.isConectado()) {
            System.out.println("MQTT Subscriber: ✅ Conectado");
        } else {
            System.out.println("MQTT Subscriber: ❌ Desconectado");
        }

        // Status geral
        if (servicosIniciados) {
            System.out.println("WebSocket Server: ✅ Ativo");
            System.out.println("Sistema: ✅ Operacional");
        } else {
            System.out.println("WebSocket Server: ❌ Inativo");
            System.out.println("Sistema: ❌ Parado");
        }

        System.out.println("\n📊 Informações de Conexão:");
        System.out.println("- WebSocket Server: ws://localhost:8080/ws");
        System.out.println("- MQTT Broker: tcp://localhost:1883");
        System.out.println("- Tópicos MQTT:");
        System.out.println("  • catraca/rfid/entrada");
        System.out.println("  • catraca/rfid/saida");
        System.out.println("  • catraca/status");
    }

    private static void gerenciarOcorrencias() {
        System.out.println("\nAbrindo gerenciador de ocorrências...");

        // Criar nova thread para não bloquear o menu principal
        Thread ocorrenciaThread = new Thread(() -> {
            OcorrenciaView view = new OcorrenciaView();
            view.main(new String[0]);
        });

        ocorrenciaThread.start();

        try {
            ocorrenciaThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void testarConectividade() {
        System.out.println("\n=== TESTE DE CONECTIVIDADE ===");

        // Teste MQTT
        System.out.print("Testando MQTT... ");
        if (MqttSubscriber.isConectado()) {
            System.out.println("✅ OK");

            // Enviar mensagem de teste
            MqttSubscriber.publicarMensagem("catraca/status", "teste_conectividade");

        } else {
            System.out.println("❌ Falha");
        }

        // Teste WebSocket
        System.out.print("Testando WebSocket... ");
        try {
            WebSocketSender.enviarMensagem("[TESTE] Teste de conectividade - " +
                    java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")));
            System.out.println("✅ OK");
        } catch (Exception e) {
            System.out.println("❌ Falha: " + e.getMessage());
        }

        System.out.println("\n💡 Dica: Verifique o console para ver as mensagens de teste.");
    }

    // Método para simular entrada de RFID (útil para testes)
    public static void simularEntradaRFID(String rfid) {
        if (servicosIniciados) {
            MqttSubscriber.publicarMensagem("catraca/rfid/entrada", rfid);
        } else {
            System.out.println("❌ Serviços não estão iniciados!");
        }
    }

    // Método para simular saída de RFID (útil para testes)
    public static void simularSaidaRFID(String rfid) {
        if (servicosIniciados) {
            MqttSubscriber.publicarMensagem("catraca/rfid/saida", rfid);
        } else {
            System.out.println("❌ Serviços não estão iniciados!");
        }
    }
}