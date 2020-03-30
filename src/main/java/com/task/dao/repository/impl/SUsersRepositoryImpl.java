package com.task.dao.repository.impl;

import com.task.dao.bo.SUserBo;
import com.task.dao.repository.SUsersRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.task.util.HibernateUtil.doInTransaction;
import static com.task.util.HibernateUtil.getInTransaction;

/**
 * Repository handling operations with user table
 */
public class SUsersRepositoryImpl implements SUsersRepository {

    // query for selecting all users
    private static final String SELECT_ALL_USERS_QUERY = "SELECT user FROM susers user";

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(SUserBo sUserBo) {
        // save user to database
        Consumer<Session> saveSUserBo = session -> {
            session.save(sUserBo);
        };

        doInTransaction(saveSUserBo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<SUserBo> findAll() {
        return findAllUsers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        Collection<SUserBo> allUsers = findAllUsers();

        // if there are any users, just delete them
        if (CollectionUtils.isNotEmpty(allUsers)) {

            // delete users
            Consumer<Session> deleteAllUsers = session -> {
                allUsers.forEach(session::delete);
            };

            doInTransaction(deleteAllUsers);
        }
    }

    private Collection<SUserBo> findAllUsers() {

        // get all users from database
        Function<Session, Collection<SUserBo>> getAllUsers = session ->
            session.createQuery(SELECT_ALL_USERS_QUERY, SUserBo.class).getResultList();

        return getInTransaction(getAllUsers);
    }

}
