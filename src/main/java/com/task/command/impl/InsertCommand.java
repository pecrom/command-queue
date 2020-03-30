package com.task.command.impl;

import com.task.command.AbstractCommand;
import com.task.command.impl.exception.SyntaxErrorException;
import com.task.dao.service.SUserService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Insert new user command
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class InsertCommand extends AbstractCommand {
    // number of command arguments
    private static final int ARGUMENTS_COUNT = 3;

    // index of id data
    private static final int USER_ID_INDEX = 0;

    // index of guid data
    private static final int USER_GUID_INDEX = 1;

    // index of username
    private static final int USER_NAME_INDEX = 2;

    // pattern matching argument part of insert command eg. (1, "a1", "Dusan")
    private static final Pattern ARGUMENT_PART_PATTERN = Pattern.compile("\\(.*\\)");

    // user id
    @NonNull
    @EqualsAndHashCode.Include
    private Long userId;

    // user guid
    @NonNull
    @EqualsAndHashCode.Include
    private String userGuid;

    // user name
    @NonNull
    @EqualsAndHashCode.Include
    private String username;

    // service processing command
    @NonNull
    private SUserService sUserService;

    /**
     * Create new Insert command
     *
     * @param commandWithArgs provided command with arguments
     * @param sUserService service processing command
     * @return new InsertCommand
     */
    public static InsertCommand of(String commandWithArgs, SUserService sUserService) {
        // get arguments part of command
        String args = findArguments(commandWithArgs);

        // get arguments in array
        String[] argsArray = parseArgsArray(args);

        return new InsertCommand(Long.parseLong(argsArray[USER_ID_INDEX]), argsArray[USER_GUID_INDEX], argsArray[USER_NAME_INDEX], sUserService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void process() {
        sUserService.addUser(userId, userGuid, username);
    }

    private static String findArguments(String commandWithArgs) {
        Matcher matcher = ARGUMENT_PART_PATTERN.matcher(commandWithArgs);

        // try to find arguments
        String args;
        if (matcher.find()) {
            // found arguments
            args = matcher.group();
        } else {
            // no arguments found
            throw new SyntaxErrorException();
        }

        return args;
    }

    private static String[] parseArgsArray(String args) {

        // get rid of parentheses and split by comma
        String[] argsArray = args.substring(1, args.length() - 1).split(",");

        // array for arguments
        String[] parsedArgs = new String[ARGUMENTS_COUNT];

        // create String array of arguments
        parsedArgs = Stream.of(argsArray)
                            .map(String::trim)  // get rid of white chars
                            .collect(Collectors.toList())   // create list of trimmed strings
                            .toArray(parsedArgs);   // convert to array

        return parsedArgs;
    }

}
