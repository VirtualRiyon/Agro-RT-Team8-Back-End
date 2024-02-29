package com.jsp.Agro_bootRT.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SqlException extends SQLIntegrityConstraintViolationException {
	private String msg="Duplicating Happening";
}
