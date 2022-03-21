package com.pb.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pb.demo.model.User;
import com.pb.demo.repo.UserRepo;

@Service
@Transactional

public class UserService {
	 private static final String REDIS_CACHE_VALUE = "user";
	 @Autowired
	 private UserRepo userrepo;
	 public Page<User> listAllUser(Pageable pageable) {
	        return userrepo.findAll(pageable);
	    }
	// @CachePut(value = REDIS_CACHE_VALUE)
	 public void saveUser(User user) {
	        userrepo.save(user);
	    }

	 @Cacheable(value = REDIS_CACHE_VALUE, key = "#id")
	 public User getUser(long id) {
	        return userrepo.findById(id).get();
	    }
	 @CacheEvict(value = REDIS_CACHE_VALUE, key = "#id")
	 public void deleteUser(long id) {
	        userrepo.deleteById(id);
	    }	 
}
