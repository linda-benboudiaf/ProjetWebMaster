package com.ProductManager.ProjectWebMaster.interfaces;
import org.springframework.data.repository.CrudRepository;
import com.ProductManager.ProjectWebMaster.models.Role;

public interface RoleInterface extends CrudRepository<Role, String>{
    /* Return a user details by its role. */
    Role findByRole(String role);
}
