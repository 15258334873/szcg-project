/**
 * MasWSImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mtmasws.service.impl;

import javax.xml.rpc.handler.HandlerRegistry;

public class MasWSImplServiceLocator extends org.apache.axis.client.Service implements com.mtmasws.service.impl.MasWSImplService {


    public MasWSImplServiceLocator() {
    }



    public MasWSImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MasWSImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MasWSImplPort
    private java.lang.String MasWSImplPort_address = "http://211.139.6.203:8010/mtmasws/service/MasWS";

    @Override
    public java.lang.String getMasWSImplPortAddress() {
        return MasWSImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MasWSImplPortWSDDServiceName = "MasWSImplPort";

    public java.lang.String getMasWSImplPortWSDDServiceName() {
        return MasWSImplPortWSDDServiceName;
    }

    public void setMasWSImplPortWSDDServiceName(java.lang.String name) {
        MasWSImplPortWSDDServiceName = name;
    }
    @Override
    public com.mtmasws.service.MasWS getMasWSImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MasWSImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMasWSImplPort(endpoint);
    }
    @Override
    public com.mtmasws.service.MasWS getMasWSImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.mtmasws.service.impl.MasWSImplServiceSoapBindingStub _stub = new com.mtmasws.service.impl.MasWSImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getMasWSImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMasWSImplPortEndpointAddress(java.lang.String address) {
        MasWSImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @Override
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.mtmasws.service.MasWS.class.isAssignableFrom(serviceEndpointInterface)) {
                com.mtmasws.service.impl.MasWSImplServiceSoapBindingStub _stub = new com.mtmasws.service.impl.MasWSImplServiceSoapBindingStub(new java.net.URL(MasWSImplPort_address), this);
                _stub.setPortName(getMasWSImplPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @Override
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("MasWSImplPort".equals(inputPortName)) {
            return getMasWSImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }
    @Override
    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.service.mtmasws.com/", "MasWSImplService");
    }

    private java.util.HashSet ports = null;
    @Override
    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.service.mtmasws.com/", "MasWSImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MasWSImplPort".equals(portName)) {
            setMasWSImplPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
