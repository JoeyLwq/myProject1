package aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyAspect {

    @Pointcut("execution(* getAll(..))")
    private void tryA(){
        System.out.println("试一下spring切面");
    }
}
