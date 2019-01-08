/**
 * IFileRepositoryService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.dragon.media.common.webservice;

public interface IFileRepositoryService extends java.rmi.Remote {
    public CommonResult existsDirectory(String dirPath) throws java.rmi.RemoteException;
    public CommonResult createDirectory(String dirPath) throws java.rmi.RemoteException;
    public CommonResult deleteDirectory(String dirPath) throws java.rmi.RemoteException;
    public CommonResult renameDirectory(String sourceDirName, String destDirName) throws java.rmi.RemoteException;
    public CommonResult existsFile(String filePath) throws java.rmi.RemoteException;
    public void createImageFile(byte[] fileData, javax.xml.rpc.holders.BooleanWrapperHolder isSuccess, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException;
    public CommonResult deleteFile(String filePath) throws java.rmi.RemoteException;
    public CommonResult renameFile(String sourceFileName, String destFileName) throws java.rmi.RemoteException;
    public DragonDirectoryInfo[] getDirectories(String dirPath) throws java.rmi.RemoteException;
    public DragonDirectoryInfo getDirectoryInfo(String dirPath) throws java.rmi.RemoteException;
    public DragonFileInfo[] getImageFiles(String dirPath) throws java.rmi.RemoteException;
    public DragonFileInfo[] searchImageFiles(String dirPath, String fileName) throws java.rmi.RemoteException;
    public byte[] getFileStream(String filePath) throws java.rmi.RemoteException;
}
