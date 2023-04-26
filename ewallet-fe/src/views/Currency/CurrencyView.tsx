import React, { useEffect, useState } from 'react';
import Currencies from './Currencies';
import CurrencyService from '../../services/Currency/CurrencyService';
import axios from 'axios';
import css from "./CurrencyView.module.css";

let allCurrData: any;

const CurrencyView = () => {

    const [appState, setAppState] = useState({
        loading: false,
        currencies: null
    });

    useEffect(() => {
        CurrencyService.getAllCurrency().then((result) => {
            allCurrData = result.data;
            setAppState({loading: true, currencies: allCurrData})
        });
    }, [setAppState]);

    return (
        <div>
            <Currencies data={appState.currencies} />
        </div>

    )
}

export default CurrencyView;