# ๐ Reflection API?
> ๊ตฌ์ฒด์ ์ธ ํด๋์ค ํ์์ ์์ง ๋ชปํด๋ ํด๋น ํด๋์ค์ ์ ๋ณด์ ์ ๊ทผํ  ์ ์๊ฒ ํด์ฃผ๋ java API
> 
**********


```java
public class People {
    private String name;
    private int age;
    private int nprogram;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
        nprogram = 0;
    }

    public void coding() {
        this.nprogram++;
    }

    public int getNprogram() {
        return nprogram;
    }
}
```

```java
 Object objPeople = new People("kim", 25);

//Objectํ์ People์ coding์ ํ์ง ๋ชปํจ.
objPeople.coding (x)
```
> - ์๋ฐ๋ ์ปดํ์ผ ํ์์ ํ์์ด ๊ฒฐ์ ๋๊ธฐ ๋๋ฌธ์ ๊ฐ์ฒด๋ ์ด๋ฏธ ์ปดํ์ผ ํ์์ Objectํ์์ผ๋ก ๊ฒฐ์ ์ด ๋์ด People์ ๋ฉ์๋๋ฅผ ์ฌ์ฉํ  ์ ์๋ค. Object ํ์์ ์ ๋ณด๋ง ์ฌ์ฉ ๊ฐ๋ฅ.

```java
Class PeopleClass = People.class;
Method coding= PeopleClass.getMethod("coding");
coding.invoke(objPeople, null);

System.out.println(PeopleClass.getMethod("getNprogram")
        .invoke(objPeople,null));
```
> - Reflection API๋ฅผ ์ด์ฉํ์ฌ Peopleํด๋์ค๋ฅผ ์ป์ด์ค๊ณ , ํด๋น ํด๋์ค์์ ๋ฉ์๋๋ฅผ ์ป์ด์ฌ ์ ์๋ค.
invoke์ ์ธ์๋ก ์คํํ  ๊ฐ์ฒด์ args๋ฅผ ๋๊ฒจ์ค๋ค. getNprogram ์คํ ๊ฒฐ๊ณผ 0์ผ๋ก ์ด๊ธฐํ๋์ด ์๋ ๊ฐ์ด 1๋ก ์ฆ๊ฐํ์์์ ํ์ธํ  ์ ์๋ค.


```java
String objPeopleName =PeopleClass.getDeclaredField("name").get(objPeople).toString();
System.out.println(objPeopleName);

Field[] objPeoplefields =PeopleClass.getDeclaredFields();
for(Field f: objPeoplefields){
    System.out.println(f.get(objPeople));
}
```
> - ์ฌ์ง์ด private๋ก ์ ์ธ๋ ํ๋์๋ ์ ๊ทผ์ด ๊ฐ๋ฅํ๋ค. ์ ์์ ์ผ๋ก objPeople์ ์์ฑํ ๋ ์ธ์๋ก ์ ๋ฌํด ์ฃผ์๋ ๊ฐ์ด ์ถ๋ ฅ๋์๋ค.
> -  public ๋ง ๊ฐ์ ธ์ค๊ธฐ ์ํด์๋ getFields๋ฅผ ์ฌ์ฉ.

```java
f.set(objPeople, f.get(objPeople) + " Reflection");
```
![img.png](img.png)     
> -  private ํ๋๊ฐ์ ๋ณ๊ฒฝ๋ํ ๊ฐ๋ฅํ ๊ฒ์ ํ์ธํ  ์ ์๋ค.

## ๐ง ์ด๋ป๊ฒ?
****
์๋ฐ์ฝ๋๋ ์ปดํ์ผ๋ฌ๋ฅผ ๊ฑฐ์ณ ๋ฐ์ดํธ ์ฝ๋๋ก ๋ณํ๋์ด Static ์์ญ์ ์ ์ฅ๋๋ค. Reflection API๋ ์ด ์์ญ์ ์ ๊ทผํ์ฌ ํด๋น ์ ๋ณด๋ค์ ํ์ฉํ  ์ ์๋ค.

์ ๋ง์ ๋ผ์ด๋ธ๋ฌ๋ฆฌ๋ ํ๋ ์์ํฌ์์ Reflection์ ์ฌ์ฉํ๋ค. ์คํ๋ง ํ๋ ์์ํฌ์์๋ ๋น ํฉํ ๋ฆฌ์์ Reflection์ ์ด์ฉํ์ฌ Bean์ ๊ด๋ฆฌํ๊ณ , Hibernate์์ ๋ํ Refelction์ ์ด์ฉํ์ฌ ์ํฐํฐ ํด๋์ค์ ๋ฐ์ดํฐ๋ฅผ ์ฃผ์ํ๋ค. ๋๋ฌธ์ default constructor๋ง ์์ฑํ  ์ ์์ผ๋ฉด ํ๋์ ๊ฐ์ ์ฃผ์ํด์ค ์ ์๋ค.

> ์์๋ก ์๋ํด๋ณธ ๋ฉ์๋๋ค ๋ง๊ณ ๋ ๋ง์ ๋ฉ์๋๊ฐ ์์ผ๋ฉฐ ์ ๊ทผ์ง์ ์, ์์ฑ์, ํจํค์ง ์ ๋ณด, ์ด๋ธํ์ด์ ๋ฑ ์ ๋ง์ ์ ๋ณด๋ฅผ ์ป์ ์ ์๋ค.


    - Hibernate์์ ์ด๋ป๊ฒ ์ํฐํฐ์ ํ๋๊ฐ์ ์ฃผ์์์ผ์ฃผ๋๊ฐ๋ฅผ ์์๋ณด๋ค๊ฐ Reflection์ ๋ํด ์์๋ณด๊ฒ ๋์๋ค.    
    - ์์ด๋นํ ์ ์ฉํ API์ด์ง๋ง ์ฑ๋ฅ๋ฉด์์ ์กฐ๊ธ ๋จ์ ์ด ์๊ณ , ์๊น ๋ณด์๋ฏ private์๋ ์ ๊ทผ์ด ๊ฐ๋ฅํ๊ธฐ ๋๋ฌธ์ ์ฃผ์๊ฐ ํ์ํ  ๊ฒ ๊ฐ๋ค.