package domain.busstop;

import domain.busline.BusLine;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class BusStopTest {
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
        Integer id = 456;
        BusStop busStop = new BusStop.Builder()
                .id(id)
                .name("전남대후문정류")
                .build();

        //등록
        em.persist(busStop);

        //전체 조회
        List<BusStop> BusStopList = em.createQuery("select b from BusStop b", BusStop.class).getResultList();
        System.out.println(BusStopList.get(0).getId());
        System.out.println(BusStopList.get(0).getName());

        //id로 삭제
        BusStop findBusStop =em.find(BusStop.class, id);
        em.remove(findBusStop);
    }
}
