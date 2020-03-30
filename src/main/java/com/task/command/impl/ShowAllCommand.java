package com.task.command.impl;

import com.task.command.AbstractCommand;
import com.task.dao.bo.SUserBo;
import com.task.dao.service.SUserService;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * Command to show all database entries
 */
@Data
@RequiredArgsConstructor
public class ShowAllCommand extends AbstractCommand {

    @NonNull
    private SUserService sUserService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void process() {
        Collection<SUserBo> users = sUserService.getAll();

        // When not empty show users, otherwise show message that the database is empty
        if (CollectionUtils.isNotEmpty(users)) {
            showAll(users);
        } else {
            // no users in database
            System.out.println("Databaza je prazdna");
        }
    }

    private void showAll(Collection<SUserBo> sUserBos) {
        sUserBos.forEach(System.out::println);
    }
}
