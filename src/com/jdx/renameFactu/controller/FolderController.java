/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdx.renameFactu.controller;

import com.jdx.renameFactu.controller.interfaces.IFolderController;
import com.jdx.renameFactu.entitys.FileEntity;
import com.jdx.renameFactu.model.FileModel;
import com.jdx.renameFactu.model.interfaces.IFileModel;
import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author EQTIC_PROD_JFH
 */
public class FolderController implements IFolderController {
    
    private IFileModel model = new FileModel();
  
    public boolean validateFolder(File folder) throws Exception{
        if (folder.isDirectory())
            return true;
        else
            throw new Exception("El directorio no es válido o accesible");
    }

  
    public boolean cleanFiles(File folder) throws Exception{
        FilenameFilter textFilterDel = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                return !lowercaseName.startsWith("file") ? true : false;
            }
        };
        
        File[] filesDel = folder.listFiles(textFilterDel);
        
        for (File indexFile : filesDel) {
            model.delete(new FileEntity(indexFile));
        }
        //Sino se lanza excepción todos los ficheros serán del array serán borrados
        return true;
    }

   
    
}
