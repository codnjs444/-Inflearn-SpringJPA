package hellojpa;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
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

            Member flushTest = new Member(202L, "member200");
            entityManager.persist(flushTest);

            System.out.println("--------------------------------");
            entityManager.flush();
             */

            /*
            동일성 보장
            Member member = entityManager.find(Member.class, 200L);
            member.setName("CCC");

            entityManager.close();

            Member member2 = entityManager.find(Member.class, 201L);
             */


            /*
            JPA Table MAPPING 적용 전 (타입 등록 및 수동 연결)

            Team team = new Team();
            team.setName("TeamA");
            entityManager.persist(team);

            Member member = new Member();
            member.setUserName("member1");
            member.setTeamId(team.getId());

            entityManager.persist(member);

            Member findMember = entityManager.find(Member.class, member.getId());

            Long findTeamId = findMember.getTeamId();
            Team findTeam = entityManager.find(Team.class, findTeamId);
             */

            /*
            단방향 매핑

            Team team = new Team();
            team.setName("TeamA");
            entityManager.persist(team);

            Member member = new Member();
            member.setUserName("member1");
            member.setTeam(team);
            entityManager.persist(member);

            Member findMember = entityManager.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();
            System.out.println("----------------------------------------");
            System.out.println("findTeam = " + findTeam.getName());
            System.out.println("----------------------------------------");
             */

            /*
            양방향 Test

            Team team = new Team();
            team.setName("HAHA");
            entityManager.persist(team);

            Member member = new Member();
            member.setUserName("LeeChaeWon");
            member.setTeam(team);
            entityManager.persist(member);

            entityManager.flush();
            entityManager.clear();

            Member findMember = entityManager.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            System.out.println("----------------------------------------");
            for (Member member1 : members) {
                System.out.println("m = " + member1.getUserName());
            }
            System.out.println("----------------------------------------");
            transaction.commit();
             */

            Movie movie = new Movie();
            movie.setDirector("testDirector");
            movie.setActor("testActor");
            movie.setName("Star");
            movie.setPrice(10000);

            entityManager.persist(movie);

            entityManager.flush();
            entityManager.clear();

            Movie findMovie = entityManager.find(Movie.class, movie.getId());
            System.out.println("================================================");
            System.out.println("findMovie = " + findMovie);

            Album album = new Album();
            album.setName("testAlbum");
            album.setPrice(10000);

            entityManager.persist(album);

            entityManager.flush();
            entityManager.clear();

            Member member = new Member();
            member.setUserName("user1");
            member.setCreatedBy("kim");
            member.setCreatedDate(LocalDateTime.now());

            entityManager.persist(member);
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
        }




        entityManager.close();

        entityManagerFactory.close();
    }
}
