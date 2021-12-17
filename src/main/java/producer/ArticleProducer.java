package producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ArticleProducer {
    private final static String QUEUE_NAME = "article_Q";
    private final static String EXCHANGER_NAME = "article_exchanger";
    private static ArrayList<String> topics = new ArrayList();
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            String command = "";
            channel.exchangeDeclare(EXCHANGER_NAME, BuiltinExchangeType.DIRECT);
            while(true){
                BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
                command = br.readLine();
                if(command.equals("exit")){
                    break;
                }
                String[] commands = command.split(" ");
                String topicName = commands[0];
                String message = commands[1];
                if(!topics.contains(topicName)){
                    channel.queueDeclare(topicName + "_queue", true, false, false, null);
                    channel.queueBind(topicName + "_queue", EXCHANGER_NAME, topicName);
                    topics.add(topicName);
                }
                channel.basicPublish(EXCHANGER_NAME, topicName,null, message.getBytes());
            }
        }

    }
}