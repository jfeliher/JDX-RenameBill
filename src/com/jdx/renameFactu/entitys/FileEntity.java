/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdx.renameFactu.entitys;

import java.io.File;

/**
 *
 * @author EQTIC_PROD_JFH
 */
public class FileEntity extends File{
    
    public FileEntity(String pathname) {
        super(pathname);
    }
     public FileEntity(File file) {
        super(file.getAbsolutePath());
    }
    
}
