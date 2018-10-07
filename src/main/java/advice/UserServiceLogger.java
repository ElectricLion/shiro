package advice;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;


/**
 * @author:tanghui
 * @since 1.0
 */
//使用注解定义切面
@Aspect
public class UserServiceLogger {
    private static final Logger log = Logger.getLogger(UserServiceLogger.class);

    /**
     * 注解定义切入点

    @Pointcut("execution(* service.UserService.*(..))")
    public void pointout() {
    }*/

    /**
     * 注解定义前置增强
     *
     * @param joinPoint

    @Before("pointout()")
    public void before(JoinPoint joinPoint) {
        log.info("执行了UserService");
    }

    /**
     * 注解定义后置增强
     *
     * @param jp
     * @param returnValue

    @AfterReturning(value = "pointout()", returning = "returnValue")
    public void afterReturning(JoinPoint jp, Object returnValue) {
        log.info("执行UserService完毕");
    }
     */

}
