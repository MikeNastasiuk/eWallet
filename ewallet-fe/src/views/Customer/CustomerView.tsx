import React from 'react';
import { CUSTOMER } from '../../models/CustomerModel';

const CustomerView = () => {
    return <div>
        <p><b>First Name</b>: {CUSTOMER.FIRST_NAME}</p>
        <p><b>Last Name</b>: {CUSTOMER.LAST_NAME}</p>
        <p><b>Login</b>: {CUSTOMER.LOGIN}</p>
    </div>
}

export default CustomerView;