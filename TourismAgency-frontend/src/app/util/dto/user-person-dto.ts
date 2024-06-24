export class UserPersonDTO {
    idCustomer: number;
    firstName: string |null;
    lastName: string | null;
    address: string |null;
    dni: string| null;
    birthdate: string |null;
    nationality: string |null;
    cellPhone: string |null;
    email: string;
    usernameKeyCloak: string;
    password:string;

    constructor(
        idCustomer: number=0,
        name: string="",
        lastName: string="",
        addres: string="",
        dni: string="",
        birthdate: string="",
        nationality: string="",
        cellPhone: string="",
        email: string="",
        usernameKeyCloak: string="",
        password: string=""
    ) {
        this.idCustomer = idCustomer;
        this.firstName = name;
        this.lastName = lastName;
        this.address = addres;
        this.dni = dni;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.cellPhone = cellPhone;
        this.email = email;
        this.usernameKeyCloak = usernameKeyCloak;
        this.password = password;
    }
}
