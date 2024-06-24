export class EmployeeEntity {
    idEmployee: number;
    name: string;
    lastName: string;
    address: string;
    dni: string;
    birthdate: string;
    nationality: string;
    cellPhone: string;
    email: string;
    position: string;
    salary: number;

    constructor(
        idEmployee: number = 0,
        name: string = "",
        lastName: string = "",
        address: string = "",
        dni: string = "",
        birthdate: string = "",
        nationality: string = "",
        cellPhone: string = "",
        email: string = "",
        position: string = "",
        salary: number = 0
    ) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.dni = dni;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.cellPhone = cellPhone;
        this.email = email;
        this.position = position;
        this.salary = salary;
    }
}
