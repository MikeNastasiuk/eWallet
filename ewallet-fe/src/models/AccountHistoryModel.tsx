export class AccountHistory {
    constructor(
        public account: string,
        public quantity: number,
        public operation: string,
        public operationDate: string
    ) {}
}