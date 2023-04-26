import React, { useEffect, useState } from 'react';
import AccountService from '../../services/Account/AccountService';
import css from "./AccountView.module.css";
import CurrencyService from '../../services/Currency/CurrencyService';
import { CUSTOMER } from '../../models/CustomerModel';
import { Link } from "react-router-dom";

let allCurrData: any;

const NewAccountView = () => {

    let addAccount = (id: number, code: string, description: string) => {
        AccountService.createAccount(id, code, description)
    }

    let handleSelectChange = (event: any) => {
        setCurrCode(event.target.value)
    }

    let handleTextChange = (event: any) => {
        setAccDescription(event.target.value)
    }

    let handleButtonClick = () => {
        addAccount(CUSTOMER.ID, currCode, accDescription)
    }

    const [currCode, setCurrCode] = useState('');
    const [accDescription, setAccDescription] = useState('');
    const [appState, setAppState] = useState({
        loading: false,
        currOptions: []
    });

    useEffect(() => {
        CurrencyService.getAllCurrency().then((result) => {
            allCurrData = result.data
            setAppState({ loading: true, currOptions: allCurrData })
        });
    }, [setAppState]);

    return (
        <div>
            Select account currency:
            <select value={currCode} onChange={handleSelectChange}>
                <option />
                {
                    appState.currOptions.map((curr: any) => (
                        <option value={curr.code}>{curr.name}</option>
                    ))
                }
            </select>
            <br />
            Add account description:
            <input name='accDescription' type='text' onChange={handleTextChange} />
            <br />
            <Link to='/accounts'>
                <button
                    disabled={currCode.length == 0}
                    className={css.button}
                    onClick={handleButtonClick}>Add account</button>
            </Link>
        </div>
    )
}

export default NewAccountView;