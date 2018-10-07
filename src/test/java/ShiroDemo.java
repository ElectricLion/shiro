import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * @author:tanghui
 * @since 1.0
 */
public class ShiroDemo {
/*    public static void main(String[] args) {
        //1.创建安全管理器工厂
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath;shiro.ini");
        //2.创建安全管理器
        SecurityManager securityManager = factory.getInstance();
        //3.初始化SecurityUtils 工具类
        SecurityUtils.setSecurityManager(securityManager);
        //4.从SecurityUtils 工具中获取subject
        Subject subject = SecurityUtils.getSubject();

    }*/

    /**
     * 认证操作,判断该用户是否存在
     *
     * @param subject
     */
    public void login(Subject subject) {
        //authenticationToken 用于封装用户输入的账户信息
        AuthenticationToken token = new UsernamePasswordToken("name", "123456");
        try {
            subject.login(token);
            //如果login方法没有任何异常，代表认证成功
            System.out.println("登录成功");
        } catch (UnknownAccountException e) {
            System.out.println("账号不存在");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误，抛出IncorrectCredentialsException");
        } catch (Exception e) {
            //系统错误
            System.out.println("系统错误");
        }
    }

    /**
     * 基于资源的授权
     *
     * @param subject
     */
    public void isPermitted(Subject subject, String product) {
        //1.基于资源的授权
        //判断当前登录用户是否有"商品添加"功能
        //isPermitted();返回true,有权限，false;没有权限
        subject.isPermitted("productAdd");
    }

    /**
     * 基于资源的授权
     *
     * @param
     */
    public void hasRole(Subject subject, String roleName) {
        //1.基于资源的授权
        //判断当前登录用户是否有"商品添加"功能
        subject.hasRole("admin");
    }

}
