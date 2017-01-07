/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdx.renameFactu.model;

import com.jdx.renameFactu.entitys.FileEntity;
import com.jdx.renameFactu.exception.ImposibleDeleteDupliFile;
import com.jdx.renameFactu.exception.ImposibleDeleteFile;
import com.jdx.renameFactu.exception.ImposibleRenameFile;
import com.jdx.renameFactu.model.interfaces.IFileModel;
import java.io.File;

/**
 *
 * @author EQTIC_PROD_JFH
 */
public class FileModel implements IFileModel {

    public void delete(FileEntity file) throws ImposibleDeleteFile {
        if (!file.delete()) {
            throw new ImposibleDeleteFile("No se ha podido eliminar el fichero");
        }
    }

    public void renameTo(FileEntity file, String nombre) throws ImposibleRenameFile {

        if (!file.renameTo(new File(nombre))) {
            throw new ImposibleRenameFile("No se ha podido renombrar el fichero");
        }

    }

    public boolean deleteDupli(FileEntity file, String nombre) throws ImposibleDeleteDupliFile {

           try {
                delete(file);
                return true;
            } catch (ImposibleDeleteFile idf) {
                throw new ImposibleDeleteFile("No se ha podido eliminar el duplicado");
            }
    }

}
