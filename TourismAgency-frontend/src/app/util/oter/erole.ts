export class ERole {
    public static CUSTOMER:boolean=false;
    public static EMPLOYEE:boolean=false;
    public static OWNER:boolean=false; 
    public static ADMINISTRATOR:boolean=false;

    public static ERoleList(): boolean[]{
        return [ERole.CUSTOMER, ERole.EMPLOYEE, ERole.OWNER, ERole.ADMINISTRATOR];
    }
}
