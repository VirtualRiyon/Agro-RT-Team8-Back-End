package com.jsp.Agro_bootRT.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserIdNotFound extends RuntimeException {
	private String msg="Id Not Exist";
}
