import React from 'react';
import axios from 'axios';
import { OPERATION } from '../../api/Endpoints'
import { AUTH } from '../../api/Auth'
import { ACC_OPERATION } from '../../api/AccountOperations'

class AccountOperationService{

    addAccountOperation(accountName: string, quantity: number, operation: string) {
        return axios.post(OPERATION.ADD_OPERATION,
            {
                account: accountName,
                quantity: quantity,
                operation: operation
            },
            {
                auth: AUTH
            }
        )
    }

    getAccountOperations(accountName: string) {
        return axios.get(OPERATION.GET_OPERATIONS(accountName),
            {
                auth: AUTH
            }
        )
    }   

    addTransaction(accountFrom: string, accountTo: string, quantity: number) {
        return axios.post(OPERATION.ADD_TRANSACTION,
            {
                accountFrom: accountFrom,
                accountTo: accountTo,
                quantity: quantity
            },
            {
                auth: AUTH
            }
        )
    }  
}

export default new AccountOperationService;