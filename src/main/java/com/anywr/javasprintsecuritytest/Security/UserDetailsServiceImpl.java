package com.anywr.javasprintsecuritytest.Security;

import com.anywr.javasprintsecuritytest.Entity.Personn;
import com.anywr.javasprintsecuritytest.Service.Interface.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    public UserDetailsServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    private final AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Personn appUser=accountService.loadUserByUsername(username);
        if(appUser==null) throw new UsernameNotFoundException("Invalid User");
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        appUser.getRoles().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });
        return new User(appUser.getUsername(),appUser.getPassword(),authorities);
    }
}
