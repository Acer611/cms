/**
 * IFileRepositoryService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public interface IFileRepositoryService extends java.rmi.Remote {
    public webservice.CommonResult existsDirectory(java.lang.String dirPath) throws java.rmi.RemoteException;
    public webservice.CommonResult createDirectory(java.lang.String dirPath) throws java.rmi.RemoteException;
    public webservice.CommonResult deleteDirectory(java.lang.String dirPath) throws java.rmi.RemoteException;
    public webservice.CommonResult renameDirectory(java.lang.String sourceDirName, java.lang.String destDirName) throws java.rmi.RemoteException;
    public webservice.CommonResult existsFile(java.lang.String filePath) throws java.rmi.RemoteException;
    public void createImageFile(byte[] fileData, javax.xml.rpc.holders.BooleanWrapperHolder isSuccess, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException;
    public webservice.CommonResult deleteFile(java.lang.String filePath) throws java.rmi.RemoteException;
    public webservice.CommonResult renameFile(java.lang.String sourceFileName, java.lang.String destFileName) throws java.rmi.RemoteException;
    public webservice.DragonDirectoryInfo[] getDirectories(java.lang.String dirPath) throws java.rmi.RemoteException;
    public webservice.DragonDirectoryInfo getDirectoryInfo(java.lang.String dirPath) throws java.rmi.RemoteException;
    public webservice.DragonFileInfo[] getImageFiles(java.lang.String dirPath) throws java.rmi.RemoteException;
    public webservice.DragonFileInfo[] searchImageFiles(java.lang.String dirPath, java.lang.String fileName) throws java.rmi.RemoteException;
    public byte[] getFileStream(java.lang.String filePath) throws java.rmi.RemoteException;
}
