/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.boisestate.SimSearcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.openide.util.Exceptions;

/**
 *
 * @author tlong
 */
public class SimSearchUtilities {

    public final static String DIRECTORIES = "directories";
    public final static String FGROUP_EXTENSION = "fgx";
    public final static String CONFIG_FILE = "config.txt";
    public final static String DELIM = ",";
    public final static String CONFIG_DELIM = "=";
    public final static String[] similarity_metrics = {"chiSquare","pdfL1","pdfL2","rootOfProduct"};
    public final static String FUNCTIONAL_GROUPS = "functionalGroups";

    public static String getDirChoose(String lastDir, String title, String ext_desc, String[] extensions) {
        String dir = null;
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser(lastDir);
        fc.setDialogTitle(title);
        if (ext_desc.equals(DIRECTORIES)) {
            fc.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        } else {
            FileNameExtensionFilter filter = new FileNameExtensionFilter(ext_desc, extensions);
            fc.setFileFilter(filter);
        }
        int retval = fc.showOpenDialog(null);
        if (retval == fc.APPROVE_OPTION) {
            dir = fc.getSelectedFile().getAbsolutePath();
        }
        return dir;
    }

    public static String[] generatePartitions(File directory, String suffix, int numPartitions) {
        String[] sdf_files;
        final String new_suffix = suffix;
        sdf_files = directory.list(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                boolean rval = false;
                if (name.endsWith(new_suffix)) {
                    rval = true;
                }
                return rval;
            }
        });

        String[] partitions = new String[numPartitions];

        //initialize partitions
        for (int i = 0; i < partitions.length; i++) {
            partitions[i] = "";
        }

        int setNum = 0;

        String delim = ",";

        for (String sdf_file : sdf_files) {
            partitions[setNum] += sdf_file + delim;
            setNum = (setNum + 1) % numPartitions;
        }

        return partitions;
    }

    public static String getPreamble(String class_path, String main_class) {
        String javaHome = System.getProperty("java.home");
        File f = new File(javaHome);
        f = new File(f, "bin");
        f = new File(f, "javaw.exe");  //check for windows

        String javaPath = f.exists() ? f.getAbsolutePath() : javaHome + File.separator + "bin" + File.separator + "java";

//        return javaPath + " -Xmx2g -cp " + class_path + " " + ;
        return javaPath + " -cp " + class_path + " " + main_class + " ";
    }
    
    public static HashMap<String,String> getProperties(File properties_file){
        HashMap<String,String> properties = null;

        if(properties_file.exists()){
            properties = new HashMap<String,String>();
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(properties_file));
                String line;
                
                while((line = reader.readLine()) != null){
                    String[] tmp = line.split(CONFIG_DELIM);
                    if(tmp.length == 2){
                        properties.put(tmp[0], tmp[1]);
                    }
                }
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }finally{
                try {
                    if(reader != null){
                        reader.close();
                    }
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
        return properties;
    }
}

class SuffixFileFilter implements FilenameFilter {

    String suffix;

    public SuffixFileFilter(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public boolean accept(File directory, String fileName) {
        return fileName.contains(suffix);
    }
}
