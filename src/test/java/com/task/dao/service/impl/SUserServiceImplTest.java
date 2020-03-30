package com.task.dao.service.impl;

import com.task.dao.bo.SUserBo;
import com.task.dao.repository.SUsersRepository;
import com.task.dao.service.SUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class SUserServiceImplTest {

    @Test
    @DisplayName("Test deletion")
    void deleteAllTest() {
        SUsersRepository sUsersRepository = mock(SUsersRepository.class);
        SUserService sUserService = new SUserServiceImpl(sUsersRepository);

        sUserService.deleteAll();
        verify(sUsersRepository).deleteAll();
    }

    @Test
    @DisplayName("Test user addition")
    void addUserTest() {
        Long id = 1L;
        String guid = "guid";
        String username = "username";

        SUsersRepository sUsersRepository = mock(SUsersRepository.class);
        SUserService sUserService = new SUserServiceImpl(sUsersRepository);

        sUserService.addUser(id, guid, username);

        SUserBo expectedUser = new SUserBo();
        expectedUser.setId(id);
        expectedUser.setGuid(guid);
        expectedUser.setUsername(username);

        verify(sUsersRepository).save(expectedUser);
    }

    @Test
    @DisplayName("Test if users are in database")
    void getAllTest() {
        SUsersRepository sUsersRepository = mock(SUsersRepository.class);
        SUserService sUserService = new SUserServiceImpl(sUsersRepository);

        sUserService.getAll();
        verify(sUsersRepository).findAll();
    }

}