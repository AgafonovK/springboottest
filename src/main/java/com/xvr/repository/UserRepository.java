package com.xvr.repository;

import com.xvr.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <AppUser, String> {

    //@Query("from appuser a where a.userName = userName")

    Optional <AppUser> getAppUserByUserName (String userName);


}
