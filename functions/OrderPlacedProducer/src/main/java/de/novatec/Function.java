package de.novatec;

import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventDataBatch;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it
     * using "curl" command in bash: 1. curl -d "HTTP Body" {your
     * host}/api/HttpExample 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     * 
     * @throws JsonProcessingException
     */
    @FunctionName("HttpExample")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {
                    HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Order> request,
            final ExecutionContext context) throws JsonProcessingException {

        context.getLogger().info("OrderPlacedProducer received POST " + request.getBody().toString());

        EventHubProducerClient producer = new EventHubClientBuilder()
                .connectionString(System.getenv("CONNECTION_STRING"), System.getenv("EVENT_HUB_NAME"))
                .buildProducerClient();

        Order order = request.getBody();

        EventDataBatch batch = producer.createBatch();
        ObjectMapper objectMapper = new ObjectMapper();
        batch.tryAdd(new EventData(objectMapper.writeValueAsString(order)));
        
        producer.send(batch);
        producer.close();

        return request.createResponseBuilder(HttpStatus.OK).build();
    }
}
