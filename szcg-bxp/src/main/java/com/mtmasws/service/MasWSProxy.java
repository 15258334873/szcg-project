package com.mtmasws.service;

public class MasWSProxy implements com.mtmasws.service.MasWS {
  private String _endpoint = null;
  private com.mtmasws.service.MasWS masWS = null;
  
  public MasWSProxy() {
    _initMasWSProxy();
  }
  
  public MasWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initMasWSProxy();
  }
  
  private void _initMasWSProxy() {
    try {
      masWS = (new com.mtmasws.service.impl.MasWSImplServiceLocator()).getMasWSImplPort();
      if (masWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)masWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)masWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (masWS != null)
      ((javax.xml.rpc.Stub)masWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.mtmasws.service.MasWS getMasWS() {
    if (masWS == null)
      _initMasWSProxy();
    return masWS;
  }
  
  public java.lang.String sendCXMsgs(java.lang.String username, java.lang.String password, java.lang.String desphone, java.lang.String subject, java.lang.String XMLString, java.lang.String rem1, java.lang.String rem2) throws java.rmi.RemoteException{
    if (masWS == null)
      _initMasWSProxy();
    return masWS.sendCXMsgs(username, password, desphone, subject, XMLString, rem1, rem2);
  }
  
  public java.lang.String getUpMsgs(java.lang.String username, java.lang.String password, java.lang.String phonetype, java.lang.String flag, java.lang.String rem1, java.lang.String rem2) throws java.rmi.RemoteException{
    if (masWS == null)
      _initMasWSProxy();
    return masWS.getUpMsgs(username, password, phonetype, flag, rem1, rem2);
  }
  
  public java.lang.String sendMsgs(java.lang.String username, java.lang.String password, java.lang.String desphone, java.lang.String msg, java.lang.String extendnumber, java.lang.String taskname, java.lang.String reserve1, java.lang.String reserve2) throws java.rmi.RemoteException{
    if (masWS == null)
      _initMasWSProxy();
    return masWS.sendMsgs(username, password, desphone, msg, extendnumber, taskname, reserve1, reserve2);
  }
  
  public java.lang.String sendMsg(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, int arg4, java.lang.String arg5, java.lang.String arg6, java.lang.String arg7) throws java.rmi.RemoteException{
    if (masWS == null)
      _initMasWSProxy();
    return masWS.sendMsg(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
  }
  
  public java.lang.String getUserSms(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.rmi.RemoteException{
    if (masWS == null)
      _initMasWSProxy();
    return masWS.getUserSms(arg0, arg1, arg2, arg3);
  }
  
  public java.lang.String getReplyMsgs(java.lang.String username, java.lang.String password, java.lang.String phonetype, java.lang.String rem1) throws java.rmi.RemoteException{
    if (masWS == null)
      _initMasWSProxy();
    return masWS.getReplyMsgs(username, password, phonetype, rem1);
  }
  
  public boolean selHdQg(java.lang.String phone) throws java.rmi.RemoteException{
    if (masWS == null)
      _initMasWSProxy();
    return masWS.selHdQg(phone);
  }
  
  public java.lang.String sendGzgdMsg(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.lang.String arg4, java.lang.String arg5, java.lang.String arg6, java.lang.String arg7, java.lang.String arg8) throws java.rmi.RemoteException{
    if (masWS == null)
      _initMasWSProxy();
    return masWS.sendGzgdMsg(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
  }
  
  public java.lang.String sendMsgSxt(java.lang.String username, java.lang.String password, java.lang.String desphone, java.lang.String msg, java.lang.String msgtype, java.lang.String taskname, java.lang.String rem1, java.lang.String rem2) throws java.rmi.RemoteException{
    if (masWS == null)
      _initMasWSProxy();
    return masWS.sendMsgSxt(username, password, desphone, msg, msgtype, taskname, rem1, rem2);
  }
  
  
}