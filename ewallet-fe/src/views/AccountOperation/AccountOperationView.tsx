import React, { useEffect, useState } from 'react';
import { CUSTOMER } from '../../models/CustomerModel';
import AccountService from '../../services/Account/AccountService';
import css from './AccountOperationView.module.css'
import { ACC_OPERATION } from '../../api/AccountOperations'
import AccountOperationService from '../../services/Account/AccountOperationService';

const AccountOperationView = () => {
    let allAccounts: any = [];

    const [hideSecond, setHideSecond] = useState(false);
    const [disableButton, setDisableButton] = useState(true);
    const [accCode, setAccCode] = useState('');
    const [error, setError] = useState('');
    const [operationResult, setOperationResult] = useState('');
    const [accOperation, setAccOperation] = useState('');
    const [amount, setAmount] = useState(Number);
    const [accCodeSecond, setAccCodeSecond] = useState('');
    const [accts, setAccts] = useState([]);
    const [appState, setAppState] = useState({
        loading: false,
        accounts: null
    });

    let handleHideSecondSelector = (event: any) => {
        let hide = event.target.checked
        setHideSecond(event.target.checked)
    }

    let handleSelectChange = (event: any) => {
        let value = event.target.value
        setAccCode(value)
    }

    let handlePress = (event: any) => {
        if (!/[0-9.]/.test(event.key)) {
            event.preventDefault();
        }
    }

    let handleAmountChange = (event: any) => {
        if (!/^[0-9]{1,}[.]{0,1}[0-9]{0,2}$/.test(event.target.value)) {
            setDisableButton(true)
        } else {
            setDisableButton(false)
        }
        setAmount(event.target.value)
    }

    let addTransaction = () => {
        AccountOperationService.addTransaction(accCode, accCodeSecond, amount).then((result) => {
            setOperationResult(result.data)
            setError('')
        }).catch(err => {
            setError(err.response.data.errorCode + ': ' + err.response.data.errorMessage)
        }
        );
    }

    let addOperation = () => {
        AccountOperationService.addAccountOperation(accCode, amount, accOperation).then((result) => {
            setOperationResult(result.data)
            setError('')
        }).catch(err => {
            setError(err.response.data.errorCode + ': ' + err.response.data.errorMessage)
        });
    }

    let handleSelectChangeSecond = (event: any) => {
        let value = event.target.value
        setAccCodeSecond(value)
    }

    let handleOperationChange = (event: any) => {
        let value = event.target.value
        setAccOperation(value)
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
            Make transaction to another account:
            <input name='hideSecond' type='checkbox' onChange={handleHideSecondSelector} />
            <br />
            <br />
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
                <span hidden={hideSecond}>Select operation:</span>
                <select hidden={hideSecond} value={accOperation} onChange={handleOperationChange}>
                    <option value=''/>
                    <option value={ACC_OPERATION.REPLENISH}>{ACC_OPERATION.REPLENISH}</option>
                    <option value={ACC_OPERATION.WITHDRAW}>{ACC_OPERATION.WITHDRAW}</option>
                </select>
            </div>
            <br />
            <div hidden={!hideSecond}>
                Select second account:
                <select value={accCodeSecond} onChange={handleSelectChangeSecond}>
                    <option value=''/>
                    {
                        accts.map((acc: any) => (
                            <option value={acc.accountName}>{acc.accountName}</option>
                        ))
                    }
                </select>
            </div>
            <br />
            <div>
                Amount:
                <input pattern="[0-9]." name='amount' type='text' onKeyPress={handlePress} onChange={handleAmountChange} />
                Only numbers and dot. Pattern: XXXX.XX
            </div>
            <br />
            <br />
            <div>
                <button
                    disabled={disableButton || accCode.length == 0 || accOperation.length == 0}
                    hidden={hideSecond}
                    className={css.button}
                    onClick={addOperation}>Add operation</button>
                <button
                    disabled={disableButton || accCode.length == 0 || accCodeSecond.length == 0}
                    hidden={!hideSecond}
                    className={css.button}
                    onClick={addTransaction}>Add transaction</button>
            </div>
            <div className={css.result} hidden={error.length != 0}>
                Result: {operationResult}
            </div>
            <div className={css.error} hidden={error.length == 0}>
                Error: {error}
            </div>
        </div>
    )
}

export default AccountOperationView;