package es.wata.almansaj.wkapi.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponse {
	
	@JsonProperty("apiContent")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<?> content;

	@JsonProperty("apiResult")
	private String result;

	public ApiResponse() {
		super();
		this.content = new ArrayList<>();
	}

	public ApiResponse(List<?> content) {
		super();
		this.content = content;
	}

	public ApiResponse(String result) {
		super();
		this.content = new ArrayList<>();
		this.result = result;
	}

	public ApiResponse(List<?> content, String result) {
		super();
		this.content = content;
		this.result = result;
	}

}
