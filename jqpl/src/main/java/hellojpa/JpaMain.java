package hellojpa;


import hellojpa.jpql.Member;
import hellojpa.jpql.MemberDTO;
import hellojpa.jpql.Team;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            Member member = new Member();
            member.setUsername("user1");
            member.setAge(10);
            entityManager.persist(member);

            Member singleResult = entityManager.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "user1")
                    .getSingleResult();
            System.out.println("singleResult = " + singleResult);

//            List<Object[]> result = entityManager.createQuery("select m.username, m.age from Member m")
//                    .getResultList();

            List<MemberDTO> result = entityManager.createQuery("select new hellojpa.jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();

            entityManager.createQuery("select m.username, m.age from Member m", Member.class);

            MemberDTO memberDTO = result.get(0);
            System.out.println("memberDTO = " + memberDTO);

//            Object[] results = result.get(0);
//            System.out.println("results = " + results[0]);
//            System.out.println("results = " + results[1]);


            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        entityManager.close();
        entityManagerFactory.close();
    }
}
