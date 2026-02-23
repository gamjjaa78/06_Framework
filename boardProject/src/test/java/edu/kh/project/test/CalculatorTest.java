//package edu.kh.project.test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//public class CalculatorTest {
//
//	private Calculator calculator = new Calculator();
//
//	// JUnit5는 기본적으로 테스트메서드마다 테스트클래스의 인스턴스 새로 생성
//	// @BeforeAll, @AfterAll 테스트 클래스 내 모든 테스트가 시작되기 전후 딱 한번만 실행돼야함
//	// ->static이 아니면 테스트 클래스 인스턴스가 생성되기도 전에 JUnit이 해당 메서드 호출할 방법 없음
//	// ->클래스가 메모리 로드될 때 딱 한번만 실행하기 위해 static 강제됨
//
//	@BeforeAll
//	public static void setUp() {
//		log.info("테스트시작");
//	}
//
//	@Test
//	public void testAdd() {
//		assertEquals(5, calculator.add(2, 3));
//	}
//
//	@Test
//	public void testSubstract() {
//		assertEquals(1, calculator.subtract(3, 2));
//	}
//
//	@Test
//	public void testMultiply() {
//		assertEquals(6, calculator.multiply(2, 3));
//	}
//
//	@Test
//	public void testDivide() {
//		assertEquals(2, calculator.divide(6, 3));
//	}
//
//	public void testDivideByZero() {
//		assertThrows(IllegalArgumentException.class, () -> calculator.divide(5, 1));
//		// 패스할려면 인자에 0
//	}
//
//	@AfterAll
//	public static void testComplete() {
//		log.info("모든 테스트완료");
//
//	}
//
//}
