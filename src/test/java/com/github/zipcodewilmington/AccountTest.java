package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.Account;
import org.junit.Assert;
import org.junit.Test;

public class AccountTest {

    Account account1 = new Account("linda", "hi", 1000);

    @Test
    public void testGetUserName()  {
        Assert.assertEquals("linda", account1.getUserName());
    }

    @Test
    public void testGetPassword() {
        Assert.assertEquals("hi", account1.getPassword());
    }

    @Test
    public void testGetBalance() {
        Assert.assertEquals(1000, account1.getBalance());
    }

    @Test
    public void testSetBalance() {
        account1.setBalance(2000);
        Assert.assertEquals(2000, account1.getBalance());
    }

    @Test
    public void testCheckBalance() {
        // Check the account can place a bet of 1000
        Assert.assertTrue(account1.checkBalance(1000));
    }

    @Test
    public void testCheckBalance2() {
        // Check the account can place a bet of 10000
        Assert.assertFalse(account1.checkBalance(10000));
    }

    @Test
    public void testAddBalance() {
        account1.addBalance(1000);
        Assert.assertEquals(2000, account1.getBalance());
    }

    @Test
    public void testDeductBalance() {
        account1.deductBalance(1000);
        Assert.assertEquals(0, account1.getBalance());
    }
}
