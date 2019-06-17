package sa.user.service;

import sa.user.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserService {

    @PersistenceContext
    EntityManager entityManager;

    public List<User> getAllUsers(int first, int maxResult) {
        return entityManager.createNamedQuery(User.FIND_ALL)
                .setFirstResult(first).setMaxResults(maxResult).getResultList();
    }

    public User getUserByCode(long code){
        return entityManager.find(User.class, code);
    }

    public User getUserByUsername(String username){
        return entityManager.createNamedQuery(User.FIND_BY_USERNAME, User.class)
        .setParameter("username", username).getSingleResult();
    }

    public User createUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
        return user;
    }

    public User updateUser(long code, User user) {
        User userToUpdate = entityManager.find(User.class, code);

        userToUpdate.setName(user.getName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setCabin(user.getCabin());
        userToUpdate.setCreditCard(user.getCreditCard());
        //
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setPhoneNumber(user.getPhoneNumber());
        userToUpdate.setAddress(user.getAddress());
        userToUpdate.setCity(user.getCity());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setAvatar(user.getAvatar());
        userToUpdate.setStoreId(user.getStoreId());
        userToUpdate.setType(user.getType());
        return entityManager.merge(userToUpdate);
    }

    public long deleteUser(long code) {
        User user = entityManager.find(User.class, code);
        entityManager.remove(user);
        return code;
    }
}
