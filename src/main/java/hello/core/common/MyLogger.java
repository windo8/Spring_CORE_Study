package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
//MyLogger 의 가짜 프록시 클래스를 만들어두고 HTTP request 와 상관없이 다른 빈에 미리 주입해 둘 수 있다
//CGLIB 라는 가짜 라이브러리로 상속받은 가짜 프록시 객체 생성후 주입
//가짜 프록시 객체는 실제 request scope 와는 관계가 없는 가짜이고, 내부에 단순한 위임 로직 존재 싱글톤 처럼 동작
// * 싱글톤 처럼 사용하는 것 같지만 다르게 동작하기에 결국 주의해야 한다 *
// * 이런 특별한 scope 는 꼭 필요한 곳에만 최소화해서 사용하자 유지보수 어려워짐 *
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
