package controllers.cg_finalmodule.repository;

import controllers.cg_finalmodule.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE " +
            "(:type = 'all' AND (LOWER(u.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR u.phone LIKE CONCAT('%', :keyword, '%') OR LOWER(u.work) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%')))) " +
            "OR (:type = 'name' AND LOWER(u.fullName) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "OR (:type = 'phone' AND u.phone LIKE CONCAT('%', :keyword, '%')) " +
            "OR (:type = 'work' AND LOWER(u.work) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "OR (:type = 'email' AND LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<User> searchByKeywordAndType(String keyword, String type, Pageable pageable);

}
