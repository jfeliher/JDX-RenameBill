/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdx.renameFactu.controller;

import com.jdx.renameFactu.controller.interfaces.IFileController;
import com.jdx.renameFactu.entitys.FileEntity;
import com.jdx.renameFactu.exception.ImposibleDeleteDupliFile;
import com.jdx.renameFactu.exception.ImposibleRenameFile;
import com.jdx.renameFactu.model.FileModel;
import com.jdx.renameFactu.model.interfaces.IFileModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextArea;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author EQTIC_PROD_JFH
 */
public class FileController implements IFileController {

    private static final String PATRON = "(\\b(Factura)\\b\\s((([0-9]+){4})(R)?([0-9]+){5}\\b))";
    private static final String PATRON_COD="(((([0-9]+){4})(R)?([0-9]+){5}))";
    private final IFileModel fileModel = new FileModel();
    private final String EXT = ".pdf";

    @Override
    public int renameFiles(ArrayList<FileEntity> files, JTextArea log) {

        int cnt = 0;
        for (FileEntity file : files) {
            String nombreOld = file.getName();
            try {
                String nombre = getNumberFactu(file);
                if (!new File(nombre).exists()) {
                    fileModel.renameTo(file, nombre);
                    log.append("Renombrado fichero " + nombreOld + " correctamente" + System.lineSeparator());
                    cnt++;
                }else{
                     log.append("Factura duplicada " + nombreOld + ". Borramos fichero origen" + System.lineSeparator());
                    fileModel.deleteDupli(file, nombreOld);
                }
            } catch (FileNotFoundException fnfe) {
                log.append("El fichero " + nombreOld + " ya no existe" + System.lineSeparator());
                log.append(fnfe.getMessage() + System.lineSeparator());
            } catch (IOException io) {
                log.append("No se puede acceder al fichero " + nombreOld + System.lineSeparator());
                log.append(io.getMessage() + System.lineSeparator());
            } catch (ImposibleRenameFile irf) {
                log.append("No se ha podido renombrar el fichero " + nombreOld + " correctamente" + System.lineSeparator());
                log.append(irf.getMessage() + System.lineSeparator());
            } catch (ImposibleDeleteDupliFile idd) {
                log.append("No se ha podido borrar el fichero " + nombreOld + " correctamente" + System.lineSeparator());
                log.append(idd.getMessage() + System.lineSeparator());
            } catch (Exception e) {
                log.append("Se ha producido una excepción al procesar el fichero " + nombreOld + System.lineSeparator());
                log.append(e.getMessage() + System.lineSeparator());
            }
        }
        return cnt;
    }

    private String getNumberFactu(FileEntity file) throws FileNotFoundException, IOException, Exception {
        //Leemos fichero
        InputStream ifile = new FileInputStream(file);
        PDDocument pdDoc = PDDocument.load(ifile);
        ifile.close();

        //Acceso a contenido
        PDFTextStripper pdfStripper = new PDFTextStripper();
        pdfStripper.setStartPage(0);
        pdfStripper.setEndPage(1);

        //Definimos el patrón de busqueda
       
        Pattern pattern = Pattern.compile(PATRON);
        Matcher matcher = pattern.matcher(pdfStripper.getText(pdDoc));
        pdDoc.close();
       
        //Macheamos el patrón de rectificación primero
        if (matcher.find()) { //Si se encuentra el patrón que hemos definido renombramos
            String cadena = matcher.group(1);
            Pattern patCod = Pattern.compile(PATRON_COD);
            Matcher matCod = patCod.matcher(cadena);
            matCod.find();
            String cod = matCod.group(0).trim();
            return generarNombreFichero(file, cod);
        } else {
            throw new Exception("No se ha localizado el identificador de factura");
        }
    }

    /**
     * Tratamiento de separador de directorios dependiendo del sistema
     */
    private String generarNombreFichero(File file, String codigo) {
        StringBuilder nombre = new StringBuilder(5);
        //Concatenamos la ruta del directorio
        nombre.append(file.getParentFile().getAbsolutePath());
        //Seteamos el separador del sistema de archivos donde se ejecuta
        nombre.append(File.separator);
        //contatenamos el codigo de factura y extension
        nombre.append(codigo);
        nombre.append(EXT);

        return nombre.toString();
    }

}
