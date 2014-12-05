package pahakia.samples.spring.jpa.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pahakia.model.PhkUser;

@Component
public class UserService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(PhkUser user) {
        em.persist(user);
    }

    public PhkUser find(long userId) {
        return em.find(PhkUser.class, userId);
    }

    public PhkUser findByFederatedId(String token) {
        TypedQuery<PhkUser> query = em.createQuery("select u from PhkUser u where u.federatedIdentity = :fedId",
                PhkUser.class);
        query.setParameter("fedId", token);
        return query.getSingleResult();
    }
}
