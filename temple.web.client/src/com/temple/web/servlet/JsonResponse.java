package com.temple.web.servlet;

import com.temple.util.Charsets;
import com.temple.util.json.AbstractJsonable;
import com.temple.util.json.BooleanHandler;
import com.temple.util.json.JsonField;
import com.temple.util.json.MapHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

/**
 * Simple class to send a Json response.
 * 
 * @author flominou
 * @see #write(javax.servlet.http.HttpServletResponse) 
 */
public class JsonResponse extends AbstractJsonable {
	
	private final static String CONTENT_TYPE = "application/json";
	
	@JsonField(handler = BooleanHandler.class, inputable = false)
	public boolean success;
	
	@JsonField(inputable = false)
	public String msg;

	@JsonField(handler = MapHandler.class, inputable = false)
	private final Map<String, Object> data = new HashMap<>() ;
	
	/**
	 * Constructor
	 */
	public JsonResponse() {
		super() ;
	}

	/**
	 * Constructor
	 * TODOC
	 * @param success 
	 * @param msg 
	 */
	public JsonResponse(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}
	
	/**
	 * TODOC
	 * @param dataKey
	 * @param data 
	 * @throws NullPointerException if dataKey is {@code null}
	 */
	public void addData(Object dataKey, Object data) {
		this.data.put(dataKey.toString(), data) ;
	}
	
	/**
	 * Sets the response's status to OK, set the Json content type, character encoding to UTF-8 and writes this object's Json representation and flushes the output buffer.
	 * TODOC enhance
	 * @param response the servlet response
	 * @throws IOException 
	 */
	public final void write(HttpServletResponse response) throws IOException {
		final String json = this.toJsonObject().toString();
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType(JsonResponse.CONTENT_TYPE);
		response.setCharacterEncoding(Charsets.utf8.name());
		response.setContentLength(json.length()) ;
		response.getOutputStream().print(json);
		response.flushBuffer();
	}
	
}
