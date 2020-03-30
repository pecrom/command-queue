package com.task.command.parser;

import com.task.command.AbstractCommand;
import com.task.command.impl.DeleteAllCommand;
import com.task.command.impl.InsertCommand;
import com.task.command.impl.ShowAllCommand;
import com.task.command.parser.exception.CommandNotFoundException;
import com.task.dao.service.SUserService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class CommandParserTest {

    @Test
    @DisplayName("Test parse show command")
    void parseCommandShowTest() {
        SUserService mockSUserService = mock(SUserService.class);
        AbstractCommand expected = new ShowAllCommand(mockSUserService);

        CommandParser parser = CommandParser.getInstance(mockSUserService);
        AbstractCommand showCommand = parser.parseCommand("Vypis");

        Assert.assertEquals(expected, showCommand);
    }

    @Test
    @DisplayName("Test parse delete command")
    void parseCommandDeleteTest() {
        SUserService mockSUserService = mock(SUserService.class);
        AbstractCommand expected = new DeleteAllCommand(mockSUserService);

        CommandParser parser = CommandParser.getInstance(mockSUserService);
        AbstractCommand deleteAllCommand = parser.parseCommand("ZmazVsetko");

        Assert.assertEquals(expected, deleteAllCommand);
    }

    @Test
    @DisplayName("Test parse insert command")
    void parseCommandInsertTest() {
        Long id = 1L;
        String guid = "guid";
        String username = "username";

        SUserService mockSUserService = mock(SUserService.class);
        AbstractCommand expected = new InsertCommand(id, guid, username, mockSUserService);

        CommandParser parser = CommandParser.getInstance(mockSUserService);
        AbstractCommand insertCommand = parser.parseCommand("Vloz (" + id + ", " + guid + ", " + username + ")");

        Assert.assertEquals(expected, insertCommand);
    }

    @Test
    @DisplayName("Test parse command not found")
    void parseCommandNotFoundTest() {
        SUserService mockSUserService = mock(SUserService.class);

        CommandParser parser = CommandParser.getInstance(mockSUserService);
        Assertions.assertThrows(CommandNotFoundException.class, () -> parser.parseCommand("notfound"));
    }
}