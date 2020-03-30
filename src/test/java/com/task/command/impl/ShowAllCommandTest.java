package com.task.command.impl;

import com.task.command.AbstractCommand;
import com.task.dao.bo.SUserBo;
import com.task.dao.service.SUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;

import static org.mockito.Mockito.*;

class ShowAllCommandTest {

    @Test
    @DisplayName("Show all test")
    public void processTest() {
        SUserService sUserService = Mockito.mock(SUserService.class);
        AbstractCommand showAllCommand = new ShowAllCommand(sUserService);

        showAllCommand.process();

        verify(sUserService).getAll();
    }

    @Test
    @DisplayName("Show all empty test")
    public void processEmptyTest() {
        Collection<SUserBo> sUserBoCollection = mock(Collection.class);
        SUserService sUserService = Mockito.mock(SUserService.class);

        when(sUserService.getAll()).thenReturn(sUserBoCollection);
        when(sUserBoCollection.isEmpty()).thenReturn(true);

        AbstractCommand showAllCommand = new ShowAllCommand(sUserService);

        showAllCommand.process();

        verify(sUserBoCollection, never()).forEach(any());
    }

    @Test
    @DisplayName("Show all non empty test")
    public void processNonEmptyTest() {
        Collection<SUserBo> sUserBoCollection = mock(Collection.class);
        SUserService sUserService = Mockito.mock(SUserService.class);

        when(sUserService.getAll()).thenReturn(sUserBoCollection);
        when(sUserBoCollection.isEmpty()).thenReturn(false);

        AbstractCommand showAllCommand = new ShowAllCommand(sUserService);

        showAllCommand.process();

        verify(sUserBoCollection).forEach(any());
    }
}