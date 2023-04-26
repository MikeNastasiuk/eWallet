export class Account {
    constructor(
        public accountName: string,
        public accountCurrency: string,
        public description: string,
        public amount: number
    ) {}
}