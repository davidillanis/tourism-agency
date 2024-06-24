export class ServiceTourismEntity {
    codeService:string;
    nameService:string;
    urlImage:string|null;
    description:string;
    dateService:string;
    costService:number;
    available:boolean;

    constructor(codeService: string="", nameService: string="", urlImage: string|null="", description: string="", dateService: string="" , costService: number=0, available=true) {
        this.codeService = codeService;
        this.nameService = nameService;
        this.urlImage = urlImage;
        this.description = description;
        this.dateService = dateService;
        this.costService = costService;
        this.available=available;
    }
}

