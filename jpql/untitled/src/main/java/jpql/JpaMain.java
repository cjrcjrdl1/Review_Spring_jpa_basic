package jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("관리자1");
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            em.persist(member2);
            em.flush();
            em.clear();

            String query = "select m from Team t join t.members m";
            Collection result = em.createQuery(query, Collection.class)
                    .getResultList();

            for (Object s : result) {
                System.out.println("s = " + s);
            }


            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally{
            em.close();
        }
        emf.close();
    }
}