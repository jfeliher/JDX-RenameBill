/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdx.renameFactu.controller.interfaces;

import java.io.File;

/**
 *
 * @author EQTIC_PROD_JFH
 */
public interface IFolderController {
    
    public boolean validateFolder(File file) throws Exception;
    
    public boolean cleanFiles(File file) throws Exception;
    
}
