package service;

import Dto.BusLineRequestDto;
import Dto.BusStopRequestDto;
import domain.busline.BusLine;
import domain.busstop.BusStop;

import javax.persistence.*;
import java.util.List;

public class FavoritesService {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2jpa");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    public FavoritesService(){}

    // 버스노선 즐겨찾기 목록에 추가.
    public void save(BusLineRequestDto requestDto) {
        try {
            tx.begin();
            em.persist(requestDto.toEntity());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

    }
    // 버스정류장 즐겨찾기 목록에 추가.
    public void save(BusStopRequestDto requestDto) {
        try {
            tx.begin();
            em.persist(requestDto.toEntity());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    // id로 버스노선 즐겨찾기 목록에서 제
    public void delById(BusLineRequestDto requestDto) {
        try {
            tx.begin();
            BusLine foundBusLine = em.find(BusLine.class, requestDto.getId());
            em.remove(foundBusLine);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        }
    }
    // id로 버스정류장 즐겨찾기 목록에서 제거.
    public void delById(BusStopRequestDto requestDto) {
        try {
            tx.begin();
            BusStop foundBusStop = em.find(BusStop.class, requestDto.getId());
            em.remove(foundBusStop);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        }
    }

    //버스 노선 즐겨찾기 목록 반환.
    public List<BusLine> findAllLines() {
        try {
            tx.begin();
            List<BusLine> busLineList = em.createQuery("SELECT bl FROM BusLine bl", BusLine.class).getResultList();
            tx.commit();
            return busLineList;

        } catch (Exception e) {
            tx.rollback();
        }
        return null;
    }

    //버스 정류장 즐겨찾기 목록환 반환.
    public List<BusStop> findAllStops() {
        try {
            tx.begin();
            List<BusStop> BusStopList = em.createQuery("SELECT bs FROM BusStop bs", BusStop.class).getResultList();
            tx.commit();
            return BusStopList;

        } catch (Exception e) {
            tx.rollback();
        }
        return null;
    }

    //모든 즐겨찾기 정류장 삭제.
    public void deleteAllStop(){
        try {
            tx.begin();
            Query query = em.createQuery("delete from BusStop");
            query.executeUpdate();
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        }
    }

    //모든 즐겨찾기 버스노선 삭제.
    public void deleteAllLine(){
        try {
            tx.begin();
            Query query = em.createQuery("delete from BusLine");
            query.executeUpdate();
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        }
    }
}
