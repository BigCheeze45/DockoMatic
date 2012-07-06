/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainWindow;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author ncornia
 */
public class PDBFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.pdb)) {
                return true;
            } else {
                return false;
            }
        }

        return false;

    }

    //The description of this filter
    @Override
    public String getDescription() {
        return "Program Database Files (.pdb)";
    }
}
