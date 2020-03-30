package com.task.command.impl;

import com.task.command.AbstractCommand;
import com.task.dao.service.SUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class InsertCommandTest {

    @Test
    @DisplayName("Insert valid test")
    public void processTest() {
        Long id = 1L;
        String guid = "guid";
        String username = "username";
        SUserService sUserService = mock(SUserService.class);

        AbstractCommand insertCommand = new InsertCommand(id, guid, username, sUserService);

        insertCommand.process();

        verify(sUserService).addUser(id, guid, username);
    }

    @Test
    @DisplayName("Insert of test")
    public void ofTest() {

        Long id = 1L;
        String guid = "guid";
        String username = "username";
        SUserService sUserService = mock(SUserService.class);
        AbstractCommand expected = new InsertCommand(id, guid, username, sUserService);

        AbstractCommand insertCommand = InsertCommand.of("Vloz (" + id + ", " + guid + ", " + username + ")", sUserService);

        assertEquals(expected, insertCommand);
    }
}