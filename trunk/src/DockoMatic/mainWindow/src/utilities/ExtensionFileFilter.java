//# Program: "DockoMatic"
//# Project: "DNA Safeguard"
//# Filename: "ALIFilter.java"
//#
//# Dr. Tim Andersen
//# Department of Computer Science
//# College of Engineering
//# Boise State University
//#
//# Original Author(s): "Casey Bullock"
//#		      "Nic Cornia"
//#
//# Last Modified
//#   Date: "November 14, 2012"
//#
//#
//#  This file is part of DockoMatic.
//#
//#  DockoMatic is free software: you can redistribute it and/or modify
//#  it under the terms of the GNU Lesser General Public License as published by
//#  the Free Software Foundation, either version 3 of the License, or
//#  (at your option) any later version.
//#
//#  DockoMatic is distributed in the hope that it will be useful,
//#  but WITHOUT ANY WARRANTY; without even the implied warranty of
//#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//#  GNU Lesser General Public License for more details.
//#
//#  You should have received a copy of the GNU Lesser General Public License
//#  along with DockoMatic.  If not, see <http://www.gnu.org/licenses/>.
//#

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author tlong
 */
public class ExtensionFileFilter extends FileFilter {
    
    public final static String pdb = ".pdb";
    public final static String gpf = ".gpf";
    public final static String ali = ".ali";
    public final static String txt = ".txt";
    public final static String any = "n";
    
    private final static String pdb_description = "Program Database Files (.pdb)";
    private final static String gpf_description = "Grid Parameter Files (.gpf)";
    private final static String ali_description = "Modeller Alignment Readable Files(.ali)";
    private final static String txt_description = "Vina Configuration Files (.txt)";
    private final static String any_description = "All Files";
    
    private final String extension;
    private final String description;
    
    public ExtensionFileFilter(String extension){
        if(extension.equalsIgnoreCase(pdb)){
            this.extension = pdb;
            this.description = pdb_description;
        }else if(extension.equalsIgnoreCase(gpf)){
            this.extension = gpf;
            this.description = gpf_description;
        }else if(extension.equalsIgnoreCase(ali)){
            this.extension = ali;
            this.description = ali_description;
        }else if(extension.equalsIgnoreCase(txt)){
            this.extension = txt;
            this.description = txt_description;
        }else{
            this.extension = any;
            this.description = any_description;
        }
    }
    
    @Override
    public boolean accept(File f) {
        boolean rval = false;
        if (f.isDirectory()) {
            rval = true;
        }else if(has_correct_extension(f)){
            rval = true;
        } 
        return rval;
    }

    @Override
    public String getDescription() {
        return description;
    }

    private boolean has_correct_extension(File f) {
        boolean rval;
        if(extension.equals(any)){
            rval = true;
        }else{
            rval = f.getName().endsWith(extension);
        }
                    
        return rval;
    }
}

