package pahakia.example.jpa.entity;

import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.junit.Test;

import pahakia.dao.PahakiaEntityManagerFactory;

public class BookEntityTest {
    private static Logger logger = Logger.getLogger(BookEntityTest.class.getName());
    @Test
    public void test1() {
        EntityManager em = PahakiaEntityManagerFactory.getEntityManager();
        BookEntity book = new BookEntity("Wind", "123456789", "something about wind", 10);
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
        logger.info("book: " + book);

        EntityManager em2 = PahakiaEntityManagerFactory.getEntityManager();
        BookEntity book2 = em2.find(BookEntity.class, book.getId());
        logger.info("book2: " + book2);
        book.setBookAbstract("changes about wind");
        em2.getTransaction().begin();
        em2.merge(book);
        em2.getTransaction().commit();

        EntityManager em3 = PahakiaEntityManagerFactory.getEntityManager();
        BookEntity book3 = em3.find(BookEntity.class, book.getId());
        logger.info("book3: " + book3);

    }
}
