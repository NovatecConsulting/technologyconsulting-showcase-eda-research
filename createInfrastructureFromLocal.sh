#!/bin/bash
set -xe
VAULT_NAME="tc-showcase-common-vault"
TF_VAR_storage_account_name="tcshowcasecommonstorage"
TF_VAR_subscription_name="Practice-Area-TC-Dev"
TF_VAR_environment="dev"

#get the ARM connections details   
ARM_SUBSCRIPTION_ID=$(az keyvault secret show --vault-name "$VAULT_NAME" --name "ARMSUBSCRIPTIONID" --query value  -o tsv)
ARM_CLIENT_ID=$(az keyvault secret show --vault-name "$VAULT_NAME" --name "ARMCLIENTID" --query value  -o tsv)
ARM_CLIENT_SECRET=$(az keyvault secret show --vault-name "$VAULT_NAME" --name 'ARMCLIENTSECRET' --query value  -o tsv)
ARM_TENANT_ID=$(az keyvault secret show --vault-name "$VAULT_NAME" --name 'ARMTENANTID' --query value  -o tsv)
STATE_BLOBACCESSKEY=$(az keyvault secret show --vault-name "$VAULT_NAME" --name 'tfstateblobaccesskey' --query value  -o tsv)

export ARM_SUBSCRIPTION_ID
export ARM_CLIENT_ID
export ARM_CLIENT_SECRET
export ARM_TENANT_ID
export STATE_BLOBACCESSKEY
export TF_VAR_storage_account_name
export TF_VAR_subscription_name
export TF_VAR_environment

export TF_ACTION="plan" # plan/apply/destroy

./createInfrastructure.sh

rm -rf .terraform
