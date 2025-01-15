package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            /*
            Insert 기능

            Member member = new Member();
            member.setId(1L);
            member.setName("HelloAB");
            entityManager.persist(member);
             */

            /*
            Select 기능

            Member findMember = entityManager.find(Member.class, 1l);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
             */

            /*
            Update 기능

            Member findMember = entityManager.find(Member.class, 1l);
            findMember.setName("Change");
             */

            /*
            JPQL
             */
            List<Member> findAllMembers = entityManager.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(4)
                    .setMaxResults(8)
                    .getResultList();

            for (Member findAllMember : findAllMembers) {
                System.out.println("findAllMember = " + findAllMember.getName());
            }


            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }




        entityManager.close();

        entityManagerFactory.close();
    }
}
