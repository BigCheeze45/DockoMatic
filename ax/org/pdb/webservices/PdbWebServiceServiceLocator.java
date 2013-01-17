# Program: "DockoMatic"
# Project: "DNA Safeguard"
# Filename: "dockOmatic.pl"
#
# Dr. Tim Andersen
# Department of Computer Science
# College of Engineering
# Boise State University
#
# Original Author(s): "Casey Bullock"
#
# Last Modified
#   Date: "June 14, 2010"
#
#
#  This file is part of DockoMatic.
#
#  DockoMatic is free software: you can redistribute it and/or modify
#  it under the terms of the GNU Lesser General Public License as published by
#  the Free Software Foundation, either version 3 of the License, or
#  (at your option) any later version.
#
#  DockoMatic is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU Lesser General Public License for more details.
#
#  You should have received a copy of the GNU Lesser General Public License
#  along with DockoMatic.  If not, see <http://www.gnu.org/licenses/>.
#

/**
 * PdbWebServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.pdb.webservices;

public class PdbWebServiceServiceLocator extends org.apache.axis.client.Service implements org.pdb.webservices.PdbWebServiceService {

    public PdbWebServiceServiceLocator() {
    }


    public PdbWebServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PdbWebServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for pdbws
    private java.lang.String pdbws_address = "http://www.pdb.org/pdb/services/pdbws";

    public java.lang.String getpdbwsAddress() {
        return pdbws_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String pdbwsWSDDServiceName = "pdbws";

    public java.lang.String getpdbwsWSDDServiceName() {
        return pdbwsWSDDServiceName;
    }

    public void setpdbwsWSDDServiceName(java.lang.String name) {
        pdbwsWSDDServiceName = name;
    }

    public org.pdb.webservices.PdbWebService getpdbws() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(pdbws_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getpdbws(endpoint);
    }

    public org.pdb.webservices.PdbWebService getpdbws(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.pdb.webservices.PdbwsSoapBindingStub _stub = new org.pdb.webservices.PdbwsSoapBindingStub(portAddress, this);
            _stub.setPortName(getpdbwsWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setpdbwsEndpointAddress(java.lang.String address) {
        pdbws_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.pdb.webservices.PdbWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                org.pdb.webservices.PdbwsSoapBindingStub _stub = new org.pdb.webservices.PdbwsSoapBindingStub(new java.net.URL(pdbws_address), this);
                _stub.setPortName(getpdbwsWSDDServiceName());
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
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("pdbws".equals(inputPortName)) {
            return getpdbws();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("webservices.pdb.org", "PdbWebServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("webservices.pdb.org", "pdbws"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("pdbws".equals(portName)) {
            setpdbwsEndpointAddress(address);
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
