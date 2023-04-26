export const CUSTOMER = {
    ID: 51,
    LOGIN: 'JohnJohnson',
    PASSWORD: 'password',
    FIRST_NAME: 'John',
    LAST_NAME: 'Johnson'
};

export class CustomerModel {
    constructor(
        public login: string,
        public customerName: string
    ) {}
}

export default CustomerModel;