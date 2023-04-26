import React from 'react';
import axios from 'axios';
import { ACCOUNT } from '../../api/Endpoints'
import { AUTH } from '../../api/Auth'
import { useParams } from 'react-router-dom';

class AccountService{
    
    getAccountData(accountName: string) {
        return axios.get(ACCOUNT.GET_ACCOUNT_DATA(accountName),
            {
                auth: AUTH
            }
        )
    }

    createAccount(customerId: number, currencyCode: string, description: string) {
        return axios.post(ACCOUNT.CREATE_ACCOUNT,
            {
                customerId: customerId,
                currencyCode: currencyCode,
                description: description
            },
            {
                auth: AUTH
            }
        )
    }

    deleteAccount(accountName: string) {
        return axios.delete(ACCOUNT.DELETE_ACCOUNT(accountName),
            {
                auth: AUTH
            }
        )
    }

    getAllCustomerAccounts(customerId: number | string) {
        return axios.get(ACCOUNT.GET_ALL_ACCOUNTS(customerId),
            {
                auth: AUTH
            }
        )
    }   
}

export default new AccountService;