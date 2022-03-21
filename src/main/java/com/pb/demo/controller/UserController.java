package com.pb.demo.controller;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pb.demo.exceptions.ResourceNotFoundException;
import com.pb.demo.model.User;
import com.pb.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final String REDIS_CACHE_VALUE="user";
	
	@Autowired
	UserService userservice;

    @GetMapping("")
    public Page<User> getall(@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="3") int size) {
    	Pageable paging = PageRequest.of(page, size);
    	Page<User> temp = userservice.listAllUser(paging);
    	if(temp.isEmpty()== true)
    		throw new ResourceNotFoundException("No data present.");
    	else
    		
    		return temp;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@Valid @PathVariable long id) {
        try {
            User user = userservice.getUser(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("The requested ID was not found");
        }
    }
    
    @PostMapping("/")
    public void add(@Valid @RequestBody User user) {
        userservice.saveUser(user);
       
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody User user, @PathVariable long id) {
        try {
            User existUser = userservice.getUser(id);
            user.setId(id);            
            userservice.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
        	throw new ResourceNotFoundException("The requested ID was not found");
        }
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {

        userservice.deleteUser(id);
    }
}
