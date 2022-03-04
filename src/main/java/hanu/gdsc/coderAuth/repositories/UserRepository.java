package hanu.gdsc.coderAuth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.share.domains.Id;

public interface UserRepository extends JpaRepository<User,Id>{
    public User getByUsername(String username);
    public User getByEmail(String email);
}
