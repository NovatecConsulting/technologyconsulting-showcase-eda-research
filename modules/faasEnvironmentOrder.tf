#The function app service plan has already been created and will be reused.
#But as you need the terraform resource identifier, we'll show the creation here again: 

# resource "azurerm_app_service_plan" "function_app_service_plan" {
#     name = "tc-eda-iac-${var.environment}-function-app-service-plan"
#     location = azurerm_resource_group.resourceGroup.location
#     resource_group_name = azurerm_resource_group.resourceGroup.name
#     kind = "Windows"

#     sku {
#         tier = "Free"
#         size = "F1"
#     }
# }


# Function Environment for function order producer

resource "azurerm_function_app" /*###########*/ {
    name = /*###########*/
    location = azurerm_resource_group.resourceGroup.location
    resource_group_name = azurerm_resource_group.resourceGroup.name
    app_service_plan_id = /*###########*/
    storage_account_name = /*###########*/
    storage_account_access_key = /*###########*/
    version = "~3"

    # A function needs an identity to allow access to Key Vault
    identity {
        type = "SystemAssigned"
    }

    # App Settings represents both settings of the function environment (like function runtime java)
    # but also sets ENVIRONMENT VARIABLES for the function to use
    app_settings = {
        /*###########*/
        /*###########*/
        FUNCTIONS_WORKER_RUNTIME = "java"
    }
}

# Function Environment for function order consumer

/*###########*/
/*###########*/
/*###########*/
/*###########*/
/*###########*/
/*###########*/
/*###########*/
/*###########*/
