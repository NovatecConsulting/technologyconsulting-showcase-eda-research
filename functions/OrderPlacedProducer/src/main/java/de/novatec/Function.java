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
     * This function listens at endpoint "/api/OrderPlacedProducer". Two ways to invoke it
     * using "curl" command in bash: 1. curl -d "HTTP Body" {your
     * host}/api/OrderPlacedProducer 2. curl "{your host}/api/OrderPlacedProducer?name=HTTP%20Query"
     * 
     * @throws JsonProcessingException
     */
    @FunctionName("OrderPlacedProducer")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {
                    HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Order> request,
            final ExecutionContext context) throws JsonProcessingException {

        // Retreive the order from the request body of the HttpTrigger
        Order order = request.getBody();
        context.getLogger().info("OrderPlacedProducer received POST " + order.toString());

        // Instantiate EventHubProducerClient with CONNECTION_STRING and EVENT_HUB_NAME



        // Use EventHubProducerClient.createBatch() to prepare a batch of events
        // Hint: Use ObjectMapper to turn the Order into a JSON String



        // Gracefully shutdown the EventHubProducerClient by calling close()



        return request.createResponseBuilder(HttpStatus.OK).build();
    }
}
