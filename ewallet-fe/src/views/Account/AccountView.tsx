import React, { useEffect, useState } from 'react';
import AccountService from '../../services/Account/AccountService';
import { CUSTOMER } from '../../models/CustomerModel';
import Accounts from './Accounts';
import css from "./AccountView.module.css";
import { Link } from "react-router-dom";

const AccountView = () => {
    
    const [appState, setAppState] = useState({
        loading: false,
        accounts: null
    });

    useEffect(() => {
        AccountService.getAllCustomerAccounts(CUSTOMER.ID).then((result) => {
            let allAccounts = result.data;
            setAppState({loading: true, accounts: allAccounts})
        });
    }, [setAppState]);

    return (
        <div>
            <Accounts data={appState.accounts} />
            <br/>
            <br/>
            <br/>
            <Link to='/add-account'>
                <button className={css.button} >Add new account</button>
            </Link>
            <Link to='/account-history'>
                <button className={css.button} >Account history</button>
            </Link>
        </div>

    )
}

export default AccountView;