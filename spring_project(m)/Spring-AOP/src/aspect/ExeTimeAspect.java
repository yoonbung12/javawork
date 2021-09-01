package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

public class ExeTimeAspect {
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.nanoTime();
		try {
			Object result = joinPoint.proceed(); // Advice가 적용될 객체 호출
			return result;
		} finally {
			long finish = System.nanoTime();
			// 호출되는 메서드 정보를 구함.
			Signature sig = joinPoint.getSignature();
			System.out.printf("%s.%s 실행 시간 : %d ns\n", joinPoint.getTarget().getClass().getSimpleName(), sig.getName(),
					(finish - start));
		}
	}
}
