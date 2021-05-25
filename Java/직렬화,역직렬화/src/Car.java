import java.io.Serializable;

public class Car implements Serializable {
    private static final long serialVersionUID = 1234L;

    private String model;
    private String number;
    private String location;

    private transient String SecretNumber ="asdaf23D23";

    public Car(String model, String number){
        this.model =model;
        this.number = number;
    }
//    Sedan sedan = new Sedan();

    public void driving(){
        System.out.println("driving : "+model+", "+number);
    };

}
