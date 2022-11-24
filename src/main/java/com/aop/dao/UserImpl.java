package com.aop.dao;

import com.aop.log.Logging;
import com.aop.users.Topic;
import com.aop.users.User;

import java.util.ArrayList;
import java.util.List;

public class UserImpl implements DAO<User> {


    private static final List<User> users = new ArrayList<>();


    @Logging
    @Override
    public User getByID(Long id) {
        final User[] result = {null};
        users.forEach(user -> {
            if(user.getId() == id) {
               result[0] = user;
            }

        });
        return result[0];
    }

    @Logging
    @Override
    public void save(User user) {
        users.add(user);
    }

    @Logging
    @Override
    public List<User> getAll() {
        return users;
    }

    @Logging
    public void addTopicToUser(Topic topic, Long Id){
        User user = getByID(Id);
        user.getTopics().add(topic);
    }


}
