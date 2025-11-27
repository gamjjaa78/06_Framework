package edu.kh.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Spring EL 같은 경우 DTO 객체출력시 getter 가 필수작성돼야함!
//->${std.name}==${Student.getName()}
//내부적으로 해당 DTO의 getter 를 호출하고 있기 때문!

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	private String studentNo;
	private String name;
	private int age;
}
