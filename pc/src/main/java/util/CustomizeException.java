package util;

/**
 * Created by Bruce-Jiang on 2016/9/29.
 * 自定义异常处理：
 * 	   包含了三个层面：>抛出的异常为默认异常，包含相应的异常描述信息
 *                     >抛出的异常包含自定义异常描述信息
 *                     >抛出的异常包含自定义异常描述信息以及异常对应的返回码
 */
public class CustomizeException  extends Exception{
    private String retCd;//异常对应的返回码
    private String msgDes;//异常对应的描述信息

    /**
     * 默认构造方法
     */
    public CustomizeException(){
        super();
    }

    /**
     * 保存一场描述信息
     * @param message 异常描述信息
     */
    public CustomizeException(String message){
        super();
        this.msgDes = message;
    }

    /**
     * 抛出异常，记录异常对应的返回码和异常描述信息
     * @param retCd 异常对应的返回码
     * @param msgDes 异常描述信息
     */
    public CustomizeException(String retCd, String msgDes){
        super();
        this.retCd = retCd;
        this.msgDes = msgDes;
    }

    public String getRetCd(){
        return retCd;
    }

    public String getMsgDes(){
        return msgDes;
    }
}
