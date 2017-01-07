/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdx.renameFactu.controller.interfaces;

import com.jdx.renameFactu.entitys.FileEntity;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author EQTIC_PROD_JFH
 */
public interface IFileController {
    
    public int renameFiles(ArrayList<FileEntity> files, JTextArea log);
    
}
