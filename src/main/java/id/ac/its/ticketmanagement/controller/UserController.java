package id.ac.its.ticketmanagement.controller;

import id.ac.its.ticketmanagement.model.User;
import id.ac.its.ticketmanagement.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User create(@RequestParam int id, @RequestParam String username) {
        return service.create(id, username);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable int id, @RequestParam String username) {
        return service.update(id, username);
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable int id) {
        return service.delete(id);
    }

    @GetMapping("/{id}")
    public User retrieve(@PathVariable int id) {
        return service.retrieve(id);
    }

    @GetMapping
    public Collection<User> list() {
        return service.list();
    }
}
