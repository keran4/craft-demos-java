package com.example.functional.tests;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(classes = TestBase.class)
@ComponentScan(basePackages = {"com.example.functional.tests"})
@TestPropertySource(locations = {"classpath:env/${test.active.profile:local}.properties"})
public class TestBase  extends AbstractTestNGSpringContextTests {

    @Value("${host}")
    protected String host;
}

