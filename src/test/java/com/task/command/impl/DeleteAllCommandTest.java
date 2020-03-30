package com.task.command.impl;

import com.task.command.AbstractCommand;
import com.task.dao.service.SUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteAllCommandTest {

    @Test
    @DisplayName("Delete all command test")
    public void processTest() {
        SUserService sUserService = mock(SUserService.class);
        AbstractCommand deleteAllCommand = new DeleteAllCommand(sUserService);

        deleteAllCommand.process();

        verify(sUserService).deleteAll();
    }

}