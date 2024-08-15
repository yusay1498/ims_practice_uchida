package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {
    //Pointcut記述 (どこに、どのように介入するかの記述)
    //Adviceを挿入したいメソッドを特定するための条件記述

    //execution(* com.example.demo.aop..*.*(..))
    //>> Adviceを挿入する対象メソッドの定義パターンを記述
    //   今回の例では、任意のクラス、任意のメソッド名、任意の引数パターンを持つメソッドを対象にしている

    //@target(org.springframework.web.bind.annotation.RestController)
    //>> Adviceを挿入する対象メソッドの所属クラスに設定されているアノテーションを条件にする
    //   今回の例では RestController が設定されていることを条件にしている
    @Around("""
            execution(* com.example.demo.aop..*.*(..))
            && @target(org.springframework.web.bind.annotation.RestController)
    """)
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println(joinPoint.getTarget().getClass());
//        System.out.println(joinPoint.getThis().getClass());
//        System.out.println(joinPoint.getSignature().getName());

        //挿入処理の内容
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

//        logger.debug("Invoke getById. args=[{}]", id);
        if (logger.isDebugEnabled()) {
            logger.debug("Invoke {}. args={} (Advice)",
                    joinPoint.getSignature().getName(),
                    joinPoint.getArgs());
        }

        Object returnValue = joinPoint.proceed();

        if (returnValue instanceof User user) {
            returnValue = new User(
                    user.id(),
                    user.email(),
                    user.name() + " san"
            );
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Invoked {}. returns={} (Advice)",
                    joinPoint.getSignature().getName(),
                    returnValue);
        }

        return returnValue;
    }
}
