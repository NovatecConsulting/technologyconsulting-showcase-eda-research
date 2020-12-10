#The event hub namespace has already been created and will be reused.
#But as you need the terraform resource identifier, we'll show the creation here again: 

#resource "azurerm_eventhub_namespace" "eventhub_namespace" {
#    name = "tc-eda-iac-${var.environment}-eventhub"
#    location = azurerm_resource_group.resourceGroup.location
#    resource_group_name = azurerm_resource_group.resourceGroup.name
#    sku = "Standard"
#    capacity = 1
#}

resource "azurerm_eventhub" /*###########*/ {
    name = /*###########*/
    namespace_name = /*###########*/
    resource_group_name = azurerm_resource_group.resourceGroup.name
    partition_count = /*###########*/
    message_retention = /*###########*/

    capture_description {
      enabled = true
      encoding = "Avro"

        destination {
            name = "EventHubArchive.AzureBlockBlob"
            archive_name_format = "{Namespace}/{EventHub}/{PartitionId}/{Year}-{Month}-{Day}T{Hour}:{Minute}:{Second}"
            blob_container_name = azurerm_storage_container.storage_container_eventhub.name
            storage_account_id  = azurerm_storage_account.storage_account.id
        }
    }
}

resource "azurerm_eventhub_authorization_rule" /*###########*/ {
    name = /*###########*/
    namespace_name = /*###########*/
    eventhub_name = /*###########*/
    resource_group_name = azurerm_resource_group.resourceGroup.name
    listen = /*###########*/
    send = /*###########*/ 
    manage = /*###########*/
}

resource "azurerm_key_vault_secret" /*###########*/ {
    name = /*###########*/
    #We want to save the "primary connection string" in the key vault here
    value = /*###########*/
    key_vault_id = azurerm_key_vault.key_vault.id
}