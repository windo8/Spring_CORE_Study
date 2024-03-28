package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 설정 정보 클래스의 위치를 프로젝트 최상단(package hello.core)에 두는 것을 권장한다 최상단에 두면 basePackages = ??? 지정 생략
        //@Component 붙은 것 들은 스캔하지 않음 기존 에제를 유지하기 위해 -> AppConfig.class
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
