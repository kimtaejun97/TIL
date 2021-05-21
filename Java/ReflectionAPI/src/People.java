import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class People {
    private String name;
    private int age;
    private int nprogram;

    public People(String name, int age){
        this.name =name;
        this.age = age;
        nprogram =0;
    }

    public void coding(){
        this.nprogram ++;
    }

    public int getNprogram(){
        return nprogram;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException {
        Object objPeople = new People("kim", 25);
//        Object형의 People은 coding을 하지 못함.
//        objPeople.coding

        Class PeopleClass = People.class;
        Method coding= PeopleClass.getMethod("coding");
        coding.invoke(objPeople, null);

        System.out.println(PeopleClass.getMethod("getNprogram")
                .invoke(objPeople,null));

        String objPeopleName =PeopleClass.getDeclaredField("name").get(objPeople).toString();
        System.out.println(objPeopleName);

        Field[] objPeoplefields =PeopleClass.getDeclaredFields();
        for(Field f: objPeoplefields){
            System.out.println(f.get(objPeople));
        }
    }
}
