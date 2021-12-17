package consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class ArticleConsumerA {

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);
        //channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" Enter the topic you wish to subscribe!");
        System.out.println(" Enter: ");
        System.out.println("'1' - to subscribe for Java topic!");
        System.out.println("'2' - to subscribe for C# topic!");
        System.out.println("'3' - to subscribe for JavaScript topic!");
        System.out.println("'4' - to subscribe for PHP topic!");
        System.out.println("or just enter the name of the topic you wish to subscribe ");
        System.out.println("Type it rapidly in case some subscribes are already processing! ");

        String command = "";
        while(true){
            if(command.equals("exit")){
                break;
            }
            BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
            command = br.readLine();
            String[] commands = command.split(" ");
            String operation = commands[0]; //'+' - for subscribe, '-' for unsubscribe
            String topicName = commands[1];
            String queueName = "";
            String consumerTag = "";
            switch(topicName){
                case ("1"):
                    queueName = "java_queue";
                    break;
                case("2"):
                    queueName = "C#_queue";

                    break;
                case("3"):
                    queueName = "javascript_queue";
                    break;
                case("4"):
                    queueName = "php_queue";
                    break;
                default:
                    queueName = topicName;
            }
            DeliverCallback deliverCallback = (tag, delivery)->{
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                doWork(message);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            };
            if(operation.equals("+")){
                channel.basicConsume(queueName, false, queueName + "ConsumerTag", deliverCallback,tag -> {});
                System.out.println(consumerTag);
            }
            if(operation.equals("-")){
                channel.basicCancel(queueName + "ConsumerTag");
            }




            //channel.basicConsume(topicName + "_queue", false, deliverCallback,consumerTag -> {});

        }
    }
    private static class QueueListener extends Thread{
        String queueName;
        Channel channel;
        DeliverCallback deliverCallback;
        QueueListener(Channel channel, String queueName, DeliverCallback deliverCallback){
            this.queueName = queueName;
            this.channel = channel;
            this.deliverCallback = deliverCallback;
        }
        public void run() {
            try {
                channel.basicConsume(queueName + "_queue", false, deliverCallback,consumerTag -> {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static void doWork(String task) {
        try {
            System.out.println(task);
            Thread.sleep(5000);
        } catch (InterruptedException _ignored) {
            Thread.currentThread().interrupt();
        }
    }
}
