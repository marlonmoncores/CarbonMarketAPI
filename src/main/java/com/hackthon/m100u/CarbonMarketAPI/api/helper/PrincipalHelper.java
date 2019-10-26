package com.hackthon.m100u.CarbonMarketAPI.api.helper;

import java.security.Principal;

public class PrincipalHelper {

    public static long getUser(Principal principal){
        return Long.parseLong(principal.getName());
    }
}
