package com.iKMAK.service;

import com.iKMAK.dao.UserRepository;
import com.iKMAK.model.Role;
import com.iKMAK.model.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Leaf.Ye on 2017/3/15.
 */
@Service
public class UserService {

    @Inject
    private UserRepository userRepository;

    public List<Role> getRolesByUsername(String username){
        return null;
    }

    public List<String> getPermTokensByUsername(String username){
        return null;
    }

    public User getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }

}
