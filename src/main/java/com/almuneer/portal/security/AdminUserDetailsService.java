package com.almuneer.portal.security;

import com.almuneer.portal.model.AdminUser;
import com.almuneer.portal.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserDetailsService implements UserDetailsService {

    private final AdminUserRepository adminUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminUser admin = adminUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found: " + username));

        return new User(
                admin.getUsername(),
                admin.getPasswordHash(),
                List.of(new SimpleGrantedAuthority("ROLE_" + admin.getRole()))
        );
    }
}
