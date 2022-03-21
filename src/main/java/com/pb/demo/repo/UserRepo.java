package com.pb.demo.repo;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pb.demo.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
	Page<User> findAll(Pageable pageable);
}
