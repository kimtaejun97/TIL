import java.io.*;
import java.util.ArrayList;

public class SerializationTest {
    public static void main(String[] args) throws IOException {
       try{
           FileOutputStream fos = new FileOutputStream("Car.ser");
           BufferedOutputStream bos = new BufferedOutputStream(fos);
           ObjectOutputStream oos = new ObjectOutputStream(bos);

           Car car1 = new Car("K5", "72저1234");
           Car car2  =new Car("K9","10저1245");

           Bus bus = new Bus("BUS","75바1111");

           ArrayList<Car> cars = new ArrayList<>(){
               {
                   add(car1);
                   add(car2);
               }
           };

           oos.writeObject(cars);
           oos.writeObject(bus);
           oos.close();

           System.out.println("done");
       }catch(Exception e){
           e.printStackTrace();
       }
    }
}
