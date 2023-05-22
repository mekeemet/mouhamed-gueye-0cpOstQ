package com.anywr.javasprintsecuritytest.Service.Interface;

import com.anywr.javasprintsecuritytest.Entity.Personn;
import com.anywr.javasprintsecuritytest.Entity.Role;

public interface AccountService
{
    public Personn savePersonn(Personn personn);
    public Role saveRole(Role role);
    public Personn loadUserByUsername(String username);
    public void addRoleToUser(String username,String rolename);
}
