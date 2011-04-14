/**
 * PdbWebServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.pdb.webservices;

public interface PdbWebServiceService extends javax.xml.rpc.Service {
    public java.lang.String getpdbwsAddress();

    public org.pdb.webservices.PdbWebService getpdbws() throws javax.xml.rpc.ServiceException;

    public org.pdb.webservices.PdbWebService getpdbws(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
