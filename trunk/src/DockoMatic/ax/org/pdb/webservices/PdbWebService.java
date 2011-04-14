/**
 * PdbWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.pdb.webservices;

public interface PdbWebService extends java.rmi.Remote {
    public int[] getEntities(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String blastPDB(java.lang.String in0, java.lang.String in1, double in2, java.lang.String in3, java.lang.String in4) throws java.rmi.RemoteException;
    public java.lang.String blastPDB(java.lang.String in0, double in1, java.lang.String in2, java.lang.String in3) throws java.rmi.RemoteException;
    public java.lang.String getIdStatus(java.lang.String in0) throws java.rmi.RemoteException;
    public org.pdb.webservices.Atom_siteWS getAtomSite(java.lang.String in0) throws java.rmi.RemoteException;
    public org.pdb.webservices.Atom_siteWS[] getAtomSites(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String[] fastaStructureIdQuery(java.lang.String in0, java.lang.String in1, double in2) throws java.rmi.RemoteException;
    public java.lang.String[] fastaQuery(java.lang.String in0, double in1) throws java.rmi.RemoteException;
    public java.lang.String[] keywordQuery(java.lang.String in0, boolean in1, boolean in2) throws java.rmi.RemoteException;
    public java.lang.String[] pubmedAbstractQuery(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String getSpaceGroupForStructure(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String[] getPubmedIdForAllStructures() throws java.rmi.RemoteException;
    public java.lang.String[] getStructureGenomicsPdbIds() throws java.rmi.RemoteException;
    public java.lang.String[] getSwissProtIds() throws java.rmi.RemoteException;
    public java.lang.String[] getGenomeDetails() throws java.rmi.RemoteException;
    public java.lang.String getPubmedIdForStructure(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String getSequenceForStructureAndChain(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public java.lang.String getPrimaryCitationTitle(java.lang.String in0) throws java.rmi.RemoteException;
    public int isStructureIdObsolete(java.lang.String in0) throws java.rmi.RemoteException;
    public org.pdb.webservices.SnpInfo[] getSnpInfo(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String[] getSnps() throws java.rmi.RemoteException;
    public java.lang.String[] getReleaseDates(java.lang.String[] in0) throws java.rmi.RemoteException;
    public java.lang.String[] getEcNumsForStructures(java.lang.String[] in0) throws java.rmi.RemoteException;
    public java.lang.String[] getEcNums() throws java.rmi.RemoteException;
    public java.lang.String getCifChain(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public int getCifResidue(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException;
    public java.lang.String[] getChains(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String[] getCifChains(java.lang.String in0) throws java.rmi.RemoteException;
    public int getChainLength(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public int getCifChainLength(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public java.lang.String getKabschSander(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public java.lang.String getDisorder(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public java.lang.String[] getDbRefIds(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException;
    public java.lang.String getPdbChain(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public java.lang.String[] runXmlQuery(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String[] getCurrentPdbIds() throws java.rmi.RemoteException;
    public java.lang.String[] getObsoletePdbIds() throws java.rmi.RemoteException;
    public org.pdb.webservices.DomainFragmentWS[] getDomainFragments(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException;
    public java.lang.String blastQueryXml(java.lang.String in0, double in1) throws java.rmi.RemoteException;
    public java.lang.String blastStructureIdQueryXml(java.lang.String in0, java.lang.String in1, double in2) throws java.rmi.RemoteException;
}
