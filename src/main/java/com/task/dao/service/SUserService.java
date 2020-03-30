package com.task.dao.service;

import com.task.dao.bo.SUserBo;

import java.util.Collection;

/**
 * Service handling users
 */
public interface SUserService {

    /**
     * Add user to database
     * @param id id of user
     * @param guid guid of user
     * @param username username of user
     */
    void addUser(Long id, String guid, String username);

    /**
     * Get all users from database
     * @return {@link Collection<SUserBo>} collection of users
     */
    Collection<SUserBo> getAll();

    /**
     * Delete all users
     */
    void deleteAll();
}
