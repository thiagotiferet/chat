package Chat;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Usuario {
    
  private static String QUEUE_NAME = "";
  
  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
	/*
		nome_do_host, usernamee e password
		essas informações são unicas para cada conta do RabbitMQ	
	*/
    factory.setHost("nome_do_host");
    factory.setUsername("username");
    factory.setPassword("password");
    factory.setVirtualHost("username");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
 
    boolean parada = true;
    String data_formato = "dd/MM/yyyy";
    String hora_formato = "HH:mm";
    String data, hora, agora;
    SimpleDateFormat formata;
    
    Mensagem enviar;
    Gson gson = new Gson();
    GerenciaTextoDigitado gerenciador = new GerenciaTextoDigitado();
    
    System.out.print("USER: ");
    Scanner sca = new Scanner(System.in);
    QUEUE_NAME = sca.nextLine();
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println("USER: " + QUEUE_NAME + "");
    
    while(parada){
        
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
            throws IOException {
                Mensagem recebida = gson.fromJson(new String(body, "UTF-8"), Mensagem.class);
                System.out.println();
                System.out.println("("+recebida.time+") "+ recebida.send+" "+"diz: " + recebida.content);
                System.out.print(gerenciador.destinatario + ">> ");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
        
        System.out.print(gerenciador.destinatario + ">> ");
        Scanner sc = new Scanner(System.in);
        String texto = sc.nextLine();
        
        java.util.Date hoje = new java.util.Date();;
        formata = new SimpleDateFormat(data_formato);
        data = formata.format(hoje);
        formata = new SimpleDateFormat(hora_formato);
        hora = formata.format(hoje);
        agora = data+" às "+hora;
        
        gerenciador.gerenciaTexto(texto);
        switch(gerenciador.escolhido){
            case 1:
                    channel.exchangeDeclare(gerenciador.exchange_name, "fanout");
                    channel.queueBind(QUEUE_NAME, gerenciador.exchange_name, "");
                    break;
            case 2:
                    channel.queueBind(gerenciador.temporario, gerenciador.exchange_name, "");
                    break;
            case 3:
                    channel.queueUnbind(gerenciador.temporario, gerenciador.exchange_name, "");
                    break;
            case 4:
                    channel.exchangeDelete(gerenciador.exchange_name);
                    break;
            case 5:
                    String temp = gerenciador.queue_name_second + "/" + QUEUE_NAME;
                    enviar = new Mensagem(temp,agora,texto);
                    channel.basicPublish(gerenciador.queue_name_second,"", null, gson.toJson(enviar).getBytes("UTF-8"));
                   break;
            case 6:
                    enviar = new Mensagem(QUEUE_NAME,agora,texto);
                    channel.basicPublish("", gerenciador.queue_name_second, null, gson.toJson(enviar).getBytes("UTF-8"));
                    break;
            case 7:
                    System.out.println("Comando invalido!");
                    break;
            default:
                    break;
        }           
    }
    channel.close();
    connection.close();
  }
}