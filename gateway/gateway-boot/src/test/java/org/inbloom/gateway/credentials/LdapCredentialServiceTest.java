package org.inbloom.gateway.credentials;

import org.inbloom.gateway.configuration.LdapConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created By: paullawler
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LdapConfiguration.class)
public class LdapCredentialServiceTest {

    private LdapCredentialService service;



}
