import { ConfigServerProvider } from "../util/oter/config-server-provider";

export class ImageEntity {
    key:string;
    image:File;
    name:string;

    constructor(image:File, name="any", key:string=ConfigServerProvider.keyImgBB){
        this.image=image;
        this.name=name;
        this.key=key
    }
}
