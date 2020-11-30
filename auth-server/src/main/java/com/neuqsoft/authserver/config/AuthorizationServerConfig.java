package com.neuqsoft.authserver.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private final TokenStore tokenStore;

    /**
     * 认证管理器
     */
    @Autowired
    private final AuthenticationManager authenticationManager;
    /**
     * 授权码服务器
     */
    @Autowired
    private final AuthorizationCodeServices authorizationCodeServices;

    /**
     * token默认有效期2小时
     */
//    @Value("${tokenValidity.tokenSeconds:7200}")
//    private String tokenSeconds;
    /**
     * refreshToken默认有效期3天
     */
//    @Value("${tokenValidity.refreshSeconds:259200}")
//    private String refreshSeconds="259200";

    /**
     * 客户端详情服务配置 （demo采用本地内存存储）
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
        List<TokenEnhancer> delegates = new ArrayList<>();

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


}
