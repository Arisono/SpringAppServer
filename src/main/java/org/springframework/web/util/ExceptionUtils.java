package org.springframework.web.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

/**
 * @author Arison
 * 异常处理工具�? v1.0
 */
public class ExceptionUtils {

	public static void main(String[] args) {
		try {
			ArrayList<String> items=new ArrayList<String>();
			System.out.println(items.get(0));
		} catch (Exception e) {
			 System.out.println(printExceptionStack(e));
			 e.printStackTrace();
		}
	}
	
	
    /**
     * 打印异常�?,作废  see printExceptionStack
     * @param ex
     * @return 
     */
    @SuppressWarnings("unused")
	@Deprecated
	private static String printExceptionStackAll(Throwable ex){
		 StringBuilder sbBuilder=new StringBuilder();
		 sbBuilder.append(ex);
		 StackTraceElement[] trace = ex.getStackTrace();
         for (StackTraceElement traceElement : trace){
             //System.out.println("\tat " + traceElement);
             sbBuilder.append("\n         "+traceElement.toString());
         }
         Throwable ourCause = ex.getCause();
         System.err.println(ourCause);
         if (ourCause != null){
        	 System.out.println("cause:"+ourCause.getMessage());
         }
         return sbBuilder.toString();
	}
	
	/**
	 * @see 打印具体的异常信�?,打印异常�?
	 * @param ex
	 * @return string
	 */
	public static String printExceptionStack(Throwable ex) {
	    StringBuffer sb = new StringBuffer();
	    sb.append("----------------------异常信息打印�?�?-------------------------------------\n");
	    StringWriter writer = new StringWriter();
	    PrintWriter printWriter = new PrintWriter(writer);
	    ex.printStackTrace(printWriter);//关键方法 调用  printStackTrace(new WrappedPrintWriter(s));
	    
	    Throwable cause = ex.getCause();
	    while (cause != null) {
	        cause.printStackTrace(printWriter);
	        cause = cause.getCause();
	    }
	    printWriter.close();
	    String result = writer.toString();
	    sb.append(result);
	    sb.append("----------------------异常信息打印结束-------------------------------------");
	    return sb.toString();
	}
}
