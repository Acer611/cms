/**
 * DragonDirectoryInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.dragon.media.common.webservice;

public class DragonDirectoryInfo  implements java.io.Serializable {
    private String[] attributes;

    private java.util.Calendar creationTime;

    private String fullName;

    private Boolean hasChildren;

    private java.util.Calendar lastAccessTime;

    private java.util.Calendar lastWriteTime;

    private String name;

    private String webPath;

    public DragonDirectoryInfo() {
    }

    public DragonDirectoryInfo(
           String[] attributes,
           java.util.Calendar creationTime,
           String fullName,
           Boolean hasChildren,
           java.util.Calendar lastAccessTime,
           java.util.Calendar lastWriteTime,
           String name,
           String webPath) {
           this.attributes = attributes;
           this.creationTime = creationTime;
           this.fullName = fullName;
           this.hasChildren = hasChildren;
           this.lastAccessTime = lastAccessTime;
           this.lastWriteTime = lastWriteTime;
           this.name = name;
           this.webPath = webPath;
    }


    /**
     * Gets the attributes value for this DragonDirectoryInfo.
     *
     * @return attributes
     */
    public String[] getAttributes() {
        return attributes;
    }


    /**
     * Sets the attributes value for this DragonDirectoryInfo.
     *
     * @param attributes
     */
    public void setAttributes(String[] attributes) {
        this.attributes = attributes;
    }


    /**
     * Gets the creationTime value for this DragonDirectoryInfo.
     *
     * @return creationTime
     */
    public java.util.Calendar getCreationTime() {
        return creationTime;
    }


    /**
     * Sets the creationTime value for this DragonDirectoryInfo.
     *
     * @param creationTime
     */
    public void setCreationTime(java.util.Calendar creationTime) {
        this.creationTime = creationTime;
    }


    /**
     * Gets the fullName value for this DragonDirectoryInfo.
     *
     * @return fullName
     */
    public String getFullName() {
        return fullName;
    }


    /**
     * Sets the fullName value for this DragonDirectoryInfo.
     *
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    /**
     * Gets the hasChildren value for this DragonDirectoryInfo.
     *
     * @return hasChildren
     */
    public Boolean getHasChildren() {
        return hasChildren;
    }


    /**
     * Sets the hasChildren value for this DragonDirectoryInfo.
     *
     * @param hasChildren
     */
    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }


    /**
     * Gets the lastAccessTime value for this DragonDirectoryInfo.
     *
     * @return lastAccessTime
     */
    public java.util.Calendar getLastAccessTime() {
        return lastAccessTime;
    }


    /**
     * Sets the lastAccessTime value for this DragonDirectoryInfo.
     *
     * @param lastAccessTime
     */
    public void setLastAccessTime(java.util.Calendar lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }


    /**
     * Gets the lastWriteTime value for this DragonDirectoryInfo.
     *
     * @return lastWriteTime
     */
    public java.util.Calendar getLastWriteTime() {
        return lastWriteTime;
    }


    /**
     * Sets the lastWriteTime value for this DragonDirectoryInfo.
     *
     * @param lastWriteTime
     */
    public void setLastWriteTime(java.util.Calendar lastWriteTime) {
        this.lastWriteTime = lastWriteTime;
    }


    /**
     * Gets the name value for this DragonDirectoryInfo.
     *
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this DragonDirectoryInfo.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the webPath value for this DragonDirectoryInfo.
     *
     * @return webPath
     */
    public String getWebPath() {
        return webPath;
    }


    /**
     * Sets the webPath value for this DragonDirectoryInfo.
     *
     * @param webPath
     */
    public void setWebPath(String webPath) {
        this.webPath = webPath;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DragonDirectoryInfo)) return false;
        DragonDirectoryInfo other = (DragonDirectoryInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.attributes==null && other.getAttributes()==null) ||
             (this.attributes!=null &&
              java.util.Arrays.equals(this.attributes, other.getAttributes()))) &&
            ((this.creationTime==null && other.getCreationTime()==null) ||
             (this.creationTime!=null &&
              this.creationTime.equals(other.getCreationTime()))) &&
            ((this.fullName==null && other.getFullName()==null) ||
             (this.fullName!=null &&
              this.fullName.equals(other.getFullName()))) &&
            ((this.hasChildren==null && other.getHasChildren()==null) ||
             (this.hasChildren!=null &&
              this.hasChildren.equals(other.getHasChildren()))) &&
            ((this.lastAccessTime==null && other.getLastAccessTime()==null) ||
             (this.lastAccessTime!=null &&
              this.lastAccessTime.equals(other.getLastAccessTime()))) &&
            ((this.lastWriteTime==null && other.getLastWriteTime()==null) ||
             (this.lastWriteTime!=null &&
              this.lastWriteTime.equals(other.getLastWriteTime()))) &&
            ((this.name==null && other.getName()==null) ||
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.webPath==null && other.getWebPath()==null) ||
             (this.webPath!=null &&
              this.webPath.equals(other.getWebPath())));
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
        if (getAttributes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAttributes());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getAttributes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCreationTime() != null) {
            _hashCode += getCreationTime().hashCode();
        }
        if (getFullName() != null) {
            _hashCode += getFullName().hashCode();
        }
        if (getHasChildren() != null) {
            _hashCode += getHasChildren().hashCode();
        }
        if (getLastAccessTime() != null) {
            _hashCode += getLastAccessTime().hashCode();
        }
        if (getLastWriteTime() != null) {
            _hashCode += getLastWriteTime().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getWebPath() != null) {
            _hashCode += getWebPath().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DragonDirectoryInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/DragonUtility.IO.FileService.Contracts", "DragonDirectoryInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attributes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/DragonUtility.IO.FileService.Contracts", "Attributes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.IO", "FileAttributes"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creationTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/DragonUtility.IO.FileService.Contracts", "CreationTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fullName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/DragonUtility.IO.FileService.Contracts", "FullName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hasChildren");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/DragonUtility.IO.FileService.Contracts", "HasChildren"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastAccessTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/DragonUtility.IO.FileService.Contracts", "LastAccessTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastWriteTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/DragonUtility.IO.FileService.Contracts", "LastWriteTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/DragonUtility.IO.FileService.Contracts", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("webPath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/DragonUtility.IO.FileService.Contracts", "WebPath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
