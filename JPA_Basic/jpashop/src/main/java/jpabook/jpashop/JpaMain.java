package jpabook.jpashop;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            Book book = new Book();
            book.setName("Book1");
            book.setAuthor("Author1");
            book.setIsbn("123");
            entityManager.persist(book);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }




        entityManager.close();

        entityManagerFactory.close();
    }
}
