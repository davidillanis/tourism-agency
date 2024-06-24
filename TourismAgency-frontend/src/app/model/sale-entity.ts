export class SaleEntity {
    numberSale: number;
    dateSale: string;
    paymentMethod: string;
    idCustomer: number;
    idEmployee: number;
    codePackages: string[];
    codeServices: string[];

    constructor(
        numberSale: number=0,
        dateSale: string="",
        paymentMethod: string="",
        idCustomer: number=0,
        idEmployee: number=0,
        codePackages: string[]=[],
        codeServices: string[]=[]
    ) {
        this.numberSale = numberSale;
        this.dateSale = dateSale;
        this.paymentMethod = paymentMethod;
        this.idCustomer = idCustomer;
        this.idEmployee = idEmployee;
        this.codePackages = codePackages;
        this.codeServices = codeServices;
    }
}
