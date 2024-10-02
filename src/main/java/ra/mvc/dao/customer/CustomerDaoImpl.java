package ra.mvc.dao.customer;

import org.springframework.stereotype.Repository;
import ra.mvc.model.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Repository
public class CustomerDaoImpl implements ICustomerDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> typedQuery = entityManager.createQuery("select c from Customer c", Customer.class);
        List<Customer> list = typedQuery.getResultList();

        return list;
    }

    @Override
    public Customer findById(Integer id) {
        TypedQuery<Customer> typedQuery = entityManager.createQuery("select c from Customer c where id=:id", Customer.class);
        Customer customer = typedQuery.setParameter("id", id).getSingleResult();
        return customer;
    }

    @Override
    public void create(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public void update(Customer customer) {
        entityManager.merge(customer);

    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(findById(id));
    }
}
