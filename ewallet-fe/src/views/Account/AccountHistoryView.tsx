import React, { useEffect, useState } from 'react';
import { CUSTOMER } from '../../models/CustomerModel';
import AccountService from '../../services/Account/AccountService';
import AccountOperationService from '../../services/Account/AccountOperationService';
import HistoryView from './HistoryView';

const AccountHistoryView = () => {

    let allAccounts: any = [];
    let allHistory: any = [];
    const [accCode, setAccCode] = useState('');
    const [accHistory, setAccHistory] = useState([]);
    const [accts, setAccts] = useState([]);
    const [appState, setAppState] = useState({
        loading: false,
        accounts: null
    });

    let handleSelectChange = (event: any) => {
        let value = event.target.value
        setAccCode(value)
        if (value) {
            AccountOperationService.getAccountOperations(event.target.value).then((result) => {
                allHistory = result.data;
                setAccHistory(allHistory)

            });
        } else {
            allHistory = null
            setAccHistory(allHistory)
        }
    }

    useEffect(() => {
        AccountService.getAllCustomerAccounts(CUSTOMER.ID).then((result) => {
            allAccounts = result.data;
            setAccts(allAccounts)
            setAppState({ loading: true, accounts: allAccounts })
        });
    }, [setAppState]);

    return (
        <div>
            Select account:
            <select value={accCode} onChange={handleSelectChange}>
                <option />
                {
                    accts.map((acc: any) => (
                        <option value={acc.accountName}>{acc.accountName}</option>
                    ))
                }
            </select>
            <br />
            <div>
                <HistoryView history={accHistory} />
            </div>
        </div>
    )
}

export default AccountHistoryView;