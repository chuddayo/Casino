package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.AccountManager;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class AccountManagerTest {

    Account account1 = new Account("Linda", "test", 5000);
    Account account2 = new Account("Steven", "test", 5000);

    @Test
    public void testGetAccount1() throws IOException {
        AccountManager mgr = new AccountManager();
        mgr.registerAccount(account1);
        Account account = mgr.getAccount("Linda", "test");
        Assert.assertEquals(account, account1);
    }

    @Test
    public void testGetAccount2() throws IOException {
        AccountManager mgr = new AccountManager();
        mgr.registerAccount(account2);
        Account account = mgr.getAccount("Steven", "test");
        Assert.assertEquals(account, account2);
    }

    @Test
    public void testCreateAccount() throws IOException {
        AccountManager mgr = new AccountManager();
        Account account = mgr.createAccount("Ryan", "test", 1000);
        Assert.assertEquals(account, mgr.getAccount("Ryan", "test"));
    }

    @Test
    public void testCreateAccountNoBal() throws IOException {
        AccountManager mgr = new AccountManager();
        Account account = mgr.createAccount("Ryan", "test");
        Assert.assertEquals(account, mgr.getAccount("Ryan", "test"));
    }
}
