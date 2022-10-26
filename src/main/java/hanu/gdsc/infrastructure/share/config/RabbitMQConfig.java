package hanu.gdsc.infrastructure.share.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RabbitMQConfig {

    @Bean
    public List<Queue> declareQueues() {
        return List.of(new Queue(hanu.gdsc.domain.share.models.Queue.SUBMISSIONEVENTQUEUE.name()),
                new Queue(hanu.gdsc.domain.share.models.Queue.PROBLEMQUEUE.name()));
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(hanu.gdsc.domain.share.models.Exchange.DIRECTEXCHANGE.name());
    }

    @Bean
    public List<Binding> binding(List<Queue> queues, DirectExchange exchange) {
        List<Binding> bindings = new ArrayList<>();
        queues.forEach(queue -> {
            if(queue.getName().equals(hanu.gdsc.domain.share.models.Queue.SUBMISSIONEVENTQUEUE.name())) {
                bindings.add(BindingBuilder.bind(queue).to(exchange).with(hanu.gdsc.domain.share.models.RoutingKey.SUBMISSIONEVENTKEY));
            } else if(queue.getName().equals(hanu.gdsc.domain.share.models.Queue.PROBLEMQUEUE.name())) {
                bindings.add(BindingBuilder.bind(queue).to(exchange).with(hanu.gdsc.domain.share.models.RoutingKey.PROBLEMKEY));
            }
        });
        return bindings;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}