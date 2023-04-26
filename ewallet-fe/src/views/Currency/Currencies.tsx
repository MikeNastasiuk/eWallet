import React, {useState} from 'react';

const Currencies = ( props: any ) => {
    const currencies = props.data;

    if (!currencies || currencies.length === 0) {
        return <span>No currency data</span>
    }

    return (
        <div>
            {
                currencies.map((curr: any) => (
                    <div>
                        <span><b>Currency</b>: {curr.name} </span>
                        <span>({curr.code}) </span>
                        <span>- {curr.description}; </span>
                    </div>
                ))
            }
        </div>
    )
}

export default Currencies;