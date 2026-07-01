package eco.files.Services;

import eco.files.Models.UserPrincipal;
import eco.files.Models.Users;
import eco.files.Repository.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userDetails implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Users user= userRepo.findByUsername(username);
        if(user ==null){
            throw new UsernameNotFoundException("User not found");
        }
       return new UserPrincipal(user);
    }
}
