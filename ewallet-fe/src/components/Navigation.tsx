import React from 'react';
import { Link } from "react-router-dom";

const Navigation = () => {
    return <nav>
        <div>
            <Link to='/customer'>Customer</Link>
            {/* <a href='/customer'>Customer</a> */}
        </div>
        <div>
            <Link to='/accounts'>Accounts</Link>
            {/* <a href='/accounts'>Accounts</a> */}
        </div>
        <div>
            <Link to='/account-operation'>Account operation</Link>
            {/* <a href='/account-operation'>Account operation</a> */}
        </div>
        <div>
            <Link to='/currency'>Currencies</Link>
            {/* <a href='/currency'>Currencies</a> */}
        </div>
    </nav>
}

export default Navigation;