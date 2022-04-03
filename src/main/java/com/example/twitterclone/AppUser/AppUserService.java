package com.example.twitterclone.AppUser;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public void addUser(AppUser appUser){
        appUser.setCreatedAt(LocalDateTime.now());
        appUser.setEnabled(false);

        appUserRepository.save(appUser);
    }

    public void deleteUser(Long id){
        appUserRepository.deleteById(id);
    }

    public void editUser(AppUser appUser){
        appUserRepository.findById(appUser.getId()).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));

        appUserRepository.save(appUser);
    }

    public Map<String,Object> findUsers(int page,int size){
        Pageable paging = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC,"createdAt"));
        Page<AppUser> users = appUserRepository.findAll(paging);

        Map<String ,Object> result = new HashMap<>();
        result.put("Current Page",users.getNumber());
        result.put("Total Pages",users.getTotalPages());
        result.put("Current Elements",users.getNumberOfElements());
        result.put("Total Elements",users.getTotalElements());
        result.put("data",users.getContent());

        return result;

    }

}
