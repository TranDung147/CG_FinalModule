package controllers.cg_finalmodule.service;

import controllers.cg_finalmodule.model.User;
import controllers.cg_finalmodule.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findByKeyword(String keyword) {
        return userRepository.searchByKeywordAndType(keyword, "all", PageRequest.of(0, Integer.MAX_VALUE)).getContent();
    }

    @Override
    public Page<User> findAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findByKeyword(String keyword, Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return userRepository.searchByKeywordAndType(keyword, "all", pageable);
    }

    @Override
    public Page<User> searchUsers(String keyword, String type, Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return userRepository.searchByKeywordAndType(keyword, type, pageable);
    }
}
