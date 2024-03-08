package com.jsp.Agro_bootRT.util;

import java.util.List;

import com.jsp.Agro_bootRT.entity.Post;
import com.jsp.Agro_bootRT.entity.User;

import lombok.Data;

@Data
public class ResponseStructure <T> {
	private String msg;
	private int status;
	private T data;
	private List<T> listdata;
}
