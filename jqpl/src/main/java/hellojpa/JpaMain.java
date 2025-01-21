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
            for (int i = 0; i < 100; i++) {
                Member member = new Member();
                member.setUsername("user" + i);
                member.setAge(10 + i);
                entityManager.persist(member);
            }

            List<Member> result = entityManager.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            System.out.println("result.size = " + result.size());
            for (Member member1 : result) {
                System.out.println("member1 = " + member1);
            }






            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        entityManager.close();
        entityManagerFactory.close();
    }
}


/* [트랜잭션]
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
 */