import React from 'react';
import './App.css';
import {Routes, Route} from "react-router-dom";
import CustomerView from './views/Customer/CustomerView';
import Navigation from './components/Navigation';
import CurrencyView from './views/Currency/CurrencyView';
import AccountView from './views/Account/AccountView';
import NewAccountView from './views/Account/NewAccountView'
import AccountHistoryView from './views/Account/AccountHistoryView';
import AccountOperationView from './views/AccountOperation/AccountOperationView';

let customerModel = {
  login: '123456',
  customerName: 'John Johnson'
}

function App() {
  return (
    <div className='App'>
      <div className='header'>
        Customer wallet
      </div>
      <div className='navigation'>
        <Navigation />
      </div>
      <div className='content'>
        <Routes>
          <Route path='/customer' element={<CustomerView />} />
          <Route path='/currency' element={<CurrencyView />} />
          <Route path='/accounts' element={<AccountView />} />
          <Route path='/add-account' element={<NewAccountView />} />
          <Route path='/account-history' element={<AccountHistoryView />} />
          <Route path='/account-operation' element={<AccountOperationView />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
