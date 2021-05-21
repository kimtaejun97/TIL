package domain.busline;

import javax.persistence.*;
import java.util.List;

public class BusLineTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx =em.getTransaction();

        try{
            tx.begin();
            logic(em);
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
    private static void logic(EntityManager em){
        Integer id = 123;
        BusLine busLine = new BusLine.Builder()
                .id(id)
                .name("전남대")
                .build();

        //등록
        em.persist(busLine);

        //전체 조회
        List<BusLine> busLineList = em.createQuery("select b from BusLine b", BusLine.class).getResultList();
        System.out.println(busLineList.get(0).getId());
        System.out.println(busLineList.get(0).getName());

        //id로 삭제
        BusLine findBusLine =em.find(BusLine.class, id);
        em.remove(findBusLine);
    }
}
