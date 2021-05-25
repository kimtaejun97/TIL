import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DeserializationTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try{
            FileInputStream fis = new FileInputStream("Car.ser");
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);

            ArrayList<Car> cars = (ArrayList<Car>)ois.readObject();
            Bus bus =(Bus)ois.readObject();

            cars.get(0).driving();
            cars.get(1).driving();
            bus.driving();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
