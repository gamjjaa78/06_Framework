package edu.kh.project;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class BoardProjectApplicationTests {

	//ApplicationContext : Spring 컨테니저, 앱에 필요한 Been, DI 등의 처리 관리하는 인터페이스
	@Autowired
	private ApplicationContext applicationContext;
	
	//서버가 정상적으로 로드됐다면 컨텍스트가 null이 아닌 값으로 출력(DB설정, Been설정 등 모두 정상)
	@Test
	void contextLoads() {
		
		assertThat(applicationContext).isNotNull();
	}

}
