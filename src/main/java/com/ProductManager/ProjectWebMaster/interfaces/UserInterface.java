package com.ProductManager.ProjectWebMaster.interfaces;
import com.ProductManager.ProjectWebMaster.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserInterface extends CrudRepository<User, String>{
    /* Return a user details using theirs email */
    User findByEmail(String email);
}
