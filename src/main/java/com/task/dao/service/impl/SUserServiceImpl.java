package com.task.dao.service.impl;

import com.task.dao.bo.SUserBo;
import com.task.dao.repository.SUsersRepository;
import com.task.dao.service.SUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
public class SUserServiceImpl implements SUserService {

    // user repository
    @NonNull
    private SUsersRepository sUsersRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addUser(Long id, String guid, String username) {

        // create new user
        SUserBo sUserBo = new SUserBo();

        sUserBo.setId(id);
        sUserBo.setGuid(guid);
        sUserBo.setUsername(username);

        sUsersRepository.save(sUserBo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<SUserBo> getAll() {
        return sUsersRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        sUsersRepository.deleteAll();
    }
}
