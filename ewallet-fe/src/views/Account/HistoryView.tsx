import React, { useEffect, useState } from 'react';

const HistoryView = (props: any) => {

    let history = props.history
    if (!history || history.length === 0) {
        return <span>No any account history</span>
    }

    return (
        <div>
            {
                history.map((acc: any) => (
                    <div>
                        <span><b>Operation date</b>: {acc.operationDate} </span>
                        <span><b>Operation</b>: {acc.operation} </span>
                        <span>- {acc.quantity}; </span>
                    </div>
                ))
            }
        </div>
    )
}

export default HistoryView;