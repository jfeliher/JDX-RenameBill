/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdx.renameFactu.model.interfaces;

import com.jdx.renameFactu.entitys.FileEntity;
import com.jdx.renameFactu.exception.ImposibleDeleteDupliFile;
import com.jdx.renameFactu.exception.ImposibleDeleteFile;
import com.jdx.renameFactu.exception.ImposibleRenameFile;

/**
 *
 * @author EQTIC_PROD_JFH
 */
public interface IFileModel {
    
    public void delete(FileEntity file) throws ImposibleDeleteFile;
    
    public void renameTo(FileEntity file, String nombre) throws ImposibleRenameFile;
    
    public boolean deleteDupli(FileEntity file, String nombre) throws ImposibleDeleteDupliFile;
}
