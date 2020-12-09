#resource "azurerm_app_service_plan" "function_app_service_plan" {
#    name = "tc-eda-iac-${var.environment}-function-app-service-plan"
#    location = azurerm_resource_group.resourceGroup.location
#    resource_group_name = azurerm_resource_group.resourceGroup.name
#    kind = "Windows"
#
#    sku {
#        tier = "Free"
#        size = "F1"
#    }
#}

resource "azurerm_function_app" "order_function_producer" {
    name = "tc-eda-iac-${var.environment}-function-order-producer"
    location = azurerm_resource_group.resourceGroup.location
    resource_group_name = azurerm_resource_group.resourceGroup.name
    app_service_plan_id = azurerm_app_service_plan.function_app_service_plan.id
    storage_account_name = azurerm_storage_account.storage_account.name
    storage_account_access_key = azurerm_storage_account.storage_account.primary_access_key
    version = "~3"

    identity {
        type = "SystemAssigned"
    }

    site_config {
      linux_fx_version = "JAVA|11"
    }

    app_settings = {
        EVENT_HUB_NAME = azurerm_eventhub.eventhub_order_placed.name
        CONNECTION_STRING = "@Microsoft.KeyVault(SecretUri=${azurerm_key_vault_secret.eventhub_order_placed_sas_connectionstring.id})"
        FUNCTIONS_WORKER_RUNTIME = "java"
    }
}

resource "azurerm_function_app" "order_function_consumer" {
    name = "tc-eda-iac-${var.environment}-function-order-consumer"
    location = azurerm_resource_group.resourceGroup.location
    resource_group_name = azurerm_resource_group.resourceGroup.name
    app_service_plan_id = azurerm_app_service_plan.function_app_service_plan.id
    storage_account_name = azurerm_storage_account.storage_account.name
    storage_account_access_key = azurerm_storage_account.storage_account.primary_access_key
    version = "~3"

    identity {
        type = "SystemAssigned"
    }

    site_config {
      linux_fx_version = "JAVA|11"
    }

    app_settings = {
        EVENT_HUB_NAME = azurerm_eventhub.eventhub_order_placed.name
        CONNECTION_STRING = "@Microsoft.KeyVault(SecretUri=${azurerm_key_vault_secret.eventhub_order_placed_sas_connectionstring.id})"
        FUNCTIONS_WORKER_RUNTIME = "java"
    }
}