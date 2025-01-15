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
            entityM                  anager.persist(member);
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

            List<Member> findAllMembers = entityManager.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(4)
                    .setMaxResults(8)
                    .getResultList();

            for (Member findAllMember : findAllMembers) {
                System.out.println("findAllMember = " + findAllMember.getName());
            }
             */


            /*
            1차 캐쉬 사용 Test

            Member member = new Member();
            member.setId(101L);
            member.setName("PERSISTANCE TEST");

            entityManager.persist(member);

            Member findmember = entityManager.find(Member.class, 101L);

            System.out.println("findmember = " + findmember.getId());
            System.out.println("findmember = " + findmember.getName());
             */

            /*
            트랜잭션 사용 쓰기 지연 (Commit 하기 전까지 버퍼? 공간이 있음)

            Member member1 = new Member(2L, "A");
            Member member2 = new Member(3L, "B");


            entityManager.persist(member1);
            entityManager.persist(member2);

            System.out.println("member2 = " + member2);
             */

            /*
            영속성 컨텍스트 변경감지

            Member m = entityManager.find(Member.class, 2L);
            m.setName("A->C");

            System.out.println("+==-=-=-=--==-=-=-=-=");
             */

            /*
            플러시
             */

            Member flushTest = new Member(202L, "member200");
            entityManager.persist(flushTest);

            System.out.println("--------------------------------");
            entityManager.flush();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }




        entityManager.close();

        entityManagerFactory.close();
    }
}
