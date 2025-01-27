package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        String query = "select o from Order o join o.member m " +
                "where o.status = :status " +
                "and m.name like :name";

        return em.createQuery(query, Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .setMaxResults(1000)
                .getResultList();
    }

    public List<Order> findAllByString(OrderSearch orderSearch) {
        // 기본 JPQL 문
        String jpql = "select o from Order o join o.member m where 1=1";

        // 동적 쿼리 생성
        if (orderSearch.getOrderStatus() != null) {
            jpql += " and o.status = :status";
        }
        if (orderSearch.getMemberName() != null && !orderSearch.getMemberName().isEmpty()) {
            jpql += " and m.name like :name";
        }

        var query = em.createQuery(jpql, Order.class);

        // 파라미터 설정
        if (orderSearch.getOrderStatus() != null) {
            query.setParameter("status", orderSearch.getOrderStatus());
        }
        if (orderSearch.getMemberName() != null && !orderSearch.getMemberName().isEmpty()) {
            query.setParameter("name", "%" + orderSearch.getMemberName() + "%"); // 부분 일치 검색
        }

        return query.setMaxResults(1000) // 최대 1000개 조회 제한
                .getResultList();
    }

    public List<Order> findAllWithMemberDelivery() {
        return em.createQuery(
                "select o from Order o join fetch o.member join fetch o.delivery", Order.class
        ).getResultList();
    }


}
