package com.digital.coffee_outletassessment.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * This class authorize the client , generate access token and refresh token
 */
@Configuration
@EnableAuthorizationServer
@Slf4j
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityConfig config;
    @Value("${clientId}")
    private String clientId;
    @Value("${secret}")
    private String secret;
    @Value("${authorizedGrantTypes:password,authorization_code,refresh_token}")
    private String[] authorizedGrantTypes;
    @Value("${jwt.signing-key:123}")
    private String jwtSigningKey;

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    /**
     *
     * @param clients  as ClientsDetailsServiceConfigurer  which is used for configure the client detail and
     *  its scope and resource
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientId).secret(config.bCryptPasswordEncoder().encode(secret))
                .authorizedGrantTypes(authorizedGrantTypes).scopes("read", "write")
                .resourceIds("resource-server-rest-api")
                .accessTokenValiditySeconds(180)//Access token is only valid for 3 minutes.
                .refreshTokenValiditySeconds(600);//Refresh token is only valid for 10 minutes.;
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .accessTokenConverter(defaultAccessTokenConverter());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(defaultAccessTokenConverter());
    }

    /**
     * This method create jwt token using jwtSignedkey
     * @return JwtAccessTokenConverter return as jwt access token
     */
    @Bean
    public JwtAccessTokenConverter defaultAccessTokenConverter() {
         log.info("defaultAccessTokenConverter is called");

        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(jwtSigningKey);
        return converter;
    }

}
