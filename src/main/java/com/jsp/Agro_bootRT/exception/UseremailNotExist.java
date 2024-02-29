package com.jsp.Agro_bootRT.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UseremailNotExist extends RuntimeException{
	private String msg="User mail not found";
}
