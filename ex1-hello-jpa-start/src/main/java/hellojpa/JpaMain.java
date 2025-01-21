package hellojpa;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Slf4j
public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {



            transaction.commit(); // 트랜잭션 커밋;


        } catch (Exception e) {
            transaction.rollback();
        }
        entityManager.close();
        entityManagerFactory.close();
    }
}

            /* [jpql]
            List<Member> result = entityManager.createQuery(
                    "select m from Member m where m.userName like '%kim%'",
                    Member.class
            ).getResultList();

            System.out.println("============================================");
            for (Member member : result) {
                System.out.println("member = " + member);
            }
            System.out.println("============================================");
             */


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

            /*
            상속 관계 매핑 Test
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

             */

            /*
            Proxy (EntityManager.find & getReference + getPersistenceUnitUtil

            Member member = new Member();
            member.setUserName("User1");
            entityManager.persist(member);

            entityManager.flush();
            entityManager.clear();

            Member findmember = entityManager.find(Member.class, member.getId());
            System.out.println("findmember = " + findmember.getId());
            System.out.println("findmember = " + findmember.getUserName());

            Member reference = entityManager.getReference(Member.class, member.getId());
            System.out.println("reference = " + reference);
            System.out.println("reference = " + reference.getClass());

            System.out.println("entityManagerFactory.getPersistenceUnitUtil().isLoaded(findmember) = " + entityManagerFactory.getPersistenceUnitUtil().isLoaded(findmember));

            Hibernate.initialize(findmember);
             */

            /*
            Team team = new Team();
            team.setName("team1");
            entityManager.persist(team);

            Member member = new Member();
            member.setUserName("member1");
            member.setTeam(team);

            entityManager.persist(member);

            entityManager.flush();
            entityManager.clear();

            Member findMember = entityManager.find(Member.class, member.getId());

            System.out.println("---------------------------------------------------");
            System.out.println("findMember = " + findMember.getTeam().getClass());
            System.out.println("---------------------------------------------------");
             */

            /*
            OrphanRemoval Test

            Parent parent = new Parent();
            Child child1 = new Child();
            Child child2 = new Child();

            parent.setName("sleep");
            parent.addChild(child1);
            parent.addChild(child2);

            entityManager.persist(parent);

            entityManager.flush();
            entityManager.clear();

            Parent findParent = entityManager.find(Parent.class, parent.getId());
            findParent.getChildList().remove(0);
            entityManager.remove(findParent);
             */

        /*
        Embedded
            Member member = new Member();
            member.setUserName("hello");
            member.setHomeAddress(new Address("city", "street", "zipcode"));

            entityManager.persist(member);
         */

        /*
        불변 객체 세팅

            Address address = new Address("city", "street", "10000");
            Address newAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());

            Member member = new Member();
            member.setUserName("member1");
            member.setHomeAddress(address);
            entityManager.persist(member);

            Member member2 = new Member();
            member2.setUserName("member2");
            member2.setHomeAddress(newAddress);
            entityManager.persist(member2);
         */

            /* [ 값 타입 조회]
            Member findMember = entityManager.find(Member.class, member.getId());

            List<Address> addressHistory = findMember.getAddressHistory();
            for (Address address : addressHistory) {
                System.out.println("address = " + address.getCity());
            }

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for (String favoriteFood : favoriteFoods) {
                System.out.println("favoriteFood = " + favoriteFood);
            }
             */

            /* Entity 객체 Update / Delete
            Member member = new Member();
            member.setUserName("member1");
            member.setHomeAddress(new Address("부산", "해운대", "3333"));
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("족발");

            member.getAddressHistory().add(new Address("old1", "street1", "1111"));
            member.getAddressHistory().add(new Address("old2", "street2", "2222"));


            entityManager.persist(member);

            entityManager.flush();
            entityManager.clear();

            System.out.println("====================================================");

            Member findMember = entityManager.find(Member.class, member.getId());
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", a.getStreet(),a.getZipcode()));

            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            findMember.getAddressHistory().remove(new Address("old1", "street1", "1111"));
            findMember.getAddressHistory().add(new Address("removeAndAdd", "street1", "1111"));
             */