package com.dgmf.security;

import com.dgmf.entity.Person;
import com.dgmf.entity.Roles;
import com.dgmf.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EazySchoolUsernamePwdAuthenticationProvider
        implements AuthenticationProvider
{
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String email = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        // Retrieving Person Object from DB Based on User Credentials
        Person person = personRepository.readByEmail(email);
        // To Check if Entered User Credentials Matched DB Credentials
        if(null != person && person.getPersonId()>0 &&
                pwd.equals(person.getPwd())){
            return new UsernamePasswordAuthenticationToken(
                    person.getName(), pwd, getGrantedAuthorities(person.getRoles()));
        }else{
            throw new BadCredentialsException("Invalid credentials!");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(Roles roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(
                "ROLE_"+roles.getRoleName())
        );

        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
