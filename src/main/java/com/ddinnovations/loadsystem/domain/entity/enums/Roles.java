package com.ddinnovations.loadsystem.domain.entity.enums;


import java.util.Arrays;
import java.util.List;

public enum Roles {
    ADMINISTRADOR(Arrays.asList(Permission.FULL)),
    VENDEDOR(Arrays.asList(Permission.GET,Permission.SAVE,Permission.UPDATE,Permission.PATCH));

    private List<Permission> permissions;
    Roles(List<Permission> permissions){
        this.permissions=permissions;
    }
    public List<Permission> getPermissions(){
        return permissions;
    }
}
