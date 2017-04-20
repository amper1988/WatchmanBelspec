package retrofit.model.get_car_to_parking.request;


import org.simpleframework.xml.Element;

public class GetCarToParkingRequestData {
    @Element(name = "tes:Identifier", required = false)
    private int identifier;
    @Element(name = "tes:Manufacture", required = false)
    private String manufacture;
    @Element(name = "tes:Model", required = false)
    private String model;
    @Element(name = "tes:Color", required =  false)
    private String color;
    @Element(name = "tes:CarId", required = false)
    private String carId;
    @Element(name = "tes:PoliceDepartment", required = false)
    private String policeDepartment;
    @Element(name = "tes:Policeman", required = false)
    private String policeman;
    @Element(name = "tes:Clause", required = false)
    private String clause;
    @Element(name = "tes:EvacuatedBool", required = false)
    private boolean evacuatedBool;
    @Element(name = "tes:EvacuationOrganization", required = false)
    private String evacuationOrganization;
    @Element(name = "tes:Wrecker", required = false)
    private String wrecker;
    @Element(name = "tes:EvacuationAddress", required = false)
    private String evacuationAddress;
    @Element(name = "tes:Photo1", required = false)
    private String photo1;
    @Element(name = "tes:Photo2", required = false)
    private String photo2;
    @Element(name = "tes:Photo3", required = false)
    private String photo3;
    @Element(name = "tes:Photo4", required = false)
    private String photo4;

    public GetCarToParkingRequestData(int identifier, String manufacture, String model, String color, String carId,
                                      String policeDepartment, String policeman, String clause, boolean evacuatedBool,
                                      String evacuationOrganization, String wrecker, String evacuationAddress,
                                      String photo1, String photo2, String photo3, String photo4){


        this.identifier = identifier;
        if(manufacture == null){
            this.manufacture = "";
        }else{
            this.manufacture = manufacture;
        }

        if(model == null){
            this.model = "";
        }else {
            this.model = model;
        }

        if(color == null){
            this.color = "";
        }else{
            this.color = color;
        }

        if(carId == null){
            this.carId = "";
        }else{
            this.carId = carId;
        }

        if(policeDepartment == null){
            this.policeDepartment = "";
        }else{
            this.policeDepartment = policeDepartment;
        }

        if(policeman == null){
            this.policeman = "";
        }else        {
            this.policeman = policeman;
        }

        if(clause == null){
            this.clause = "";
        }else{
            this.clause = clause;
        }

        if(evacuationOrganization == null){
            this.evacuationOrganization = "";
        }else
        {
            this.evacuationOrganization = evacuationOrganization;
        }
        if(wrecker == null){
            this.wrecker = "";
        }else {
            this.wrecker = wrecker;
        }
        if(photo1 == null){
            this.photo1 = "";
        }else{
            this.photo1 = photo1;
        }
        if(photo2 == null){
            this.photo2 = "";
        }else{
            this.photo2= photo2;
        }
        if(photo3 == null){
            this.photo3 = "";
        }else{
            this.photo3 = photo3;
        }
        if(photo4== null){
            this.photo4 = "";
        }else{
            this.photo4 = photo4;
        }

        if(evacuationAddress == null){
            this.evacuationAddress = "";
        }else{
            this.evacuationAddress = evacuationAddress;
        }
        this.evacuatedBool = evacuatedBool;





    }
}
