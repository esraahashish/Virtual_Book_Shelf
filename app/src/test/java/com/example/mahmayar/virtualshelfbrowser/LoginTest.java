package com.example.mahmayar.virtualshelfbrowser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    private AuthenticationSystem as;


    @Test
    public void testAdminLogin(){
        as = new AuthenticationSystem();
        assertEquals(true, as.verify("admin", "123456"));
    }


    @Test
    public void testIncorrectUsername() {
        as = new AuthenticationSystem();
        assertEquals(false, as.verify("mayar", "123456"));
    }


    @Test
    public void testIncorrectPassword() {
        as = new AuthenticationSystem();
        assertEquals(false, as.verify("admin", "1234567"));
    }

}
