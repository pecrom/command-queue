package com.task.dao.repository;

import com.task.dao.bo.SUserBo;

import java.util.Collection;

/**
 * Repository for handling susers table
 */
public interface SUsersRepository {

    /**
     * Save new user to database
     * @param sUserBo {@link SUserBo} user to be saved
     */
    void save(SUserBo sUserBo);

    /**
     * Get all users from table
     * @return {@link Collection<SUserBo>} of users
     */
    Collection<SUserBo> findAll();

    /**
     * Delete all users from database
     */
    void deleteAll();
}
