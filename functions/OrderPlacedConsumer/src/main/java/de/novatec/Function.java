package de.novatec;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.Cardinality;
import com.microsoft.azure.functions.annotation.EventHubTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;

public class Function {
    @FunctionName("OrderPlacedConsumer")
    public void run(
            @EventHubTrigger(
                name = "order",
                consumerGroup = "$Default",
                eventHubName = "",
                connection = "CONNECTION_STRING",
                cardinality = Cardinality.ONE) Order order,
            final ExecutionContext context) {

        context.getLogger().info("OrderPlacedConsumer received event from event hub " + order.toString());
    }
}
