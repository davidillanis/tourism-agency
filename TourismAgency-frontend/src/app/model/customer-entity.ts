export class CustomerEntity {
    idCustomer: number;
    name: string;
    lastName: string;
    address: string;
    dni: string;
    birthdate: string;
    nationality: string;
    cellPhone: string;
    email: string;

    constructor(idCustomer: number=0, name: string="", lastName: string="", address: string="", dni: string="", birthdate: string="", nationality: string="", cellPhone: string="", email: string="") {
        this.idCustomer = idCustomer;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.dni = dni;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.cellPhone = cellPhone;
        this.email = email;
    }
}
