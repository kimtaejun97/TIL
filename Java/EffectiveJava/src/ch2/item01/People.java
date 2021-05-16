package ch2.item01;

//item 01 : 생성자 대신 정적 팩터리 메서드를 고려하라.
public class People {

    private String name;
    private String number;
    private static final People staticPeople = new People("kim");

    public People(){}

    public People(String name){
        this.name = name;
    }
//    public item01(String address){
//        this.address = address;
//    }
    public static People student(String name, String number){
        People people =new People(name);
        people.number = number;
        return people;
    }
    public static People getStaticPeople(){
        return staticPeople;
    }
    public static People isMan(Boolean flag){
        return flag ? new Man(): new Girl();
    }

    public static People getPeople(){
        People people = new People();
        // people = 풀 패키지 경로로부터 현 클래스의 하위클래스를 읽어옴.
        return people;
    }

    public static void main(String[] args) {
        People item = new People("kim");
        People staticMethodItem = People.student("kim","164160");

        People staticItem = People.getStaticPeople();
    }
}
