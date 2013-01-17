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
 * AtomCoords.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.pdb.webservices;

public class AtomCoords  implements java.io.Serializable {
    private java.lang.String adp_type;

    private java.lang.Float aniso_B11;

    private java.lang.Float aniso_B11_esd;

    private java.lang.Float aniso_B12;

    private java.lang.Float aniso_B12_esd;

    private java.lang.Float aniso_B13;

    private java.lang.Float aniso_B13_esd;

    private java.lang.Float aniso_B22;

    private java.lang.Float aniso_B22_esd;

    private java.lang.Float aniso_B23;

    private java.lang.Float aniso_B23_esd;

    private java.lang.Float aniso_B33;

    private java.lang.Float aniso_B33_esd;

    private java.lang.Float aniso_U11;

    private java.lang.Float aniso_U11_esd;

    private java.lang.Float aniso_U12;

    private java.lang.Float aniso_U12_esd;

    private java.lang.Float aniso_U13;

    private java.lang.Float aniso_U13_esd;

    private java.lang.Float aniso_U22;

    private java.lang.Float aniso_U22_esd;

    private java.lang.Float aniso_U23;

    private java.lang.Float aniso_U23_esd;

    private java.lang.Float aniso_U33;

    private java.lang.Float aniso_U33_esd;

    private java.lang.Float aniso_ratio;

    private java.lang.Integer attached_hydrogens;

    private java.lang.String auth_asym_id;

    private java.lang.String auth_atom_id;

    private java.lang.String auth_comp_id;

    private java.lang.String auth_seq_id;

    private java.lang.Float b_equiv_geom_mean;

    private java.lang.Float b_equiv_geom_mean_esd;

    private java.lang.Float b_iso_or_equiv;

    private java.lang.Float b_iso_or_equiv_esd;

    private java.lang.String calc_attached_atom;

    private java.lang.String calc_flag;

    private java.lang.Float cartn_x;

    private java.lang.Float cartn_x_esd;

    private java.lang.Float cartn_y;

    private java.lang.Float cartn_y_esd;

    private java.lang.Float cartn_z;

    private java.lang.Float cartn_z_esd;

    private java.lang.Integer chemical_conn_number;

    private java.lang.String constraints;

    private java.lang.String details;

    private java.lang.String disorder_assembly;

    private java.lang.String disorder_group;

    private java.lang.String footnote_id;

    private java.lang.Float fract_x;

    private java.lang.Float fract_x_esd;

    private java.lang.Float fract_y;

    private java.lang.Float fract_y_esd;

    private java.lang.Float fract_z;

    private java.lang.Float fract_z_esd;

    private java.lang.String group_PDB;

    private java.lang.String id_;

    private java.lang.String label_alt_id;

    private java.lang.String label_asym_id;

    private java.lang.String label_atom_id;

    private java.lang.String label_comp_id;

    private java.lang.String label_entity_id;

    private java.lang.Integer label_seq_id;

    private java.lang.Float occupancy;

    private java.lang.Float occupancy_esd;

    private java.lang.String pdbx_PDB_atom_name;

    private java.lang.String pdbx_PDB_ins_code;

    private java.lang.String pdbx_PDB_model_num;

    private java.lang.String pdbx_PDB_residue_name;

    private java.lang.String pdbx_PDB_residue_no;

    private java.lang.String pdbx_PDB_strand_id;

    private java.lang.String pdbx_auth_alt_id;

    private java.lang.String pdbx_auth_atom_name;

    private java.lang.String refinement_flags;

    private java.lang.String refinement_flags_adp;

    private java.lang.String refinement_flags_occupancy;

    private java.lang.String refinement_flags_posn;

    private java.lang.String restraints;

    private java.lang.Integer symmetry_multiplicity;

    private java.lang.String thermal_displace_type;

    private java.lang.String type_symbol;

    private java.lang.Float u_equiv_geom_mean;

    private java.lang.Float u_equiv_geom_mean_esd;

    private java.lang.Float u_iso_or_equiv;

    private java.lang.Float u_iso_or_equiv_esd;

    private java.lang.String wyckoff_symbol;

    private java.lang.Long obj_id;

    public AtomCoords() {
    }

    public AtomCoords(
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
           this.adp_type = adp_type;
           this.aniso_B11 = aniso_B11;
           this.aniso_B11_esd = aniso_B11_esd;
           this.aniso_B12 = aniso_B12;
           this.aniso_B12_esd = aniso_B12_esd;
           this.aniso_B13 = aniso_B13;
           this.aniso_B13_esd = aniso_B13_esd;
           this.aniso_B22 = aniso_B22;
           this.aniso_B22_esd = aniso_B22_esd;
           this.aniso_B23 = aniso_B23;
           this.aniso_B23_esd = aniso_B23_esd;
           this.aniso_B33 = aniso_B33;
           this.aniso_B33_esd = aniso_B33_esd;
           this.aniso_U11 = aniso_U11;
           this.aniso_U11_esd = aniso_U11_esd;
           this.aniso_U12 = aniso_U12;
           this.aniso_U12_esd = aniso_U12_esd;
           this.aniso_U13 = aniso_U13;
           this.aniso_U13_esd = aniso_U13_esd;
           this.aniso_U22 = aniso_U22;
           this.aniso_U22_esd = aniso_U22_esd;
           this.aniso_U23 = aniso_U23;
           this.aniso_U23_esd = aniso_U23_esd;
           this.aniso_U33 = aniso_U33;
           this.aniso_U33_esd = aniso_U33_esd;
           this.aniso_ratio = aniso_ratio;
           this.attached_hydrogens = attached_hydrogens;
           this.auth_asym_id = auth_asym_id;
           this.auth_atom_id = auth_atom_id;
           this.auth_comp_id = auth_comp_id;
           this.auth_seq_id = auth_seq_id;
           this.b_equiv_geom_mean = b_equiv_geom_mean;
           this.b_equiv_geom_mean_esd = b_equiv_geom_mean_esd;
           this.b_iso_or_equiv = b_iso_or_equiv;
           this.b_iso_or_equiv_esd = b_iso_or_equiv_esd;
           this.calc_attached_atom = calc_attached_atom;
           this.calc_flag = calc_flag;
           this.cartn_x = cartn_x;
           this.cartn_x_esd = cartn_x_esd;
           this.cartn_y = cartn_y;
           this.cartn_y_esd = cartn_y_esd;
           this.cartn_z = cartn_z;
           this.cartn_z_esd = cartn_z_esd;
           this.chemical_conn_number = chemical_conn_number;
           this.constraints = constraints;
           this.details = details;
           this.disorder_assembly = disorder_assembly;
           this.disorder_group = disorder_group;
           this.footnote_id = footnote_id;
           this.fract_x = fract_x;
           this.fract_x_esd = fract_x_esd;
           this.fract_y = fract_y;
           this.fract_y_esd = fract_y_esd;
           this.fract_z = fract_z;
           this.fract_z_esd = fract_z_esd;
           this.group_PDB = group_PDB;
           this.id_ = id_;
           this.label_alt_id = label_alt_id;
           this.label_asym_id = label_asym_id;
           this.label_atom_id = label_atom_id;
           this.label_comp_id = label_comp_id;
           this.label_entity_id = label_entity_id;
           this.label_seq_id = label_seq_id;
           this.occupancy = occupancy;
           this.occupancy_esd = occupancy_esd;
           this.pdbx_PDB_atom_name = pdbx_PDB_atom_name;
           this.pdbx_PDB_ins_code = pdbx_PDB_ins_code;
           this.pdbx_PDB_model_num = pdbx_PDB_model_num;
           this.pdbx_PDB_residue_name = pdbx_PDB_residue_name;
           this.pdbx_PDB_residue_no = pdbx_PDB_residue_no;
           this.pdbx_PDB_strand_id = pdbx_PDB_strand_id;
           this.pdbx_auth_alt_id = pdbx_auth_alt_id;
           this.pdbx_auth_atom_name = pdbx_auth_atom_name;
           this.refinement_flags = refinement_flags;
           this.refinement_flags_adp = refinement_flags_adp;
           this.refinement_flags_occupancy = refinement_flags_occupancy;
           this.refinement_flags_posn = refinement_flags_posn;
           this.restraints = restraints;
           this.symmetry_multiplicity = symmetry_multiplicity;
           this.thermal_displace_type = thermal_displace_type;
           this.type_symbol = type_symbol;
           this.u_equiv_geom_mean = u_equiv_geom_mean;
           this.u_equiv_geom_mean_esd = u_equiv_geom_mean_esd;
           this.u_iso_or_equiv = u_iso_or_equiv;
           this.u_iso_or_equiv_esd = u_iso_or_equiv_esd;
           this.wyckoff_symbol = wyckoff_symbol;
           this.obj_id = obj_id;
    }


    /**
     * Gets the adp_type value for this AtomCoords.
     * 
     * @return adp_type
     */
    public java.lang.String getAdp_type() {
        return adp_type;
    }


    /**
     * Sets the adp_type value for this AtomCoords.
     * 
     * @param adp_type
     */
    public void setAdp_type(java.lang.String adp_type) {
        this.adp_type = adp_type;
    }


    /**
     * Gets the aniso_B11 value for this AtomCoords.
     * 
     * @return aniso_B11
     */
    public java.lang.Float getAniso_B11() {
        return aniso_B11;
    }


    /**
     * Sets the aniso_B11 value for this AtomCoords.
     * 
     * @param aniso_B11
     */
    public void setAniso_B11(java.lang.Float aniso_B11) {
        this.aniso_B11 = aniso_B11;
    }


    /**
     * Gets the aniso_B11_esd value for this AtomCoords.
     * 
     * @return aniso_B11_esd
     */
    public java.lang.Float getAniso_B11_esd() {
        return aniso_B11_esd;
    }


    /**
     * Sets the aniso_B11_esd value for this AtomCoords.
     * 
     * @param aniso_B11_esd
     */
    public void setAniso_B11_esd(java.lang.Float aniso_B11_esd) {
        this.aniso_B11_esd = aniso_B11_esd;
    }


    /**
     * Gets the aniso_B12 value for this AtomCoords.
     * 
     * @return aniso_B12
     */
    public java.lang.Float getAniso_B12() {
        return aniso_B12;
    }


    /**
     * Sets the aniso_B12 value for this AtomCoords.
     * 
     * @param aniso_B12
     */
    public void setAniso_B12(java.lang.Float aniso_B12) {
        this.aniso_B12 = aniso_B12;
    }


    /**
     * Gets the aniso_B12_esd value for this AtomCoords.
     * 
     * @return aniso_B12_esd
     */
    public java.lang.Float getAniso_B12_esd() {
        return aniso_B12_esd;
    }


    /**
     * Sets the aniso_B12_esd value for this AtomCoords.
     * 
     * @param aniso_B12_esd
     */
    public void setAniso_B12_esd(java.lang.Float aniso_B12_esd) {
        this.aniso_B12_esd = aniso_B12_esd;
    }


    /**
     * Gets the aniso_B13 value for this AtomCoords.
     * 
     * @return aniso_B13
     */
    public java.lang.Float getAniso_B13() {
        return aniso_B13;
    }


    /**
     * Sets the aniso_B13 value for this AtomCoords.
     * 
     * @param aniso_B13
     */
    public void setAniso_B13(java.lang.Float aniso_B13) {
        this.aniso_B13 = aniso_B13;
    }


    /**
     * Gets the aniso_B13_esd value for this AtomCoords.
     * 
     * @return aniso_B13_esd
     */
    public java.lang.Float getAniso_B13_esd() {
        return aniso_B13_esd;
    }


    /**
     * Sets the aniso_B13_esd value for this AtomCoords.
     * 
     * @param aniso_B13_esd
     */
    public void setAniso_B13_esd(java.lang.Float aniso_B13_esd) {
        this.aniso_B13_esd = aniso_B13_esd;
    }


    /**
     * Gets the aniso_B22 value for this AtomCoords.
     * 
     * @return aniso_B22
     */
    public java.lang.Float getAniso_B22() {
        return aniso_B22;
    }


    /**
     * Sets the aniso_B22 value for this AtomCoords.
     * 
     * @param aniso_B22
     */
    public void setAniso_B22(java.lang.Float aniso_B22) {
        this.aniso_B22 = aniso_B22;
    }


    /**
     * Gets the aniso_B22_esd value for this AtomCoords.
     * 
     * @return aniso_B22_esd
     */
    public java.lang.Float getAniso_B22_esd() {
        return aniso_B22_esd;
    }


    /**
     * Sets the aniso_B22_esd value for this AtomCoords.
     * 
     * @param aniso_B22_esd
     */
    public void setAniso_B22_esd(java.lang.Float aniso_B22_esd) {
        this.aniso_B22_esd = aniso_B22_esd;
    }


    /**
     * Gets the aniso_B23 value for this AtomCoords.
     * 
     * @return aniso_B23
     */
    public java.lang.Float getAniso_B23() {
        return aniso_B23;
    }


    /**
     * Sets the aniso_B23 value for this AtomCoords.
     * 
     * @param aniso_B23
     */
    public void setAniso_B23(java.lang.Float aniso_B23) {
        this.aniso_B23 = aniso_B23;
    }


    /**
     * Gets the aniso_B23_esd value for this AtomCoords.
     * 
     * @return aniso_B23_esd
     */
    public java.lang.Float getAniso_B23_esd() {
        return aniso_B23_esd;
    }


    /**
     * Sets the aniso_B23_esd value for this AtomCoords.
     * 
     * @param aniso_B23_esd
     */
    public void setAniso_B23_esd(java.lang.Float aniso_B23_esd) {
        this.aniso_B23_esd = aniso_B23_esd;
    }


    /**
     * Gets the aniso_B33 value for this AtomCoords.
     * 
     * @return aniso_B33
     */
    public java.lang.Float getAniso_B33() {
        return aniso_B33;
    }


    /**
     * Sets the aniso_B33 value for this AtomCoords.
     * 
     * @param aniso_B33
     */
    public void setAniso_B33(java.lang.Float aniso_B33) {
        this.aniso_B33 = aniso_B33;
    }


    /**
     * Gets the aniso_B33_esd value for this AtomCoords.
     * 
     * @return aniso_B33_esd
     */
    public java.lang.Float getAniso_B33_esd() {
        return aniso_B33_esd;
    }


    /**
     * Sets the aniso_B33_esd value for this AtomCoords.
     * 
     * @param aniso_B33_esd
     */
    public void setAniso_B33_esd(java.lang.Float aniso_B33_esd) {
        this.aniso_B33_esd = aniso_B33_esd;
    }


    /**
     * Gets the aniso_U11 value for this AtomCoords.
     * 
     * @return aniso_U11
     */
    public java.lang.Float getAniso_U11() {
        return aniso_U11;
    }


    /**
     * Sets the aniso_U11 value for this AtomCoords.
     * 
     * @param aniso_U11
     */
    public void setAniso_U11(java.lang.Float aniso_U11) {
        this.aniso_U11 = aniso_U11;
    }


    /**
     * Gets the aniso_U11_esd value for this AtomCoords.
     * 
     * @return aniso_U11_esd
     */
    public java.lang.Float getAniso_U11_esd() {
        return aniso_U11_esd;
    }


    /**
     * Sets the aniso_U11_esd value for this AtomCoords.
     * 
     * @param aniso_U11_esd
     */
    public void setAniso_U11_esd(java.lang.Float aniso_U11_esd) {
        this.aniso_U11_esd = aniso_U11_esd;
    }


    /**
     * Gets the aniso_U12 value for this AtomCoords.
     * 
     * @return aniso_U12
     */
    public java.lang.Float getAniso_U12() {
        return aniso_U12;
    }


    /**
     * Sets the aniso_U12 value for this AtomCoords.
     * 
     * @param aniso_U12
     */
    public void setAniso_U12(java.lang.Float aniso_U12) {
        this.aniso_U12 = aniso_U12;
    }


    /**
     * Gets the aniso_U12_esd value for this AtomCoords.
     * 
     * @return aniso_U12_esd
     */
    public java.lang.Float getAniso_U12_esd() {
        return aniso_U12_esd;
    }


    /**
     * Sets the aniso_U12_esd value for this AtomCoords.
     * 
     * @param aniso_U12_esd
     */
    public void setAniso_U12_esd(java.lang.Float aniso_U12_esd) {
        this.aniso_U12_esd = aniso_U12_esd;
    }


    /**
     * Gets the aniso_U13 value for this AtomCoords.
     * 
     * @return aniso_U13
     */
    public java.lang.Float getAniso_U13() {
        return aniso_U13;
    }


    /**
     * Sets the aniso_U13 value for this AtomCoords.
     * 
     * @param aniso_U13
     */
    public void setAniso_U13(java.lang.Float aniso_U13) {
        this.aniso_U13 = aniso_U13;
    }


    /**
     * Gets the aniso_U13_esd value for this AtomCoords.
     * 
     * @return aniso_U13_esd
     */
    public java.lang.Float getAniso_U13_esd() {
        return aniso_U13_esd;
    }


    /**
     * Sets the aniso_U13_esd value for this AtomCoords.
     * 
     * @param aniso_U13_esd
     */
    public void setAniso_U13_esd(java.lang.Float aniso_U13_esd) {
        this.aniso_U13_esd = aniso_U13_esd;
    }


    /**
     * Gets the aniso_U22 value for this AtomCoords.
     * 
     * @return aniso_U22
     */
    public java.lang.Float getAniso_U22() {
        return aniso_U22;
    }


    /**
     * Sets the aniso_U22 value for this AtomCoords.
     * 
     * @param aniso_U22
     */
    public void setAniso_U22(java.lang.Float aniso_U22) {
        this.aniso_U22 = aniso_U22;
    }


    /**
     * Gets the aniso_U22_esd value for this AtomCoords.
     * 
     * @return aniso_U22_esd
     */
    public java.lang.Float getAniso_U22_esd() {
        return aniso_U22_esd;
    }


    /**
     * Sets the aniso_U22_esd value for this AtomCoords.
     * 
     * @param aniso_U22_esd
     */
    public void setAniso_U22_esd(java.lang.Float aniso_U22_esd) {
        this.aniso_U22_esd = aniso_U22_esd;
    }


    /**
     * Gets the aniso_U23 value for this AtomCoords.
     * 
     * @return aniso_U23
     */
    public java.lang.Float getAniso_U23() {
        return aniso_U23;
    }


    /**
     * Sets the aniso_U23 value for this AtomCoords.
     * 
     * @param aniso_U23
     */
    public void setAniso_U23(java.lang.Float aniso_U23) {
        this.aniso_U23 = aniso_U23;
    }


    /**
     * Gets the aniso_U23_esd value for this AtomCoords.
     * 
     * @return aniso_U23_esd
     */
    public java.lang.Float getAniso_U23_esd() {
        return aniso_U23_esd;
    }


    /**
     * Sets the aniso_U23_esd value for this AtomCoords.
     * 
     * @param aniso_U23_esd
     */
    public void setAniso_U23_esd(java.lang.Float aniso_U23_esd) {
        this.aniso_U23_esd = aniso_U23_esd;
    }


    /**
     * Gets the aniso_U33 value for this AtomCoords.
     * 
     * @return aniso_U33
     */
    public java.lang.Float getAniso_U33() {
        return aniso_U33;
    }


    /**
     * Sets the aniso_U33 value for this AtomCoords.
     * 
     * @param aniso_U33
     */
    public void setAniso_U33(java.lang.Float aniso_U33) {
        this.aniso_U33 = aniso_U33;
    }


    /**
     * Gets the aniso_U33_esd value for this AtomCoords.
     * 
     * @return aniso_U33_esd
     */
    public java.lang.Float getAniso_U33_esd() {
        return aniso_U33_esd;
    }


    /**
     * Sets the aniso_U33_esd value for this AtomCoords.
     * 
     * @param aniso_U33_esd
     */
    public void setAniso_U33_esd(java.lang.Float aniso_U33_esd) {
        this.aniso_U33_esd = aniso_U33_esd;
    }


    /**
     * Gets the aniso_ratio value for this AtomCoords.
     * 
     * @return aniso_ratio
     */
    public java.lang.Float getAniso_ratio() {
        return aniso_ratio;
    }


    /**
     * Sets the aniso_ratio value for this AtomCoords.
     * 
     * @param aniso_ratio
     */
    public void setAniso_ratio(java.lang.Float aniso_ratio) {
        this.aniso_ratio = aniso_ratio;
    }


    /**
     * Gets the attached_hydrogens value for this AtomCoords.
     * 
     * @return attached_hydrogens
     */
    public java.lang.Integer getAttached_hydrogens() {
        return attached_hydrogens;
    }


    /**
     * Sets the attached_hydrogens value for this AtomCoords.
     * 
     * @param attached_hydrogens
     */
    public void setAttached_hydrogens(java.lang.Integer attached_hydrogens) {
        this.attached_hydrogens = attached_hydrogens;
    }


    /**
     * Gets the auth_asym_id value for this AtomCoords.
     * 
     * @return auth_asym_id
     */
    public java.lang.String getAuth_asym_id() {
        return auth_asym_id;
    }


    /**
     * Sets the auth_asym_id value for this AtomCoords.
     * 
     * @param auth_asym_id
     */
    public void setAuth_asym_id(java.lang.String auth_asym_id) {
        this.auth_asym_id = auth_asym_id;
    }


    /**
     * Gets the auth_atom_id value for this AtomCoords.
     * 
     * @return auth_atom_id
     */
    public java.lang.String getAuth_atom_id() {
        return auth_atom_id;
    }


    /**
     * Sets the auth_atom_id value for this AtomCoords.
     * 
     * @param auth_atom_id
     */
    public void setAuth_atom_id(java.lang.String auth_atom_id) {
        this.auth_atom_id = auth_atom_id;
    }


    /**
     * Gets the auth_comp_id value for this AtomCoords.
     * 
     * @return auth_comp_id
     */
    public java.lang.String getAuth_comp_id() {
        return auth_comp_id;
    }


    /**
     * Sets the auth_comp_id value for this AtomCoords.
     * 
     * @param auth_comp_id
     */
    public void setAuth_comp_id(java.lang.String auth_comp_id) {
        this.auth_comp_id = auth_comp_id;
    }


    /**
     * Gets the auth_seq_id value for this AtomCoords.
     * 
     * @return auth_seq_id
     */
    public java.lang.String getAuth_seq_id() {
        return auth_seq_id;
    }


    /**
     * Sets the auth_seq_id value for this AtomCoords.
     * 
     * @param auth_seq_id
     */
    public void setAuth_seq_id(java.lang.String auth_seq_id) {
        this.auth_seq_id = auth_seq_id;
    }


    /**
     * Gets the b_equiv_geom_mean value for this AtomCoords.
     * 
     * @return b_equiv_geom_mean
     */
    public java.lang.Float getB_equiv_geom_mean() {
        return b_equiv_geom_mean;
    }


    /**
     * Sets the b_equiv_geom_mean value for this AtomCoords.
     * 
     * @param b_equiv_geom_mean
     */
    public void setB_equiv_geom_mean(java.lang.Float b_equiv_geom_mean) {
        this.b_equiv_geom_mean = b_equiv_geom_mean;
    }


    /**
     * Gets the b_equiv_geom_mean_esd value for this AtomCoords.
     * 
     * @return b_equiv_geom_mean_esd
     */
    public java.lang.Float getB_equiv_geom_mean_esd() {
        return b_equiv_geom_mean_esd;
    }


    /**
     * Sets the b_equiv_geom_mean_esd value for this AtomCoords.
     * 
     * @param b_equiv_geom_mean_esd
     */
    public void setB_equiv_geom_mean_esd(java.lang.Float b_equiv_geom_mean_esd) {
        this.b_equiv_geom_mean_esd = b_equiv_geom_mean_esd;
    }


    /**
     * Gets the b_iso_or_equiv value for this AtomCoords.
     * 
     * @return b_iso_or_equiv
     */
    public java.lang.Float getB_iso_or_equiv() {
        return b_iso_or_equiv;
    }


    /**
     * Sets the b_iso_or_equiv value for this AtomCoords.
     * 
     * @param b_iso_or_equiv
     */
    public void setB_iso_or_equiv(java.lang.Float b_iso_or_equiv) {
        this.b_iso_or_equiv = b_iso_or_equiv;
    }


    /**
     * Gets the b_iso_or_equiv_esd value for this AtomCoords.
     * 
     * @return b_iso_or_equiv_esd
     */
    public java.lang.Float getB_iso_or_equiv_esd() {
        return b_iso_or_equiv_esd;
    }


    /**
     * Sets the b_iso_or_equiv_esd value for this AtomCoords.
     * 
     * @param b_iso_or_equiv_esd
     */
    public void setB_iso_or_equiv_esd(java.lang.Float b_iso_or_equiv_esd) {
        this.b_iso_or_equiv_esd = b_iso_or_equiv_esd;
    }


    /**
     * Gets the calc_attached_atom value for this AtomCoords.
     * 
     * @return calc_attached_atom
     */
    public java.lang.String getCalc_attached_atom() {
        return calc_attached_atom;
    }


    /**
     * Sets the calc_attached_atom value for this AtomCoords.
     * 
     * @param calc_attached_atom
     */
    public void setCalc_attached_atom(java.lang.String calc_attached_atom) {
        this.calc_attached_atom = calc_attached_atom;
    }


    /**
     * Gets the calc_flag value for this AtomCoords.
     * 
     * @return calc_flag
     */
    public java.lang.String getCalc_flag() {
        return calc_flag;
    }


    /**
     * Sets the calc_flag value for this AtomCoords.
     * 
     * @param calc_flag
     */
    public void setCalc_flag(java.lang.String calc_flag) {
        this.calc_flag = calc_flag;
    }


    /**
     * Gets the cartn_x value for this AtomCoords.
     * 
     * @return cartn_x
     */
    public java.lang.Float getCartn_x() {
        return cartn_x;
    }


    /**
     * Sets the cartn_x value for this AtomCoords.
     * 
     * @param cartn_x
     */
    public void setCartn_x(java.lang.Float cartn_x) {
        this.cartn_x = cartn_x;
    }


    /**
     * Gets the cartn_x_esd value for this AtomCoords.
     * 
     * @return cartn_x_esd
     */
    public java.lang.Float getCartn_x_esd() {
        return cartn_x_esd;
    }


    /**
     * Sets the cartn_x_esd value for this AtomCoords.
     * 
     * @param cartn_x_esd
     */
    public void setCartn_x_esd(java.lang.Float cartn_x_esd) {
        this.cartn_x_esd = cartn_x_esd;
    }


    /**
     * Gets the cartn_y value for this AtomCoords.
     * 
     * @return cartn_y
     */
    public java.lang.Float getCartn_y() {
        return cartn_y;
    }


    /**
     * Sets the cartn_y value for this AtomCoords.
     * 
     * @param cartn_y
     */
    public void setCartn_y(java.lang.Float cartn_y) {
        this.cartn_y = cartn_y;
    }


    /**
     * Gets the cartn_y_esd value for this AtomCoords.
     * 
     * @return cartn_y_esd
     */
    public java.lang.Float getCartn_y_esd() {
        return cartn_y_esd;
    }


    /**
     * Sets the cartn_y_esd value for this AtomCoords.
     * 
     * @param cartn_y_esd
     */
    public void setCartn_y_esd(java.lang.Float cartn_y_esd) {
        this.cartn_y_esd = cartn_y_esd;
    }


    /**
     * Gets the cartn_z value for this AtomCoords.
     * 
     * @return cartn_z
     */
    public java.lang.Float getCartn_z() {
        return cartn_z;
    }


    /**
     * Sets the cartn_z value for this AtomCoords.
     * 
     * @param cartn_z
     */
    public void setCartn_z(java.lang.Float cartn_z) {
        this.cartn_z = cartn_z;
    }


    /**
     * Gets the cartn_z_esd value for this AtomCoords.
     * 
     * @return cartn_z_esd
     */
    public java.lang.Float getCartn_z_esd() {
        return cartn_z_esd;
    }


    /**
     * Sets the cartn_z_esd value for this AtomCoords.
     * 
     * @param cartn_z_esd
     */
    public void setCartn_z_esd(java.lang.Float cartn_z_esd) {
        this.cartn_z_esd = cartn_z_esd;
    }


    /**
     * Gets the chemical_conn_number value for this AtomCoords.
     * 
     * @return chemical_conn_number
     */
    public java.lang.Integer getChemical_conn_number() {
        return chemical_conn_number;
    }


    /**
     * Sets the chemical_conn_number value for this AtomCoords.
     * 
     * @param chemical_conn_number
     */
    public void setChemical_conn_number(java.lang.Integer chemical_conn_number) {
        this.chemical_conn_number = chemical_conn_number;
    }


    /**
     * Gets the constraints value for this AtomCoords.
     * 
     * @return constraints
     */
    public java.lang.String getConstraints() {
        return constraints;
    }


    /**
     * Sets the constraints value for this AtomCoords.
     * 
     * @param constraints
     */
    public void setConstraints(java.lang.String constraints) {
        this.constraints = constraints;
    }


    /**
     * Gets the details value for this AtomCoords.
     * 
     * @return details
     */
    public java.lang.String getDetails() {
        return details;
    }


    /**
     * Sets the details value for this AtomCoords.
     * 
     * @param details
     */
    public void setDetails(java.lang.String details) {
        this.details = details;
    }


    /**
     * Gets the disorder_assembly value for this AtomCoords.
     * 
     * @return disorder_assembly
     */
    public java.lang.String getDisorder_assembly() {
        return disorder_assembly;
    }


    /**
     * Sets the disorder_assembly value for this AtomCoords.
     * 
     * @param disorder_assembly
     */
    public void setDisorder_assembly(java.lang.String disorder_assembly) {
        this.disorder_assembly = disorder_assembly;
    }


    /**
     * Gets the disorder_group value for this AtomCoords.
     * 
     * @return disorder_group
     */
    public java.lang.String getDisorder_group() {
        return disorder_group;
    }


    /**
     * Sets the disorder_group value for this AtomCoords.
     * 
     * @param disorder_group
     */
    public void setDisorder_group(java.lang.String disorder_group) {
        this.disorder_group = disorder_group;
    }


    /**
     * Gets the footnote_id value for this AtomCoords.
     * 
     * @return footnote_id
     */
    public java.lang.String getFootnote_id() {
        return footnote_id;
    }


    /**
     * Sets the footnote_id value for this AtomCoords.
     * 
     * @param footnote_id
     */
    public void setFootnote_id(java.lang.String footnote_id) {
        this.footnote_id = footnote_id;
    }


    /**
     * Gets the fract_x value for this AtomCoords.
     * 
     * @return fract_x
     */
    public java.lang.Float getFract_x() {
        return fract_x;
    }


    /**
     * Sets the fract_x value for this AtomCoords.
     * 
     * @param fract_x
     */
    public void setFract_x(java.lang.Float fract_x) {
        this.fract_x = fract_x;
    }


    /**
     * Gets the fract_x_esd value for this AtomCoords.
     * 
     * @return fract_x_esd
     */
    public java.lang.Float getFract_x_esd() {
        return fract_x_esd;
    }


    /**
     * Sets the fract_x_esd value for this AtomCoords.
     * 
     * @param fract_x_esd
     */
    public void setFract_x_esd(java.lang.Float fract_x_esd) {
        this.fract_x_esd = fract_x_esd;
    }


    /**
     * Gets the fract_y value for this AtomCoords.
     * 
     * @return fract_y
     */
    public java.lang.Float getFract_y() {
        return fract_y;
    }


    /**
     * Sets the fract_y value for this AtomCoords.
     * 
     * @param fract_y
     */
    public void setFract_y(java.lang.Float fract_y) {
        this.fract_y = fract_y;
    }


    /**
     * Gets the fract_y_esd value for this AtomCoords.
     * 
     * @return fract_y_esd
     */
    public java.lang.Float getFract_y_esd() {
        return fract_y_esd;
    }


    /**
     * Sets the fract_y_esd value for this AtomCoords.
     * 
     * @param fract_y_esd
     */
    public void setFract_y_esd(java.lang.Float fract_y_esd) {
        this.fract_y_esd = fract_y_esd;
    }


    /**
     * Gets the fract_z value for this AtomCoords.
     * 
     * @return fract_z
     */
    public java.lang.Float getFract_z() {
        return fract_z;
    }


    /**
     * Sets the fract_z value for this AtomCoords.
     * 
     * @param fract_z
     */
    public void setFract_z(java.lang.Float fract_z) {
        this.fract_z = fract_z;
    }


    /**
     * Gets the fract_z_esd value for this AtomCoords.
     * 
     * @return fract_z_esd
     */
    public java.lang.Float getFract_z_esd() {
        return fract_z_esd;
    }


    /**
     * Sets the fract_z_esd value for this AtomCoords.
     * 
     * @param fract_z_esd
     */
    public void setFract_z_esd(java.lang.Float fract_z_esd) {
        this.fract_z_esd = fract_z_esd;
    }


    /**
     * Gets the group_PDB value for this AtomCoords.
     * 
     * @return group_PDB
     */
    public java.lang.String getGroup_PDB() {
        return group_PDB;
    }


    /**
     * Sets the group_PDB value for this AtomCoords.
     * 
     * @param group_PDB
     */
    public void setGroup_PDB(java.lang.String group_PDB) {
        this.group_PDB = group_PDB;
    }


    /**
     * Gets the id_ value for this AtomCoords.
     * 
     * @return id_
     */
    public java.lang.String getId_() {
        return id_;
    }


    /**
     * Sets the id_ value for this AtomCoords.
     * 
     * @param id_
     */
    public void setId_(java.lang.String id_) {
        this.id_ = id_;
    }


    /**
     * Gets the label_alt_id value for this AtomCoords.
     * 
     * @return label_alt_id
     */
    public java.lang.String getLabel_alt_id() {
        return label_alt_id;
    }


    /**
     * Sets the label_alt_id value for this AtomCoords.
     * 
     * @param label_alt_id
     */
    public void setLabel_alt_id(java.lang.String label_alt_id) {
        this.label_alt_id = label_alt_id;
    }


    /**
     * Gets the label_asym_id value for this AtomCoords.
     * 
     * @return label_asym_id
     */
    public java.lang.String getLabel_asym_id() {
        return label_asym_id;
    }


    /**
     * Sets the label_asym_id value for this AtomCoords.
     * 
     * @param label_asym_id
     */
    public void setLabel_asym_id(java.lang.String label_asym_id) {
        this.label_asym_id = label_asym_id;
    }


    /**
     * Gets the label_atom_id value for this AtomCoords.
     * 
     * @return label_atom_id
     */
    public java.lang.String getLabel_atom_id() {
        return label_atom_id;
    }


    /**
     * Sets the label_atom_id value for this AtomCoords.
     * 
     * @param label_atom_id
     */
    public void setLabel_atom_id(java.lang.String label_atom_id) {
        this.label_atom_id = label_atom_id;
    }


    /**
     * Gets the label_comp_id value for this AtomCoords.
     * 
     * @return label_comp_id
     */
    public java.lang.String getLabel_comp_id() {
        return label_comp_id;
    }


    /**
     * Sets the label_comp_id value for this AtomCoords.
     * 
     * @param label_comp_id
     */
    public void setLabel_comp_id(java.lang.String label_comp_id) {
        this.label_comp_id = label_comp_id;
    }


    /**
     * Gets the label_entity_id value for this AtomCoords.
     * 
     * @return label_entity_id
     */
    public java.lang.String getLabel_entity_id() {
        return label_entity_id;
    }


    /**
     * Sets the label_entity_id value for this AtomCoords.
     * 
     * @param label_entity_id
     */
    public void setLabel_entity_id(java.lang.String label_entity_id) {
        this.label_entity_id = label_entity_id;
    }


    /**
     * Gets the label_seq_id value for this AtomCoords.
     * 
     * @return label_seq_id
     */
    public java.lang.Integer getLabel_seq_id() {
        return label_seq_id;
    }


    /**
     * Sets the label_seq_id value for this AtomCoords.
     * 
     * @param label_seq_id
     */
    public void setLabel_seq_id(java.lang.Integer label_seq_id) {
        this.label_seq_id = label_seq_id;
    }


    /**
     * Gets the occupancy value for this AtomCoords.
     * 
     * @return occupancy
     */
    public java.lang.Float getOccupancy() {
        return occupancy;
    }


    /**
     * Sets the occupancy value for this AtomCoords.
     * 
     * @param occupancy
     */
    public void setOccupancy(java.lang.Float occupancy) {
        this.occupancy = occupancy;
    }


    /**
     * Gets the occupancy_esd value for this AtomCoords.
     * 
     * @return occupancy_esd
     */
    public java.lang.Float getOccupancy_esd() {
        return occupancy_esd;
    }


    /**
     * Sets the occupancy_esd value for this AtomCoords.
     * 
     * @param occupancy_esd
     */
    public void setOccupancy_esd(java.lang.Float occupancy_esd) {
        this.occupancy_esd = occupancy_esd;
    }


    /**
     * Gets the pdbx_PDB_atom_name value for this AtomCoords.
     * 
     * @return pdbx_PDB_atom_name
     */
    public java.lang.String getPdbx_PDB_atom_name() {
        return pdbx_PDB_atom_name;
    }


    /**
     * Sets the pdbx_PDB_atom_name value for this AtomCoords.
     * 
     * @param pdbx_PDB_atom_name
     */
    public void setPdbx_PDB_atom_name(java.lang.String pdbx_PDB_atom_name) {
        this.pdbx_PDB_atom_name = pdbx_PDB_atom_name;
    }


    /**
     * Gets the pdbx_PDB_ins_code value for this AtomCoords.
     * 
     * @return pdbx_PDB_ins_code
     */
    public java.lang.String getPdbx_PDB_ins_code() {
        return pdbx_PDB_ins_code;
    }


    /**
     * Sets the pdbx_PDB_ins_code value for this AtomCoords.
     * 
     * @param pdbx_PDB_ins_code
     */
    public void setPdbx_PDB_ins_code(java.lang.String pdbx_PDB_ins_code) {
        this.pdbx_PDB_ins_code = pdbx_PDB_ins_code;
    }


    /**
     * Gets the pdbx_PDB_model_num value for this AtomCoords.
     * 
     * @return pdbx_PDB_model_num
     */
    public java.lang.String getPdbx_PDB_model_num() {
        return pdbx_PDB_model_num;
    }


    /**
     * Sets the pdbx_PDB_model_num value for this AtomCoords.
     * 
     * @param pdbx_PDB_model_num
     */
    public void setPdbx_PDB_model_num(java.lang.String pdbx_PDB_model_num) {
        this.pdbx_PDB_model_num = pdbx_PDB_model_num;
    }


    /**
     * Gets the pdbx_PDB_residue_name value for this AtomCoords.
     * 
     * @return pdbx_PDB_residue_name
     */
    public java.lang.String getPdbx_PDB_residue_name() {
        return pdbx_PDB_residue_name;
    }


    /**
     * Sets the pdbx_PDB_residue_name value for this AtomCoords.
     * 
     * @param pdbx_PDB_residue_name
     */
    public void setPdbx_PDB_residue_name(java.lang.String pdbx_PDB_residue_name) {
        this.pdbx_PDB_residue_name = pdbx_PDB_residue_name;
    }


    /**
     * Gets the pdbx_PDB_residue_no value for this AtomCoords.
     * 
     * @return pdbx_PDB_residue_no
     */
    public java.lang.String getPdbx_PDB_residue_no() {
        return pdbx_PDB_residue_no;
    }


    /**
     * Sets the pdbx_PDB_residue_no value for this AtomCoords.
     * 
     * @param pdbx_PDB_residue_no
     */
    public void setPdbx_PDB_residue_no(java.lang.String pdbx_PDB_residue_no) {
        this.pdbx_PDB_residue_no = pdbx_PDB_residue_no;
    }


    /**
     * Gets the pdbx_PDB_strand_id value for this AtomCoords.
     * 
     * @return pdbx_PDB_strand_id
     */
    public java.lang.String getPdbx_PDB_strand_id() {
        return pdbx_PDB_strand_id;
    }


    /**
     * Sets the pdbx_PDB_strand_id value for this AtomCoords.
     * 
     * @param pdbx_PDB_strand_id
     */
    public void setPdbx_PDB_strand_id(java.lang.String pdbx_PDB_strand_id) {
        this.pdbx_PDB_strand_id = pdbx_PDB_strand_id;
    }


    /**
     * Gets the pdbx_auth_alt_id value for this AtomCoords.
     * 
     * @return pdbx_auth_alt_id
     */
    public java.lang.String getPdbx_auth_alt_id() {
        return pdbx_auth_alt_id;
    }


    /**
     * Sets the pdbx_auth_alt_id value for this AtomCoords.
     * 
     * @param pdbx_auth_alt_id
     */
    public void setPdbx_auth_alt_id(java.lang.String pdbx_auth_alt_id) {
        this.pdbx_auth_alt_id = pdbx_auth_alt_id;
    }


    /**
     * Gets the pdbx_auth_atom_name value for this AtomCoords.
     * 
     * @return pdbx_auth_atom_name
     */
    public java.lang.String getPdbx_auth_atom_name() {
        return pdbx_auth_atom_name;
    }


    /**
     * Sets the pdbx_auth_atom_name value for this AtomCoords.
     * 
     * @param pdbx_auth_atom_name
     */
    public void setPdbx_auth_atom_name(java.lang.String pdbx_auth_atom_name) {
        this.pdbx_auth_atom_name = pdbx_auth_atom_name;
    }


    /**
     * Gets the refinement_flags value for this AtomCoords.
     * 
     * @return refinement_flags
     */
    public java.lang.String getRefinement_flags() {
        return refinement_flags;
    }


    /**
     * Sets the refinement_flags value for this AtomCoords.
     * 
     * @param refinement_flags
     */
    public void setRefinement_flags(java.lang.String refinement_flags) {
        this.refinement_flags = refinement_flags;
    }


    /**
     * Gets the refinement_flags_adp value for this AtomCoords.
     * 
     * @return refinement_flags_adp
     */
    public java.lang.String getRefinement_flags_adp() {
        return refinement_flags_adp;
    }


    /**
     * Sets the refinement_flags_adp value for this AtomCoords.
     * 
     * @param refinement_flags_adp
     */
    public void setRefinement_flags_adp(java.lang.String refinement_flags_adp) {
        this.refinement_flags_adp = refinement_flags_adp;
    }


    /**
     * Gets the refinement_flags_occupancy value for this AtomCoords.
     * 
     * @return refinement_flags_occupancy
     */
    public java.lang.String getRefinement_flags_occupancy() {
        return refinement_flags_occupancy;
    }


    /**
     * Sets the refinement_flags_occupancy value for this AtomCoords.
     * 
     * @param refinement_flags_occupancy
     */
    public void setRefinement_flags_occupancy(java.lang.String refinement_flags_occupancy) {
        this.refinement_flags_occupancy = refinement_flags_occupancy;
    }


    /**
     * Gets the refinement_flags_posn value for this AtomCoords.
     * 
     * @return refinement_flags_posn
     */
    public java.lang.String getRefinement_flags_posn() {
        return refinement_flags_posn;
    }


    /**
     * Sets the refinement_flags_posn value for this AtomCoords.
     * 
     * @param refinement_flags_posn
     */
    public void setRefinement_flags_posn(java.lang.String refinement_flags_posn) {
        this.refinement_flags_posn = refinement_flags_posn;
    }


    /**
     * Gets the restraints value for this AtomCoords.
     * 
     * @return restraints
     */
    public java.lang.String getRestraints() {
        return restraints;
    }


    /**
     * Sets the restraints value for this AtomCoords.
     * 
     * @param restraints
     */
    public void setRestraints(java.lang.String restraints) {
        this.restraints = restraints;
    }


    /**
     * Gets the symmetry_multiplicity value for this AtomCoords.
     * 
     * @return symmetry_multiplicity
     */
    public java.lang.Integer getSymmetry_multiplicity() {
        return symmetry_multiplicity;
    }


    /**
     * Sets the symmetry_multiplicity value for this AtomCoords.
     * 
     * @param symmetry_multiplicity
     */
    public void setSymmetry_multiplicity(java.lang.Integer symmetry_multiplicity) {
        this.symmetry_multiplicity = symmetry_multiplicity;
    }


    /**
     * Gets the thermal_displace_type value for this AtomCoords.
     * 
     * @return thermal_displace_type
     */
    public java.lang.String getThermal_displace_type() {
        return thermal_displace_type;
    }


    /**
     * Sets the thermal_displace_type value for this AtomCoords.
     * 
     * @param thermal_displace_type
     */
    public void setThermal_displace_type(java.lang.String thermal_displace_type) {
        this.thermal_displace_type = thermal_displace_type;
    }


    /**
     * Gets the type_symbol value for this AtomCoords.
     * 
     * @return type_symbol
     */
    public java.lang.String getType_symbol() {
        return type_symbol;
    }


    /**
     * Sets the type_symbol value for this AtomCoords.
     * 
     * @param type_symbol
     */
    public void setType_symbol(java.lang.String type_symbol) {
        this.type_symbol = type_symbol;
    }


    /**
     * Gets the u_equiv_geom_mean value for this AtomCoords.
     * 
     * @return u_equiv_geom_mean
     */
    public java.lang.Float getU_equiv_geom_mean() {
        return u_equiv_geom_mean;
    }


    /**
     * Sets the u_equiv_geom_mean value for this AtomCoords.
     * 
     * @param u_equiv_geom_mean
     */
    public void setU_equiv_geom_mean(java.lang.Float u_equiv_geom_mean) {
        this.u_equiv_geom_mean = u_equiv_geom_mean;
    }


    /**
     * Gets the u_equiv_geom_mean_esd value for this AtomCoords.
     * 
     * @return u_equiv_geom_mean_esd
     */
    public java.lang.Float getU_equiv_geom_mean_esd() {
        return u_equiv_geom_mean_esd;
    }


    /**
     * Sets the u_equiv_geom_mean_esd value for this AtomCoords.
     * 
     * @param u_equiv_geom_mean_esd
     */
    public void setU_equiv_geom_mean_esd(java.lang.Float u_equiv_geom_mean_esd) {
        this.u_equiv_geom_mean_esd = u_equiv_geom_mean_esd;
    }


    /**
     * Gets the u_iso_or_equiv value for this AtomCoords.
     * 
     * @return u_iso_or_equiv
     */
    public java.lang.Float getU_iso_or_equiv() {
        return u_iso_or_equiv;
    }


    /**
     * Sets the u_iso_or_equiv value for this AtomCoords.
     * 
     * @param u_iso_or_equiv
     */
    public void setU_iso_or_equiv(java.lang.Float u_iso_or_equiv) {
        this.u_iso_or_equiv = u_iso_or_equiv;
    }


    /**
     * Gets the u_iso_or_equiv_esd value for this AtomCoords.
     * 
     * @return u_iso_or_equiv_esd
     */
    public java.lang.Float getU_iso_or_equiv_esd() {
        return u_iso_or_equiv_esd;
    }


    /**
     * Sets the u_iso_or_equiv_esd value for this AtomCoords.
     * 
     * @param u_iso_or_equiv_esd
     */
    public void setU_iso_or_equiv_esd(java.lang.Float u_iso_or_equiv_esd) {
        this.u_iso_or_equiv_esd = u_iso_or_equiv_esd;
    }


    /**
     * Gets the wyckoff_symbol value for this AtomCoords.
     * 
     * @return wyckoff_symbol
     */
    public java.lang.String getWyckoff_symbol() {
        return wyckoff_symbol;
    }


    /**
     * Sets the wyckoff_symbol value for this AtomCoords.
     * 
     * @param wyckoff_symbol
     */
    public void setWyckoff_symbol(java.lang.String wyckoff_symbol) {
        this.wyckoff_symbol = wyckoff_symbol;
    }


    /**
     * Gets the obj_id value for this AtomCoords.
     * 
     * @return obj_id
     */
    public java.lang.Long getObj_id() {
        return obj_id;
    }


    /**
     * Sets the obj_id value for this AtomCoords.
     * 
     * @param obj_id
     */
    public void setObj_id(java.lang.Long obj_id) {
        this.obj_id = obj_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AtomCoords)) return false;
        AtomCoords other = (AtomCoords) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.adp_type==null && other.getAdp_type()==null) || 
             (this.adp_type!=null &&
              this.adp_type.equals(other.getAdp_type()))) &&
            ((this.aniso_B11==null && other.getAniso_B11()==null) || 
             (this.aniso_B11!=null &&
              this.aniso_B11.equals(other.getAniso_B11()))) &&
            ((this.aniso_B11_esd==null && other.getAniso_B11_esd()==null) || 
             (this.aniso_B11_esd!=null &&
              this.aniso_B11_esd.equals(other.getAniso_B11_esd()))) &&
            ((this.aniso_B12==null && other.getAniso_B12()==null) || 
             (this.aniso_B12!=null &&
              this.aniso_B12.equals(other.getAniso_B12()))) &&
            ((this.aniso_B12_esd==null && other.getAniso_B12_esd()==null) || 
             (this.aniso_B12_esd!=null &&
              this.aniso_B12_esd.equals(other.getAniso_B12_esd()))) &&
            ((this.aniso_B13==null && other.getAniso_B13()==null) || 
             (this.aniso_B13!=null &&
              this.aniso_B13.equals(other.getAniso_B13()))) &&
            ((this.aniso_B13_esd==null && other.getAniso_B13_esd()==null) || 
             (this.aniso_B13_esd!=null &&
              this.aniso_B13_esd.equals(other.getAniso_B13_esd()))) &&
            ((this.aniso_B22==null && other.getAniso_B22()==null) || 
             (this.aniso_B22!=null &&
              this.aniso_B22.equals(other.getAniso_B22()))) &&
            ((this.aniso_B22_esd==null && other.getAniso_B22_esd()==null) || 
             (this.aniso_B22_esd!=null &&
              this.aniso_B22_esd.equals(other.getAniso_B22_esd()))) &&
            ((this.aniso_B23==null && other.getAniso_B23()==null) || 
             (this.aniso_B23!=null &&
              this.aniso_B23.equals(other.getAniso_B23()))) &&
            ((this.aniso_B23_esd==null && other.getAniso_B23_esd()==null) || 
             (this.aniso_B23_esd!=null &&
              this.aniso_B23_esd.equals(other.getAniso_B23_esd()))) &&
            ((this.aniso_B33==null && other.getAniso_B33()==null) || 
             (this.aniso_B33!=null &&
              this.aniso_B33.equals(other.getAniso_B33()))) &&
            ((this.aniso_B33_esd==null && other.getAniso_B33_esd()==null) || 
             (this.aniso_B33_esd!=null &&
              this.aniso_B33_esd.equals(other.getAniso_B33_esd()))) &&
            ((this.aniso_U11==null && other.getAniso_U11()==null) || 
             (this.aniso_U11!=null &&
              this.aniso_U11.equals(other.getAniso_U11()))) &&
            ((this.aniso_U11_esd==null && other.getAniso_U11_esd()==null) || 
             (this.aniso_U11_esd!=null &&
              this.aniso_U11_esd.equals(other.getAniso_U11_esd()))) &&
            ((this.aniso_U12==null && other.getAniso_U12()==null) || 
             (this.aniso_U12!=null &&
              this.aniso_U12.equals(other.getAniso_U12()))) &&
            ((this.aniso_U12_esd==null && other.getAniso_U12_esd()==null) || 
             (this.aniso_U12_esd!=null &&
              this.aniso_U12_esd.equals(other.getAniso_U12_esd()))) &&
            ((this.aniso_U13==null && other.getAniso_U13()==null) || 
             (this.aniso_U13!=null &&
              this.aniso_U13.equals(other.getAniso_U13()))) &&
            ((this.aniso_U13_esd==null && other.getAniso_U13_esd()==null) || 
             (this.aniso_U13_esd!=null &&
              this.aniso_U13_esd.equals(other.getAniso_U13_esd()))) &&
            ((this.aniso_U22==null && other.getAniso_U22()==null) || 
             (this.aniso_U22!=null &&
              this.aniso_U22.equals(other.getAniso_U22()))) &&
            ((this.aniso_U22_esd==null && other.getAniso_U22_esd()==null) || 
             (this.aniso_U22_esd!=null &&
              this.aniso_U22_esd.equals(other.getAniso_U22_esd()))) &&
            ((this.aniso_U23==null && other.getAniso_U23()==null) || 
             (this.aniso_U23!=null &&
              this.aniso_U23.equals(other.getAniso_U23()))) &&
            ((this.aniso_U23_esd==null && other.getAniso_U23_esd()==null) || 
             (this.aniso_U23_esd!=null &&
              this.aniso_U23_esd.equals(other.getAniso_U23_esd()))) &&
            ((this.aniso_U33==null && other.getAniso_U33()==null) || 
             (this.aniso_U33!=null &&
              this.aniso_U33.equals(other.getAniso_U33()))) &&
            ((this.aniso_U33_esd==null && other.getAniso_U33_esd()==null) || 
             (this.aniso_U33_esd!=null &&
              this.aniso_U33_esd.equals(other.getAniso_U33_esd()))) &&
            ((this.aniso_ratio==null && other.getAniso_ratio()==null) || 
             (this.aniso_ratio!=null &&
              this.aniso_ratio.equals(other.getAniso_ratio()))) &&
            ((this.attached_hydrogens==null && other.getAttached_hydrogens()==null) || 
             (this.attached_hydrogens!=null &&
              this.attached_hydrogens.equals(other.getAttached_hydrogens()))) &&
            ((this.auth_asym_id==null && other.getAuth_asym_id()==null) || 
             (this.auth_asym_id!=null &&
              this.auth_asym_id.equals(other.getAuth_asym_id()))) &&
            ((this.auth_atom_id==null && other.getAuth_atom_id()==null) || 
             (this.auth_atom_id!=null &&
              this.auth_atom_id.equals(other.getAuth_atom_id()))) &&
            ((this.auth_comp_id==null && other.getAuth_comp_id()==null) || 
             (this.auth_comp_id!=null &&
              this.auth_comp_id.equals(other.getAuth_comp_id()))) &&
            ((this.auth_seq_id==null && other.getAuth_seq_id()==null) || 
             (this.auth_seq_id!=null &&
              this.auth_seq_id.equals(other.getAuth_seq_id()))) &&
            ((this.b_equiv_geom_mean==null && other.getB_equiv_geom_mean()==null) || 
             (this.b_equiv_geom_mean!=null &&
              this.b_equiv_geom_mean.equals(other.getB_equiv_geom_mean()))) &&
            ((this.b_equiv_geom_mean_esd==null && other.getB_equiv_geom_mean_esd()==null) || 
             (this.b_equiv_geom_mean_esd!=null &&
              this.b_equiv_geom_mean_esd.equals(other.getB_equiv_geom_mean_esd()))) &&
            ((this.b_iso_or_equiv==null && other.getB_iso_or_equiv()==null) || 
             (this.b_iso_or_equiv!=null &&
              this.b_iso_or_equiv.equals(other.getB_iso_or_equiv()))) &&
            ((this.b_iso_or_equiv_esd==null && other.getB_iso_or_equiv_esd()==null) || 
             (this.b_iso_or_equiv_esd!=null &&
              this.b_iso_or_equiv_esd.equals(other.getB_iso_or_equiv_esd()))) &&
            ((this.calc_attached_atom==null && other.getCalc_attached_atom()==null) || 
             (this.calc_attached_atom!=null &&
              this.calc_attached_atom.equals(other.getCalc_attached_atom()))) &&
            ((this.calc_flag==null && other.getCalc_flag()==null) || 
             (this.calc_flag!=null &&
              this.calc_flag.equals(other.getCalc_flag()))) &&
            ((this.cartn_x==null && other.getCartn_x()==null) || 
             (this.cartn_x!=null &&
              this.cartn_x.equals(other.getCartn_x()))) &&
            ((this.cartn_x_esd==null && other.getCartn_x_esd()==null) || 
             (this.cartn_x_esd!=null &&
              this.cartn_x_esd.equals(other.getCartn_x_esd()))) &&
            ((this.cartn_y==null && other.getCartn_y()==null) || 
             (this.cartn_y!=null &&
              this.cartn_y.equals(other.getCartn_y()))) &&
            ((this.cartn_y_esd==null && other.getCartn_y_esd()==null) || 
             (this.cartn_y_esd!=null &&
              this.cartn_y_esd.equals(other.getCartn_y_esd()))) &&
            ((this.cartn_z==null && other.getCartn_z()==null) || 
             (this.cartn_z!=null &&
              this.cartn_z.equals(other.getCartn_z()))) &&
            ((this.cartn_z_esd==null && other.getCartn_z_esd()==null) || 
             (this.cartn_z_esd!=null &&
              this.cartn_z_esd.equals(other.getCartn_z_esd()))) &&
            ((this.chemical_conn_number==null && other.getChemical_conn_number()==null) || 
             (this.chemical_conn_number!=null &&
              this.chemical_conn_number.equals(other.getChemical_conn_number()))) &&
            ((this.constraints==null && other.getConstraints()==null) || 
             (this.constraints!=null &&
              this.constraints.equals(other.getConstraints()))) &&
            ((this.details==null && other.getDetails()==null) || 
             (this.details!=null &&
              this.details.equals(other.getDetails()))) &&
            ((this.disorder_assembly==null && other.getDisorder_assembly()==null) || 
             (this.disorder_assembly!=null &&
              this.disorder_assembly.equals(other.getDisorder_assembly()))) &&
            ((this.disorder_group==null && other.getDisorder_group()==null) || 
             (this.disorder_group!=null &&
              this.disorder_group.equals(other.getDisorder_group()))) &&
            ((this.footnote_id==null && other.getFootnote_id()==null) || 
             (this.footnote_id!=null &&
              this.footnote_id.equals(other.getFootnote_id()))) &&
            ((this.fract_x==null && other.getFract_x()==null) || 
             (this.fract_x!=null &&
              this.fract_x.equals(other.getFract_x()))) &&
            ((this.fract_x_esd==null && other.getFract_x_esd()==null) || 
             (this.fract_x_esd!=null &&
              this.fract_x_esd.equals(other.getFract_x_esd()))) &&
            ((this.fract_y==null && other.getFract_y()==null) || 
             (this.fract_y!=null &&
              this.fract_y.equals(other.getFract_y()))) &&
            ((this.fract_y_esd==null && other.getFract_y_esd()==null) || 
             (this.fract_y_esd!=null &&
              this.fract_y_esd.equals(other.getFract_y_esd()))) &&
            ((this.fract_z==null && other.getFract_z()==null) || 
             (this.fract_z!=null &&
              this.fract_z.equals(other.getFract_z()))) &&
            ((this.fract_z_esd==null && other.getFract_z_esd()==null) || 
             (this.fract_z_esd!=null &&
              this.fract_z_esd.equals(other.getFract_z_esd()))) &&
            ((this.group_PDB==null && other.getGroup_PDB()==null) || 
             (this.group_PDB!=null &&
              this.group_PDB.equals(other.getGroup_PDB()))) &&
            ((this.id_==null && other.getId_()==null) || 
             (this.id_!=null &&
              this.id_.equals(other.getId_()))) &&
            ((this.label_alt_id==null && other.getLabel_alt_id()==null) || 
             (this.label_alt_id!=null &&
              this.label_alt_id.equals(other.getLabel_alt_id()))) &&
            ((this.label_asym_id==null && other.getLabel_asym_id()==null) || 
             (this.label_asym_id!=null &&
              this.label_asym_id.equals(other.getLabel_asym_id()))) &&
            ((this.label_atom_id==null && other.getLabel_atom_id()==null) || 
             (this.label_atom_id!=null &&
              this.label_atom_id.equals(other.getLabel_atom_id()))) &&
            ((this.label_comp_id==null && other.getLabel_comp_id()==null) || 
             (this.label_comp_id!=null &&
              this.label_comp_id.equals(other.getLabel_comp_id()))) &&
            ((this.label_entity_id==null && other.getLabel_entity_id()==null) || 
             (this.label_entity_id!=null &&
              this.label_entity_id.equals(other.getLabel_entity_id()))) &&
            ((this.label_seq_id==null && other.getLabel_seq_id()==null) || 
             (this.label_seq_id!=null &&
              this.label_seq_id.equals(other.getLabel_seq_id()))) &&
            ((this.occupancy==null && other.getOccupancy()==null) || 
             (this.occupancy!=null &&
              this.occupancy.equals(other.getOccupancy()))) &&
            ((this.occupancy_esd==null && other.getOccupancy_esd()==null) || 
             (this.occupancy_esd!=null &&
              this.occupancy_esd.equals(other.getOccupancy_esd()))) &&
            ((this.pdbx_PDB_atom_name==null && other.getPdbx_PDB_atom_name()==null) || 
             (this.pdbx_PDB_atom_name!=null &&
              this.pdbx_PDB_atom_name.equals(other.getPdbx_PDB_atom_name()))) &&
            ((this.pdbx_PDB_ins_code==null && other.getPdbx_PDB_ins_code()==null) || 
             (this.pdbx_PDB_ins_code!=null &&
              this.pdbx_PDB_ins_code.equals(other.getPdbx_PDB_ins_code()))) &&
            ((this.pdbx_PDB_model_num==null && other.getPdbx_PDB_model_num()==null) || 
             (this.pdbx_PDB_model_num!=null &&
              this.pdbx_PDB_model_num.equals(other.getPdbx_PDB_model_num()))) &&
            ((this.pdbx_PDB_residue_name==null && other.getPdbx_PDB_residue_name()==null) || 
             (this.pdbx_PDB_residue_name!=null &&
              this.pdbx_PDB_residue_name.equals(other.getPdbx_PDB_residue_name()))) &&
            ((this.pdbx_PDB_residue_no==null && other.getPdbx_PDB_residue_no()==null) || 
             (this.pdbx_PDB_residue_no!=null &&
              this.pdbx_PDB_residue_no.equals(other.getPdbx_PDB_residue_no()))) &&
            ((this.pdbx_PDB_strand_id==null && other.getPdbx_PDB_strand_id()==null) || 
             (this.pdbx_PDB_strand_id!=null &&
              this.pdbx_PDB_strand_id.equals(other.getPdbx_PDB_strand_id()))) &&
            ((this.pdbx_auth_alt_id==null && other.getPdbx_auth_alt_id()==null) || 
             (this.pdbx_auth_alt_id!=null &&
              this.pdbx_auth_alt_id.equals(other.getPdbx_auth_alt_id()))) &&
            ((this.pdbx_auth_atom_name==null && other.getPdbx_auth_atom_name()==null) || 
             (this.pdbx_auth_atom_name!=null &&
              this.pdbx_auth_atom_name.equals(other.getPdbx_auth_atom_name()))) &&
            ((this.refinement_flags==null && other.getRefinement_flags()==null) || 
             (this.refinement_flags!=null &&
              this.refinement_flags.equals(other.getRefinement_flags()))) &&
            ((this.refinement_flags_adp==null && other.getRefinement_flags_adp()==null) || 
             (this.refinement_flags_adp!=null &&
              this.refinement_flags_adp.equals(other.getRefinement_flags_adp()))) &&
            ((this.refinement_flags_occupancy==null && other.getRefinement_flags_occupancy()==null) || 
             (this.refinement_flags_occupancy!=null &&
              this.refinement_flags_occupancy.equals(other.getRefinement_flags_occupancy()))) &&
            ((this.refinement_flags_posn==null && other.getRefinement_flags_posn()==null) || 
             (this.refinement_flags_posn!=null &&
              this.refinement_flags_posn.equals(other.getRefinement_flags_posn()))) &&
            ((this.restraints==null && other.getRestraints()==null) || 
             (this.restraints!=null &&
              this.restraints.equals(other.getRestraints()))) &&
            ((this.symmetry_multiplicity==null && other.getSymmetry_multiplicity()==null) || 
             (this.symmetry_multiplicity!=null &&
              this.symmetry_multiplicity.equals(other.getSymmetry_multiplicity()))) &&
            ((this.thermal_displace_type==null && other.getThermal_displace_type()==null) || 
             (this.thermal_displace_type!=null &&
              this.thermal_displace_type.equals(other.getThermal_displace_type()))) &&
            ((this.type_symbol==null && other.getType_symbol()==null) || 
             (this.type_symbol!=null &&
              this.type_symbol.equals(other.getType_symbol()))) &&
            ((this.u_equiv_geom_mean==null && other.getU_equiv_geom_mean()==null) || 
             (this.u_equiv_geom_mean!=null &&
              this.u_equiv_geom_mean.equals(other.getU_equiv_geom_mean()))) &&
            ((this.u_equiv_geom_mean_esd==null && other.getU_equiv_geom_mean_esd()==null) || 
             (this.u_equiv_geom_mean_esd!=null &&
              this.u_equiv_geom_mean_esd.equals(other.getU_equiv_geom_mean_esd()))) &&
            ((this.u_iso_or_equiv==null && other.getU_iso_or_equiv()==null) || 
             (this.u_iso_or_equiv!=null &&
              this.u_iso_or_equiv.equals(other.getU_iso_or_equiv()))) &&
            ((this.u_iso_or_equiv_esd==null && other.getU_iso_or_equiv_esd()==null) || 
             (this.u_iso_or_equiv_esd!=null &&
              this.u_iso_or_equiv_esd.equals(other.getU_iso_or_equiv_esd()))) &&
            ((this.wyckoff_symbol==null && other.getWyckoff_symbol()==null) || 
             (this.wyckoff_symbol!=null &&
              this.wyckoff_symbol.equals(other.getWyckoff_symbol()))) &&
            ((this.obj_id==null && other.getObj_id()==null) || 
             (this.obj_id!=null &&
              this.obj_id.equals(other.getObj_id())));
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
        if (getAdp_type() != null) {
            _hashCode += getAdp_type().hashCode();
        }
        if (getAniso_B11() != null) {
            _hashCode += getAniso_B11().hashCode();
        }
        if (getAniso_B11_esd() != null) {
            _hashCode += getAniso_B11_esd().hashCode();
        }
        if (getAniso_B12() != null) {
            _hashCode += getAniso_B12().hashCode();
        }
        if (getAniso_B12_esd() != null) {
            _hashCode += getAniso_B12_esd().hashCode();
        }
        if (getAniso_B13() != null) {
            _hashCode += getAniso_B13().hashCode();
        }
        if (getAniso_B13_esd() != null) {
            _hashCode += getAniso_B13_esd().hashCode();
        }
        if (getAniso_B22() != null) {
            _hashCode += getAniso_B22().hashCode();
        }
        if (getAniso_B22_esd() != null) {
            _hashCode += getAniso_B22_esd().hashCode();
        }
        if (getAniso_B23() != null) {
            _hashCode += getAniso_B23().hashCode();
        }
        if (getAniso_B23_esd() != null) {
            _hashCode += getAniso_B23_esd().hashCode();
        }
        if (getAniso_B33() != null) {
            _hashCode += getAniso_B33().hashCode();
        }
        if (getAniso_B33_esd() != null) {
            _hashCode += getAniso_B33_esd().hashCode();
        }
        if (getAniso_U11() != null) {
            _hashCode += getAniso_U11().hashCode();
        }
        if (getAniso_U11_esd() != null) {
            _hashCode += getAniso_U11_esd().hashCode();
        }
        if (getAniso_U12() != null) {
            _hashCode += getAniso_U12().hashCode();
        }
        if (getAniso_U12_esd() != null) {
            _hashCode += getAniso_U12_esd().hashCode();
        }
        if (getAniso_U13() != null) {
            _hashCode += getAniso_U13().hashCode();
        }
        if (getAniso_U13_esd() != null) {
            _hashCode += getAniso_U13_esd().hashCode();
        }
        if (getAniso_U22() != null) {
            _hashCode += getAniso_U22().hashCode();
        }
        if (getAniso_U22_esd() != null) {
            _hashCode += getAniso_U22_esd().hashCode();
        }
        if (getAniso_U23() != null) {
            _hashCode += getAniso_U23().hashCode();
        }
        if (getAniso_U23_esd() != null) {
            _hashCode += getAniso_U23_esd().hashCode();
        }
        if (getAniso_U33() != null) {
            _hashCode += getAniso_U33().hashCode();
        }
        if (getAniso_U33_esd() != null) {
            _hashCode += getAniso_U33_esd().hashCode();
        }
        if (getAniso_ratio() != null) {
            _hashCode += getAniso_ratio().hashCode();
        }
        if (getAttached_hydrogens() != null) {
            _hashCode += getAttached_hydrogens().hashCode();
        }
        if (getAuth_asym_id() != null) {
            _hashCode += getAuth_asym_id().hashCode();
        }
        if (getAuth_atom_id() != null) {
            _hashCode += getAuth_atom_id().hashCode();
        }
        if (getAuth_comp_id() != null) {
            _hashCode += getAuth_comp_id().hashCode();
        }
        if (getAuth_seq_id() != null) {
            _hashCode += getAuth_seq_id().hashCode();
        }
        if (getB_equiv_geom_mean() != null) {
            _hashCode += getB_equiv_geom_mean().hashCode();
        }
        if (getB_equiv_geom_mean_esd() != null) {
            _hashCode += getB_equiv_geom_mean_esd().hashCode();
        }
        if (getB_iso_or_equiv() != null) {
            _hashCode += getB_iso_or_equiv().hashCode();
        }
        if (getB_iso_or_equiv_esd() != null) {
            _hashCode += getB_iso_or_equiv_esd().hashCode();
        }
        if (getCalc_attached_atom() != null) {
            _hashCode += getCalc_attached_atom().hashCode();
        }
        if (getCalc_flag() != null) {
            _hashCode += getCalc_flag().hashCode();
        }
        if (getCartn_x() != null) {
            _hashCode += getCartn_x().hashCode();
        }
        if (getCartn_x_esd() != null) {
            _hashCode += getCartn_x_esd().hashCode();
        }
        if (getCartn_y() != null) {
            _hashCode += getCartn_y().hashCode();
        }
        if (getCartn_y_esd() != null) {
            _hashCode += getCartn_y_esd().hashCode();
        }
        if (getCartn_z() != null) {
            _hashCode += getCartn_z().hashCode();
        }
        if (getCartn_z_esd() != null) {
            _hashCode += getCartn_z_esd().hashCode();
        }
        if (getChemical_conn_number() != null) {
            _hashCode += getChemical_conn_number().hashCode();
        }
        if (getConstraints() != null) {
            _hashCode += getConstraints().hashCode();
        }
        if (getDetails() != null) {
            _hashCode += getDetails().hashCode();
        }
        if (getDisorder_assembly() != null) {
            _hashCode += getDisorder_assembly().hashCode();
        }
        if (getDisorder_group() != null) {
            _hashCode += getDisorder_group().hashCode();
        }
        if (getFootnote_id() != null) {
            _hashCode += getFootnote_id().hashCode();
        }
        if (getFract_x() != null) {
            _hashCode += getFract_x().hashCode();
        }
        if (getFract_x_esd() != null) {
            _hashCode += getFract_x_esd().hashCode();
        }
        if (getFract_y() != null) {
            _hashCode += getFract_y().hashCode();
        }
        if (getFract_y_esd() != null) {
            _hashCode += getFract_y_esd().hashCode();
        }
        if (getFract_z() != null) {
            _hashCode += getFract_z().hashCode();
        }
        if (getFract_z_esd() != null) {
            _hashCode += getFract_z_esd().hashCode();
        }
        if (getGroup_PDB() != null) {
            _hashCode += getGroup_PDB().hashCode();
        }
        if (getId_() != null) {
            _hashCode += getId_().hashCode();
        }
        if (getLabel_alt_id() != null) {
            _hashCode += getLabel_alt_id().hashCode();
        }
        if (getLabel_asym_id() != null) {
            _hashCode += getLabel_asym_id().hashCode();
        }
        if (getLabel_atom_id() != null) {
            _hashCode += getLabel_atom_id().hashCode();
        }
        if (getLabel_comp_id() != null) {
            _hashCode += getLabel_comp_id().hashCode();
        }
        if (getLabel_entity_id() != null) {
            _hashCode += getLabel_entity_id().hashCode();
        }
        if (getLabel_seq_id() != null) {
            _hashCode += getLabel_seq_id().hashCode();
        }
        if (getOccupancy() != null) {
            _hashCode += getOccupancy().hashCode();
        }
        if (getOccupancy_esd() != null) {
            _hashCode += getOccupancy_esd().hashCode();
        }
        if (getPdbx_PDB_atom_name() != null) {
            _hashCode += getPdbx_PDB_atom_name().hashCode();
        }
        if (getPdbx_PDB_ins_code() != null) {
            _hashCode += getPdbx_PDB_ins_code().hashCode();
        }
        if (getPdbx_PDB_model_num() != null) {
            _hashCode += getPdbx_PDB_model_num().hashCode();
        }
        if (getPdbx_PDB_residue_name() != null) {
            _hashCode += getPdbx_PDB_residue_name().hashCode();
        }
        if (getPdbx_PDB_residue_no() != null) {
            _hashCode += getPdbx_PDB_residue_no().hashCode();
        }
        if (getPdbx_PDB_strand_id() != null) {
            _hashCode += getPdbx_PDB_strand_id().hashCode();
        }
        if (getPdbx_auth_alt_id() != null) {
            _hashCode += getPdbx_auth_alt_id().hashCode();
        }
        if (getPdbx_auth_atom_name() != null) {
            _hashCode += getPdbx_auth_atom_name().hashCode();
        }
        if (getRefinement_flags() != null) {
            _hashCode += getRefinement_flags().hashCode();
        }
        if (getRefinement_flags_adp() != null) {
            _hashCode += getRefinement_flags_adp().hashCode();
        }
        if (getRefinement_flags_occupancy() != null) {
            _hashCode += getRefinement_flags_occupancy().hashCode();
        }
        if (getRefinement_flags_posn() != null) {
            _hashCode += getRefinement_flags_posn().hashCode();
        }
        if (getRestraints() != null) {
            _hashCode += getRestraints().hashCode();
        }
        if (getSymmetry_multiplicity() != null) {
            _hashCode += getSymmetry_multiplicity().hashCode();
        }
        if (getThermal_displace_type() != null) {
            _hashCode += getThermal_displace_type().hashCode();
        }
        if (getType_symbol() != null) {
            _hashCode += getType_symbol().hashCode();
        }
        if (getU_equiv_geom_mean() != null) {
            _hashCode += getU_equiv_geom_mean().hashCode();
        }
        if (getU_equiv_geom_mean_esd() != null) {
            _hashCode += getU_equiv_geom_mean_esd().hashCode();
        }
        if (getU_iso_or_equiv() != null) {
            _hashCode += getU_iso_or_equiv().hashCode();
        }
        if (getU_iso_or_equiv_esd() != null) {
            _hashCode += getU_iso_or_equiv_esd().hashCode();
        }
        if (getWyckoff_symbol() != null) {
            _hashCode += getWyckoff_symbol().hashCode();
        }
        if (getObj_id() != null) {
            _hashCode += getObj_id().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AtomCoords.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("webservices.pdb.org", "AtomCoords"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adp_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adp_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_B11");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_B11"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_B11_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_B11_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_B12");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_B12"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_B12_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_B12_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_B13");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_B13"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_B13_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_B13_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_B22");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_B22"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_B22_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_B22_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_B23");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_B23"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_B23_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_B23_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_B33");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_B33"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_B33_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_B33_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_U11");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_U11"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_U11_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_U11_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_U12");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_U12"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_U12_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_U12_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_U13");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_U13"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_U13_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_U13_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_U22");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_U22"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_U22_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_U22_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_U23");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_U23"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_U23_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_U23_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_U33");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_U33"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_U33_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_U33_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniso_ratio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aniso_ratio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attached_hydrogens");
        elemField.setXmlName(new javax.xml.namespace.QName("", "attached_hydrogens"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth_asym_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auth_asym_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth_atom_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auth_atom_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth_comp_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auth_comp_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth_seq_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auth_seq_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("b_equiv_geom_mean");
        elemField.setXmlName(new javax.xml.namespace.QName("", "b_equiv_geom_mean"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("b_equiv_geom_mean_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "b_equiv_geom_mean_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("b_iso_or_equiv");
        elemField.setXmlName(new javax.xml.namespace.QName("", "b_iso_or_equiv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("b_iso_or_equiv_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "b_iso_or_equiv_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("calc_attached_atom");
        elemField.setXmlName(new javax.xml.namespace.QName("", "calc_attached_atom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("calc_flag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "calc_flag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cartn_x");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cartn_x"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cartn_x_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cartn_x_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cartn_y");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cartn_y"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cartn_y_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cartn_y_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cartn_z");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cartn_z"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cartn_z_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cartn_z_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chemical_conn_number");
        elemField.setXmlName(new javax.xml.namespace.QName("", "chemical_conn_number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("constraints");
        elemField.setXmlName(new javax.xml.namespace.QName("", "constraints"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details");
        elemField.setXmlName(new javax.xml.namespace.QName("", "details"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("disorder_assembly");
        elemField.setXmlName(new javax.xml.namespace.QName("", "disorder_assembly"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("disorder_group");
        elemField.setXmlName(new javax.xml.namespace.QName("", "disorder_group"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("footnote_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "footnote_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fract_x");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fract_x"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fract_x_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fract_x_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fract_y");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fract_y"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fract_y_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fract_y_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fract_z");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fract_z"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fract_z_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fract_z_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("group_PDB");
        elemField.setXmlName(new javax.xml.namespace.QName("", "group_PDB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id_"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("label_alt_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "label_alt_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("label_asym_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "label_asym_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("label_atom_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "label_atom_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("label_comp_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "label_comp_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("label_entity_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "label_entity_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("label_seq_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "label_seq_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("occupancy");
        elemField.setXmlName(new javax.xml.namespace.QName("", "occupancy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("occupancy_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "occupancy_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pdbx_PDB_atom_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pdbx_PDB_atom_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pdbx_PDB_ins_code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pdbx_PDB_ins_code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pdbx_PDB_model_num");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pdbx_PDB_model_num"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pdbx_PDB_residue_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pdbx_PDB_residue_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pdbx_PDB_residue_no");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pdbx_PDB_residue_no"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pdbx_PDB_strand_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pdbx_PDB_strand_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pdbx_auth_alt_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pdbx_auth_alt_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pdbx_auth_atom_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pdbx_auth_atom_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("refinement_flags");
        elemField.setXmlName(new javax.xml.namespace.QName("", "refinement_flags"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("refinement_flags_adp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "refinement_flags_adp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("refinement_flags_occupancy");
        elemField.setXmlName(new javax.xml.namespace.QName("", "refinement_flags_occupancy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("refinement_flags_posn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "refinement_flags_posn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("restraints");
        elemField.setXmlName(new javax.xml.namespace.QName("", "restraints"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("symmetry_multiplicity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "symmetry_multiplicity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("thermal_displace_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "thermal_displace_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type_symbol");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type_symbol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_equiv_geom_mean");
        elemField.setXmlName(new javax.xml.namespace.QName("", "u_equiv_geom_mean"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_equiv_geom_mean_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "u_equiv_geom_mean_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_iso_or_equiv");
        elemField.setXmlName(new javax.xml.namespace.QName("", "u_iso_or_equiv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_iso_or_equiv_esd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "u_iso_or_equiv_esd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("wyckoff_symbol");
        elemField.setXmlName(new javax.xml.namespace.QName("", "wyckoff_symbol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("obj_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "obj_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
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
