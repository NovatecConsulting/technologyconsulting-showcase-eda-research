package de.novatec;

import java.util.logging.Logger;

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
                connection = "",
                cardinality = Cardinality.ONE) Order order,
            final ExecutionContext context) {

        Logger log = context.getLogger();

        // Call CustomerContainer at 
        // "http://tc-eda-iac-" + <insert_env_name_here> + "-customer-consumer-container.westeurope.azurecontainer.io/customers/{customerId}"
        // ...


        // Log customer + order with log.info()
    }
}
