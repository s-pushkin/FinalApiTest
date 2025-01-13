package com.example.creditservice;

import com.example.creditservice.TariffTest;
import com.example.creditservice.LoanOrderTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({TariffTest.class, LoanOrderTest.class})
public class SmokeSuite {
}