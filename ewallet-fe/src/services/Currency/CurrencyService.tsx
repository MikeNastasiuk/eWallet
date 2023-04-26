import React from 'react';
import axios from 'axios';
import { CURRENCY } from '../../api/Endpoints'
import { AUTH } from '../../api/Auth'

class CurrencyService {

    getAllCurrency () {
        return axios.get(CURRENCY.GET_ALL_CURRENCY,
            {
                auth: AUTH
            }
        )
    }

    getCurrencyByCode (code: string) {
        return axios.get(CURRENCY.GET_CURRENCY_DATA(code),
            {
                auth: AUTH,
            }
        )
    }
}

export default new CurrencyService;