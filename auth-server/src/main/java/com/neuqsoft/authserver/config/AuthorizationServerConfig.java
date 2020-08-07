package com.neuqsoft.authserver.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

/**
 * 认证服务器配置类
 *
 * @author sunjiarui
 * @Date 2020/8/6
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * 令牌存储配置
     */
    private final TokenStore tokenStore;
    /**
     * 客户端详细服务
     */
    private final ClientDetailsService clientDetailsService;
    /**
     * 认证管理器
     */
    private final AuthenticationManager authenticationManager;
    /**
     * 授权码服务器
     */
    private final AuthorizationCodeServices authorizationCodeServices;
    /**
     * jwt Token解析器
     */
    private final JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * token默认有效期2小时
     */
    @Value("${accessToken.token.ValiditySeconds:7200}")
    int tokenSeconds;
    /**
     * refreshToken默认有效期3天
     */
    @Value("${accessToken.RefreshToken.ValiditySeconds:259200}")
    int refreshSeconds;

    /**
     * 客户端详情服务配置 （demo采用本地内存存储）
     *
     * @param clients 客户端
     * @throws Exception 异常
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()// 使用本地存储
                // 客户端id
                .withClient("client_1")
                // 客户端密码
                .secret(new BCryptPasswordEncoder().encode("123456"))
                // 客户端允许授权类型
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                // 客户端允许授权范围
                .scopes("all")
                // false跳转到授权页面，true不跳转，直接发令牌
                .autoApprove(false);
    }

    /**
     * 配置访问令牌端点
     *
     * @param endpoints 端点
     * @throws Exception 异常
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // 认证管理器
                .authenticationManager(authenticationManager)
                // 授权码服务
                .authorizationCodeServices(authorizationCodeServices)
                // 令牌管理服务
                .tokenStore(tokenStore)
                // 允许的方法
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    /**
     * 配置令牌端点安全约束
     *
     * @param security 安全选项
     * @throws Exception 异常
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // oauth/check_token公开
                .checkTokenAccess("permitAll()")
                // oauth/token_key 公开密钥
                .tokenKeyAccess("permitAll()")
                // 允许表单认证
                .allowFormAuthenticationForClients();
    }

    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setSupportRefreshToken(true);
        // 令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter));
        tokenServices.setTokenEnhancer(tokenEnhancerChain);
        // 令牌有效期
        tokenServices.setAccessTokenValiditySeconds(tokenSeconds);
        // 刷新令牌默认有效期
        tokenServices.setRefreshTokenValiditySeconds(refreshSeconds);
        return tokenServices;
    }


}
