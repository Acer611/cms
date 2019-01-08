/**
 * FileRepositoryServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.dragon.media.common.webservice;

public class FileRepositoryServiceLocator extends org.apache.axis.client.Service implements FileRepositoryService {

    public FileRepositoryServiceLocator() {
    }


    public FileRepositoryServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FileRepositoryServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_IFileRepositoryService
    private String BasicHttpBinding_IFileRepositoryService_address = "http://uploadfile.qikan.com.cn/FileRepositoryService.svc";

    public String getBasicHttpBinding_IFileRepositoryServiceAddress() {
        return BasicHttpBinding_IFileRepositoryService_address;
    }

    // The WSDD service name defaults to the port name.
    private String BasicHttpBinding_IFileRepositoryServiceWSDDServiceName = "BasicHttpBinding_IFileRepositoryService";

    public String getBasicHttpBinding_IFileRepositoryServiceWSDDServiceName() {
        return BasicHttpBinding_IFileRepositoryServiceWSDDServiceName;
    }

    public void setBasicHttpBinding_IFileRepositoryServiceWSDDServiceName(String name) {
        BasicHttpBinding_IFileRepositoryServiceWSDDServiceName = name;
    }

    public IFileRepositoryService getBasicHttpBinding_IFileRepositoryService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_IFileRepositoryService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_IFileRepositoryService(endpoint);
    }

    public IFileRepositoryService getBasicHttpBinding_IFileRepositoryService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            BasicHttpBinding_IFileRepositoryServiceStub _stub = new BasicHttpBinding_IFileRepositoryServiceStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_IFileRepositoryServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_IFileRepositoryServiceEndpointAddress(String address) {
        BasicHttpBinding_IFileRepositoryService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (IFileRepositoryService.class.isAssignableFrom(serviceEndpointInterface)) {
                BasicHttpBinding_IFileRepositoryServiceStub _stub = new BasicHttpBinding_IFileRepositoryServiceStub(new java.net.URL(BasicHttpBinding_IFileRepositoryService_address), this);
                _stub.setPortName(getBasicHttpBinding_IFileRepositoryServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("BasicHttpBinding_IFileRepositoryService".equals(inputPortName)) {
            return getBasicHttpBinding_IFileRepositoryService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "FileRepositoryService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpBinding_IFileRepositoryService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

if ("BasicHttpBinding_IFileRepositoryService".equals(portName)) {
            setBasicHttpBinding_IFileRepositoryServiceEndpointAddress(address);
        }
        else
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
