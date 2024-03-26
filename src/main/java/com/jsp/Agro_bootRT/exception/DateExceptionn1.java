package com.jsp.Agro_bootRT.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DateExceptionn1 extends RuntimeException {
	private String msg="Provide Valid Date AND Time";
}
