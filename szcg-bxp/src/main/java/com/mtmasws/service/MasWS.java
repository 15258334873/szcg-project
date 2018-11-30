/**
 * MasWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mtmasws.service;

public interface MasWS extends java.rmi.Remote {
    public java.lang.String sendCXMsgs(java.lang.String username, java.lang.String password, java.lang.String desphone, java.lang.String subject, java.lang.String XMLString, java.lang.String rem1, java.lang.String rem2) throws java.rmi.RemoteException;
    public java.lang.String getUpMsgs(java.lang.String username, java.lang.String password, java.lang.String phonetype, java.lang.String flag, java.lang.String rem1, java.lang.String rem2) throws java.rmi.RemoteException;
    public java.lang.String sendMsgs(java.lang.String username, java.lang.String password, java.lang.String desphone, java.lang.String msg, java.lang.String extendnumber, java.lang.String taskname, java.lang.String reserve1, java.lang.String reserve2) throws java.rmi.RemoteException;
    public java.lang.String sendMsg(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, int arg4, java.lang.String arg5, java.lang.String arg6, java.lang.String arg7) throws java.rmi.RemoteException;
    public java.lang.String getUserSms(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.rmi.RemoteException;
    public java.lang.String getReplyMsgs(java.lang.String username, java.lang.String password, java.lang.String phonetype, java.lang.String rem1) throws java.rmi.RemoteException;
    public boolean selHdQg(java.lang.String phone) throws java.rmi.RemoteException;
    public java.lang.String sendGzgdMsg(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.lang.String arg4, java.lang.String arg5, java.lang.String arg6, java.lang.String arg7, java.lang.String arg8) throws java.rmi.RemoteException;
    public java.lang.String sendMsgSxt(java.lang.String username, java.lang.String password, java.lang.String desphone, java.lang.String msg, java.lang.String msgtype, java.lang.String taskname, java.lang.String rem1, java.lang.String rem2) throws java.rmi.RemoteException;
}
