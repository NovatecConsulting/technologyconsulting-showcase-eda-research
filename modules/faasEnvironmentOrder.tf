#The function app service plan has already been created and will be reused.
#But as you need the terraform resource identifier, we'll show the creation here again: 

# resource "azurerm_app_service_plan" "function_app_service_plan" {
#     name = "tc-eda-iac-${var.environment}-function-app-service-plan"
#     location = azurerm_resource_group.resourceGroup.location
#     resource_group_name = azurerm_resource_group.resourceGroup.name
#     kind = "Windows"

#     sku {
#         tier = "Shared"
#         size = "D1"
#     }
# }


# Function Environment for function order producer

resource "azurerm_function_app" "order_placed_producer" {
    name = "${var.environment}-order-placed-producer"
    location = azurerm_resource_group.resourceGroup.location
    resource_group_name = azurerm_resource_group.resourceGroup.name
    app_service_plan_id = azurerm_app_service_plan.function_app_service_plan.id
    storage_account_name = azurerm_storage_account.storage_account.name
    storage_account_access_key = azurerm_storage_account.storage_account.primary_access_key
    version = "~3"

    # A function needs an identity to allow access to Key Vault
    identity {
        type = "SystemAssigned"
    }

    # App Settings represents both settings of the function environment (like function runtime java)
    # but also sets ENVIRONMENT VARIABLES for the function to use
    app_settings = {
        # TODO 
        EVENT_HUB_NAME = azurerm_eventhub.eventhub_order_placed.name
        CONNECTION_STRING = "@Microsoft.KeyVault(SecretUri=${azurerm_key_vault_secret.eventhub_order_placed_sas_connectionstring.id})"
        FUNCTIONS_WORKER_RUNTIME = "java"
    }
}

# Function Environment for function order consumer

resource "azurerm_function_app" "order_placed_consumer" {
    name = "${var.environment}-order-placed-consumer"
    location = azurerm_resource_group.resourceGroup.location
    resource_group_name = azurerm_resource_group.resourceGroup.name
    app_service_plan_id = azurerm_app_service_plan.function_app_service_plan.id
    storage_account_name = azurerm_storage_account.storage_account.name
    storage_account_access_key = azurerm_storage_account.storage_account.primary_access_key
    version = "~3"

    # A function needs an identity to allow access to Key Vault
    identity {
        type = "SystemAssigned"
    }

    # App Settings represents both settings of the function environment (like function runtime java)
    # but also sets ENVIRONMENT VARIABLES for the function to use
    app_settings = {
        # TODO 
        EVENT_HUB_NAME = azurerm_eventhub.eventhub_order_placed.name
        CONNECTION_STRING = "@Microsoft.KeyVault(SecretUri=${azurerm_key_vault_secret.eventhub_order_placed_sas_connectionstring.id})"
        FUNCTIONS_WORKER_RUNTIME = "java"
    }
}
