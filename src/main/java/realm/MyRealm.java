package realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


/**
 * @author:tanghui
 * @since 1.0
 */
public class MyRealm extends AuthorizingRealm {

    /**
     * 认证方法，获取认证信息
     *
     * @param authenticationToken
     * @return null 值的话，shiro底层会自动抛出UnknowAccountException 未知账号异常
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //第一步：认证方法中在authenticationToken中拿到认证消息（获得登录时存储的用户信息与数据库中进行比较）
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        //第二步：去数据库中查询这个用户信息  /**
        //         * 从数据库中去根据这个用户名去查询，如果有该名字的用户则继续验证否则抛出UnknowAccoutException
        //         */
        String name = "admin";
        String password = "123";
        //第三步：判断用户是否存在，不存在返回null。底层抛出UnknowAccountException
        if (!username.equals(name)) {
            return null;// 返回null,shiro 底层自动抛出UnknowAccoutException
        }
        //第四步：判断密码.返回一个SimpleAuthenticatiionInfo对象
        /*
        参数一：principal,用于把数据回传到login方法。回调方法，如果认证通过，再将数据回传到登录方法进行登录处理。
        参数二：数据库的密码
        Shiro 底层对比密码的结果：
            1）密码正确： 认证通过
            2）密码不正确：自动抛出IncorrectCredentialsException
        参数三：realm的名称，只有在多个realm的时候才会使用
         */
        return new SimpleAuthenticationInfo("login", password, "");
    }
    /**
     * 重写授权方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加资源授权码
        principalCollection.getPrimaryPrincipal();
        /*
        应该将Web应用中有的权限和角色从数据库中读取出来
        然后调用 addStringPermissions()  将读取出来的权限和角色封装到SimpleAuthorizationInfo对象中
        并返回给授权管理器。
        当subject对象调用
        这时授权管理器会自动判断该用户的权限是否在这些权限里面。
        */
//        Subject subjey
        //以下的数据，是手动指定的，不是从数据库中获取。
        //info.addStringPermission("productAdd");
        //使用通配符授权
        info.addStringPermission("prodcut:*");
        //基于角色的授权
        info.addRole("admin");


        return info;
    }
}
