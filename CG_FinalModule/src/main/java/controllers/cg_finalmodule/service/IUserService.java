package controllers.cg_finalmodule.service;

import controllers.cg_finalmodule.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    List<User> findByKeyword(String keyword);
    Page<User> findAll(Integer pageNo);
    Page<User> findByKeyword(String keyword, Integer pageNo);
    Page<User> searchUsers(String keyword, String type, Integer pageNo);
}
