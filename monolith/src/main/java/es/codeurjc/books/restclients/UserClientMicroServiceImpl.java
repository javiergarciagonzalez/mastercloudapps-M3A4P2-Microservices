package es.codeurjc.books.restclients;

import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.codeurjc.books.models.User;

public class UserClientMicroServiceImpl implements UserClient {

    private UserClientMicroService userClientMicroService;

    public UserClientMicroServiceImpl(UserClientMicroService userClientMicroService) {
        this.userClientMicroService = userClientMicroService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{nick}")
    public Optional<User> findByNick(@PathVariable("nick") String nick) {
        return userClientMicroService.findByNick(nick);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
    public Optional<User> findById(@PathVariable("userId") long userId) {
        return userClientMicroService.findById(userId);

    }

}