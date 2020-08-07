package com.anyu.tiangou.oauthserve.integration;

/**
 * @author shkstart Administrator
 * @create 2020-07-31 17:33
 */
public class IntegrationAuthenticationContext {
    private static ThreadLocal<IntegrationAuthentication> holder = new ThreadLocal<>();

    public static void set(IntegrationAuthentication integrationAuthentication){
        holder.set(integrationAuthentication);
    }

    public static IntegrationAuthentication get(){
        return holder.get();
    }

    public static void clear(){
        holder.remove();
    }

}
