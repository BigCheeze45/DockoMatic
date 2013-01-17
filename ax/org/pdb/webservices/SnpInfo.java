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
 * SnpInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.pdb.webservices;

public class SnpInfo  implements java.io.Serializable {
    private java.lang.String chainId;

    private java.lang.String contigResidue;

    private java.lang.Integer flgIdentical;

    private java.lang.String masterResidue;

    private java.lang.Integer omimId;

    private java.lang.String snpId;

    private java.lang.String snpType;

    private java.lang.Integer structAaPos;

    private java.lang.String structId;

    private java.lang.String structResidue;

    public SnpInfo() {
    }

    public SnpInfo(
           java.lang.String chainId,
           java.lang.String contigResidue,
           java.lang.Integer flgIdentical,
           java.lang.String masterResidue,
           java.lang.Integer omimId,
           java.lang.String snpId,
           java.lang.String snpType,
           java.lang.Integer structAaPos,
           java.lang.String structId,
           java.lang.String structResidue) {
           this.chainId = chainId;
           this.contigResidue = contigResidue;
           this.flgIdentical = flgIdentical;
           this.masterResidue = masterResidue;
           this.omimId = omimId;
           this.snpId = snpId;
           this.snpType = snpType;
           this.structAaPos = structAaPos;
           this.structId = structId;
           this.structResidue = structResidue;
    }


    /**
     * Gets the chainId value for this SnpInfo.
     * 
     * @return chainId
     */
    public java.lang.String getChainId() {
        return chainId;
    }


    /**
     * Sets the chainId value for this SnpInfo.
     * 
     * @param chainId
     */
    public void setChainId(java.lang.String chainId) {
        this.chainId = chainId;
    }


    /**
     * Gets the contigResidue value for this SnpInfo.
     * 
     * @return contigResidue
     */
    public java.lang.String getContigResidue() {
        return contigResidue;
    }


    /**
     * Sets the contigResidue value for this SnpInfo.
     * 
     * @param contigResidue
     */
    public void setContigResidue(java.lang.String contigResidue) {
        this.contigResidue = contigResidue;
    }


    /**
     * Gets the flgIdentical value for this SnpInfo.
     * 
     * @return flgIdentical
     */
    public java.lang.Integer getFlgIdentical() {
        return flgIdentical;
    }


    /**
     * Sets the flgIdentical value for this SnpInfo.
     * 
     * @param flgIdentical
     */
    public void setFlgIdentical(java.lang.Integer flgIdentical) {
        this.flgIdentical = flgIdentical;
    }


    /**
     * Gets the masterResidue value for this SnpInfo.
     * 
     * @return masterResidue
     */
    public java.lang.String getMasterResidue() {
        return masterResidue;
    }


    /**
     * Sets the masterResidue value for this SnpInfo.
     * 
     * @param masterResidue
     */
    public void setMasterResidue(java.lang.String masterResidue) {
        this.masterResidue = masterResidue;
    }


    /**
     * Gets the omimId value for this SnpInfo.
     * 
     * @return omimId
     */
    public java.lang.Integer getOmimId() {
        return omimId;
    }


    /**
     * Sets the omimId value for this SnpInfo.
     * 
     * @param omimId
     */
    public void setOmimId(java.lang.Integer omimId) {
        this.omimId = omimId;
    }


    /**
     * Gets the snpId value for this SnpInfo.
     * 
     * @return snpId
     */
    public java.lang.String getSnpId() {
        return snpId;
    }


    /**
     * Sets the snpId value for this SnpInfo.
     * 
     * @param snpId
     */
    public void setSnpId(java.lang.String snpId) {
        this.snpId = snpId;
    }


    /**
     * Gets the snpType value for this SnpInfo.
     * 
     * @return snpType
     */
    public java.lang.String getSnpType() {
        return snpType;
    }


    /**
     * Sets the snpType value for this SnpInfo.
     * 
     * @param snpType
     */
    public void setSnpType(java.lang.String snpType) {
        this.snpType = snpType;
    }


    /**
     * Gets the structAaPos value for this SnpInfo.
     * 
     * @return structAaPos
     */
    public java.lang.Integer getStructAaPos() {
        return structAaPos;
    }


    /**
     * Sets the structAaPos value for this SnpInfo.
     * 
     * @param structAaPos
     */
    public void setStructAaPos(java.lang.Integer structAaPos) {
        this.structAaPos = structAaPos;
    }


    /**
     * Gets the structId value for this SnpInfo.
     * 
     * @return structId
     */
    public java.lang.String getStructId() {
        return structId;
    }


    /**
     * Sets the structId value for this SnpInfo.
     * 
     * @param structId
     */
    public void setStructId(java.lang.String structId) {
        this.structId = structId;
    }


    /**
     * Gets the structResidue value for this SnpInfo.
     * 
     * @return structResidue
     */
    public java.lang.String getStructResidue() {
        return structResidue;
    }


    /**
     * Sets the structResidue value for this SnpInfo.
     * 
     * @param structResidue
     */
    public void setStructResidue(java.lang.String structResidue) {
        this.structResidue = structResidue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SnpInfo)) return false;
        SnpInfo other = (SnpInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.chainId==null && other.getChainId()==null) || 
             (this.chainId!=null &&
              this.chainId.equals(other.getChainId()))) &&
            ((this.contigResidue==null && other.getContigResidue()==null) || 
             (this.contigResidue!=null &&
              this.contigResidue.equals(other.getContigResidue()))) &&
            ((this.flgIdentical==null && other.getFlgIdentical()==null) || 
             (this.flgIdentical!=null &&
              this.flgIdentical.equals(other.getFlgIdentical()))) &&
            ((this.masterResidue==null && other.getMasterResidue()==null) || 
             (this.masterResidue!=null &&
              this.masterResidue.equals(other.getMasterResidue()))) &&
            ((this.omimId==null && other.getOmimId()==null) || 
             (this.omimId!=null &&
              this.omimId.equals(other.getOmimId()))) &&
            ((this.snpId==null && other.getSnpId()==null) || 
             (this.snpId!=null &&
              this.snpId.equals(other.getSnpId()))) &&
            ((this.snpType==null && other.getSnpType()==null) || 
             (this.snpType!=null &&
              this.snpType.equals(other.getSnpType()))) &&
            ((this.structAaPos==null && other.getStructAaPos()==null) || 
             (this.structAaPos!=null &&
              this.structAaPos.equals(other.getStructAaPos()))) &&
            ((this.structId==null && other.getStructId()==null) || 
             (this.structId!=null &&
              this.structId.equals(other.getStructId()))) &&
            ((this.structResidue==null && other.getStructResidue()==null) || 
             (this.structResidue!=null &&
              this.structResidue.equals(other.getStructResidue())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getChainId() != null) {
            _hashCode += getChainId().hashCode();
        }
        if (getContigResidue() != null) {
            _hashCode += getContigResidue().hashCode();
        }
        if (getFlgIdentical() != null) {
            _hashCode += getFlgIdentical().hashCode();
        }
        if (getMasterResidue() != null) {
            _hashCode += getMasterResidue().hashCode();
        }
        if (getOmimId() != null) {
            _hashCode += getOmimId().hashCode();
        }
        if (getSnpId() != null) {
            _hashCode += getSnpId().hashCode();
        }
        if (getSnpType() != null) {
            _hashCode += getSnpType().hashCode();
        }
        if (getStructAaPos() != null) {
            _hashCode += getStructAaPos().hashCode();
        }
        if (getStructId() != null) {
            _hashCode += getStructId().hashCode();
        }
        if (getStructResidue() != null) {
            _hashCode += getStructResidue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SnpInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("webservices.pdb.org", "SnpInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chainId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "chainId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contigResidue");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contigResidue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flgIdentical");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flgIdentical"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("masterResidue");
        elemField.setXmlName(new javax.xml.namespace.QName("", "masterResidue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("omimId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "omimId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("snpId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "snpId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("snpType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "snpType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("structAaPos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "structAaPos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("structId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "structId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("structResidue");
        elemField.setXmlName(new javax.xml.namespace.QName("", "structResidue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
