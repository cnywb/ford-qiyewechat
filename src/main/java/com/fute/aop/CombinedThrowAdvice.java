package com.fute.aop;

import java.lang.reflect.Method;
import org.springframework.aop.ThrowsAdvice;

public abstract interface CombinedThrowAdvice extends ThrowsAdvice
{
  public abstract void afterThrowing(Method paramMethod, Object[] paramArrayOfObject, Object paramObject, Exception paramException)
    throws Throwable;
}