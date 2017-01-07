/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdx.renameFactu.entitys;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 * @author EQTIC_PROD_JFH
 */
public class FolderEntity extends File {

    private ArrayList<FileEntity> files;
    
    public FolderEntity(String pathname) {
        super(pathname);
    }

    public ArrayList<FileEntity> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<FileEntity> files) {
        this.files = files;
    }
    
    public void findFiles() throws Exception {
        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.startsWith("file")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        
        files =new ArrayList<FileEntity>();
        for(File file : this.listFiles(textFilter)){
            files.add(new FileEntity(file));
        }
        
        if (0 == files.size() )
            throw new Exception("No existen ficheros a procesar");
    }
}
