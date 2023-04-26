export const BASE_URL = 'http://localhost:8080/api/v1';

export const CUSTOMER = {
    CREATE_CUSTOMER: BASE_URL + '/create-customer',
    GET_CUSTOMER_DATA: BASE_URL + '/customer-data',
    LOGIN:  BASE_URL + '/login'
};

export const CURRENCY = {
    GET_ALL_CURRENCY: BASE_URL + '/currency/all',
    GET_CURRENCY_DATA: (currencyCode: string): string => BASE_URL + `/currency/${currencyCode}`,
};

export const OPERATION = {
    ADD_OPERATION: BASE_URL + '/account/add-operation',
    GET_OPERATIONS: (account: string): string => BASE_URL + `/${account}/operations`,
    ADD_TRANSACTION: BASE_URL + '/account/add-transaction'
};

export const ACCOUNT = {
    GET_ALL_ACCOUNTS: (customerId: number | string): string => BASE_URL + `/${customerId}/accounts`,
    GET_ACCOUNT_DATA: (accountName: string): string => BASE_URL + `/${accountName}/account-data`,
    CREATE_ACCOUNT: BASE_URL + '/create-account',
    DELETE_ACCOUNT: (accountName: string): string => BASE_URL + `/delete-account/${accountName}`
};