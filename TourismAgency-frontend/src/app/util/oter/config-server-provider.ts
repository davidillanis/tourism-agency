export class ConfigServerProvider {
    //string boot configuration
    private static readonly serverApi:string="http://localhost:8083";
    
    //keycloak configuration
    public static readonly apiAuthURL:string="http://localhost:8080/realms/spring-boot-realm-dev/protocol/openid-connect/token";
    public static readonly clientId:string="spring-client-api-rest";
    public static readonly grandType:string="password";
    public static readonly clientSecret:string="MczItIj8xDX55sOCmviM3QWRTdPqNWmW";

    //imgbb configuration
    public static readonly keyImgBB:string="a3d1b74ce17fdaf5e53592b7bf47cbf2";
    public static readonly apiImgBB:string="https://api.imgbb.com/1/upload";

    public static concatBackendAPI(router:string){
        return this.serverApi.concat(router);
    }
}
