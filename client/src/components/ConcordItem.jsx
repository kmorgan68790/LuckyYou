import React from 'react';

const ConcordItem = ({ concord }) => {
    return (
        <li>
            <h3>{concord.concordGroupNumber}</h3>
            <p>{concord.concordGroupDescription}</p>
        </li>
    );
};

export default ConcordItem;