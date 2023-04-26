import React, { useState } from 'react';
import AccountService from '../../services/Account/AccountService';
import { Routes, Route } from "react-router-dom";
import AccountHistoryView from './AccountHistoryView';
import { Link } from "react-router-dom";

const Accounts = (props: any) => {

    const [accName, setAccName] = useState('');

    let deleteAccount = (account: string) => {
        AccountService.deleteAccount(account)
    }

    const handleDelete = (event: any) => {
        deleteAccount(event.target.name)
        window.location.reload()
    }

    let handleHistory  = (event: any) => {
        setAccName(event.target.name)
    }

    const accounts = props.data;

    if (!accounts || accounts.length === 0) {
        return <span>No any accounts</span>
    }

    return (
        <div>
            {
                accounts.map((acc: any) => (
                    <div>
                        <span><b>Account</b>: {acc.accountName} </span>
                        <span>({acc.accountCurrency}); </span>
                        <span><b>Amount</b>: {acc.amount}; </span>
                        <span><b>Description</b>: {acc.description}; </span>
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        <button name={acc.accountName} onClick={handleDelete}>Delete account</button>
                    </div>
                ))
            }
        </div>
    )
}

export default Accounts;