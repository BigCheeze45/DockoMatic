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
 * Atom_siteWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.pdb.webservices;

public class Atom_siteWS  extends org.pdb.webservices.AtomCoords  implements java.io.Serializable {
    public Atom_siteWS() {
    }

    public Atom_siteWS(
           java.lang.String adp_type,
           java.lang.Float aniso_B11,
           java.lang.Float aniso_B11_esd,
           java.lang.Float aniso_B12,
           java.lang.Float aniso_B12_esd,
           java.lang.Float aniso_B13,
           java.lang.Float aniso_B13_esd,
           java.lang.Float aniso_B22,
           java.lang.Float aniso_B22_esd,
           java.lang.Float aniso_B23,
           java.lang.Float aniso_B23_esd,
           java.lang.Float aniso_B33,
           java.lang.Float aniso_B33_esd,
           java.lang.Float aniso_U11,
           java.lang.Float aniso_U11_esd,
           java.lang.Float aniso_U12,
           java.lang.Float aniso_U12_esd,
           java.lang.Float aniso_U13,
           java.lang.Float aniso_U13_esd,
           java.lang.Float aniso_U22,
           java.lang.Float aniso_U22_esd,
           java.lang.Float aniso_U23,
           java.lang.Float aniso_U23_esd,
           java.lang.Float aniso_U33,
           java.lang.Float aniso_U33_esd,
           java.lang.Float aniso_ratio,
           java.lang.Integer attached_hydrogens,
           java.lang.String auth_asym_id,
           java.lang.String auth_atom_id,
           java.lang.String auth_comp_id,
           java.lang.String auth_seq_id,
           java.lang.Float b_equiv_geom_mean,
           java.lang.Float b_equiv_geom_mean_esd,
           java.lang.Float b_iso_or_equiv,
           java.lang.Float b_iso_or_equiv_esd,
           java.lang.String calc_attached_atom,
           java.lang.String calc_flag,
           java.lang.Float cartn_x,
           java.lang.Float cartn_x_esd,
           java.lang.Float cartn_y,
           java.lang.Float cartn_y_esd,
           java.lang.Float cartn_z,
           java.lang.Float cartn_z_esd,
           java.lang.Integer chemical_conn_number,
           java.lang.String constraints,
           java.lang.String details,
           java.lang.String disorder_assembly,
           java.lang.String disorder_group,
           java.lang.String footnote_id,
           java.lang.Float fract_x,
           java.lang.Float fract_x_esd,
           java.lang.Float fract_y,
           java.lang.Float fract_y_esd,
           java.lang.Float fract_z,
           java.lang.Float fract_z_esd,
           java.lang.String group_PDB,
           java.lang.String id_,
           java.lang.String label_alt_id,
           java.lang.String label_asym_id,
           java.lang.String label_atom_id,
           java.lang.String label_comp_id,
           java.lang.String label_entity_id,
           java.lang.Integer label_seq_id,
           java.lang.Float occupancy,
           java.lang.Float occupancy_esd,
           java.lang.String pdbx_PDB_atom_name,
           java.lang.String pdbx_PDB_ins_code,
           java.lang.String pdbx_PDB_model_num,
           java.lang.String pdbx_PDB_residue_name,
           java.lang.String pdbx_PDB_residue_no,
           java.lang.String pdbx_PDB_strand_id,
           java.lang.String pdbx_auth_alt_id,
           java.lang.String pdbx_auth_atom_name,
           java.lang.String refinement_flags,
           java.lang.String refinement_flags_adp,
           java.lang.String refinement_flags_occupancy,
           java.lang.String refinement_flags_posn,
           java.lang.String restraints,
           java.lang.Integer symmetry_multiplicity,
           java.lang.String thermal_displace_type,
           java.lang.String type_symbol,
           java.lang.Float u_equiv_geom_mean,
           java.lang.Float u_equiv_geom_mean_esd,
           java.lang.Float u_iso_or_equiv,
           java.lang.Float u_iso_or_equiv_esd,
           java.lang.String wyckoff_symbol,
           java.lang.Long obj_id) {
        super(
            adp_type,
            aniso_B11,
            aniso_B11_esd,
            aniso_B12,
            aniso_B12_esd,
            aniso_B13,
            aniso_B13_esd,
            aniso_B22,
            aniso_B22_esd,
            aniso_B23,
            aniso_B23_esd,
            aniso_B33,
            aniso_B33_esd,
            aniso_U11,
            aniso_U11_esd,
            aniso_U12,
            aniso_U12_esd,
            aniso_U13,
            aniso_U13_esd,
            aniso_U22,
            aniso_U22_esd,
            aniso_U23,
            aniso_U23_esd,
            aniso_U33,
            aniso_U33_esd,
            aniso_ratio,
            attached_hydrogens,
            auth_asym_id,
            auth_atom_id,
            auth_comp_id,
            auth_seq_id,
            b_equiv_geom_mean,
            b_equiv_geom_mean_esd,
            b_iso_or_equiv,
            b_iso_or_equiv_esd,
            calc_attached_atom,
            calc_flag,
            cartn_x,
            cartn_x_esd,
            cartn_y,
            cartn_y_esd,
            cartn_z,
            cartn_z_esd,
            chemical_conn_number,
            constraints,
            details,
            disorder_assembly,
            disorder_group,
            footnote_id,
            fract_x,
            fract_x_esd,
            fract_y,
            fract_y_esd,
            fract_z,
            fract_z_esd,
            group_PDB,
            id_,
            label_alt_id,
            label_asym_id,
            label_atom_id,
            label_comp_id,
            label_entity_id,
            label_seq_id,
            occupancy,
            occupancy_esd,
            pdbx_PDB_atom_name,
            pdbx_PDB_ins_code,
            pdbx_PDB_model_num,
            pdbx_PDB_residue_name,
            pdbx_PDB_residue_no,
            pdbx_PDB_strand_id,
            pdbx_auth_alt_id,
            pdbx_auth_atom_name,
            refinement_flags,
            refinement_flags_adp,
            refinement_flags_occupancy,
            refinement_flags_posn,
            restraints,
            symmetry_multiplicity,
            thermal_displace_type,
            type_symbol,
            u_equiv_geom_mean,
            u_equiv_geom_mean_esd,
            u_iso_or_equiv,
            u_iso_or_equiv_esd,
            wyckoff_symbol,
            obj_id);
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Atom_siteWS)) return false;
        Atom_siteWS other = (Atom_siteWS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj);
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Atom_siteWS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("webservices.pdb.org", "Atom_siteWS"));
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
