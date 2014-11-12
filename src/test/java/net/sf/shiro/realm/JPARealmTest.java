package net.sf.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/datasource-test-context.xml", "classpath:/jpa-test-context.xml",
        "classpath:/shiro-test-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetup("JPARealm-dataset.xml")
@DatabaseTearDown("JPARealm-dataset.xml")
public class JPARealmTest {

    @Autowired
    private SecurityManager securityManager;

    @Before
    public void setUp() {
        SecurityUtils.setSecurityManager(securityManager);
    }

    @Test
    public void loginTest() {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setUsername("admin");
        token.setPassword("1234".toCharArray());
        subject.login(token);

        Assert.assertTrue(subject.hasRole("administrator"));
        Assert.assertTrue(subject.isPermitted("post:update:5"));
    }

}
