package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
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

    /*
    * AutoAppConfigTest 자동 등록, 수동 등록 충돌시 수동 빈 등록이 우선권
    * 사실상 여러 결과들이 꼬여서 이런 경우가 만들어진다 자동 / 수동 충돌
    * 충돌나면 요즘 스프링에서는 실행시 오류를 발생시킴
    */
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
