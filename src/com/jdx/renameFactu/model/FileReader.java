/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdx.renameFactu.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextArea;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author EQTIC_PROD_JFH
 */
public class FileReader extends File {

    private File[] files;
    private int filesRename;
    private String EXT = ".pdf";
    private JTextArea log;
    private final String PATRON = "(\\b(Factura)\\b\\s([0-9]+){1})"; 

    public FileReader(String pathname, JTextArea log) throws Exception {
        super(pathname);
        
        this.log = log;
        
    //    loadFiles();
    }
    /**
     * Cargamos los ficheros correctos que vamos a procesar
     * @throws Exception 
     */
//    private void loadFiles() throws Exception {
//        if (this.isDirectory()) {
//            readFiles();
//            deleteFiles();
//        } else {
//            throw new Exception("El fichero no es un directorio");
//        }
//    }
//
////    /**
////     * Eliminación de los archivos que no se deben renombrar
////     */
////    private void deleteFiles() {
////        FilenameFilter textFilterDel = new FilenameFilter() {
////            public boolean accept(File dir, String name) {
////                String lowercaseName = name.toLowerCase();
////                if (!lowercaseName.startsWith("file")) {
////                    return true;
////                } else {
////                    return false;
////                }
////            }
////        };
////        File[] filesDel = this.listFiles(textFilterDel);
////        for (File file : filesDel) {
////            file.delete();
////        }
////    }
//    
//
//    /**
//     * Cargamos el array que contendrá los ficheros a procesar
//     */
//    private void readFiles() {
//        FilenameFilter textFilter = new FilenameFilter() {
//            public boolean accept(File dir, String name) {
//                String lowercaseName = name.toLowerCase();
//                if (lowercaseName.startsWith("file")) {
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        };
//        files = this.listFiles(textFilter);
//    }
//
//    public int renamePdfs() {
//        int cnt = 0;
//        log.append("***********************************************" + System.lineSeparator());
//        log.append("Comienzo del proceso de renombrado" + System.lineSeparator());
//        log.append("***********************************************" + System.lineSeparator());
//        log.append(System.lineSeparator());
//        log.append("Se van a procesar: " + this.getFilesSize() + " ficheros" + System.lineSeparator());
//        log.append(System.lineSeparator());
//        for (File file : files) {
//
//            try {
//                //Leemos fichero
//                InputStream ifile = new FileInputStream(file);
//                PDDocument pdDoc = PDDocument.load(ifile);
//                ifile.close();
//
//                //Acceso a contenido
//                PDFTextStripper pdfStripper = new PDFTextStripper();
//                pdfStripper.setStartPage(0);
//                pdfStripper.setEndPage(1);
//
//                //Definimos el patrón de busqueda
//                Pattern pattern = Pattern.compile(PATRON);
//                Matcher matcher = pattern.matcher(pdfStripper.getText(pdDoc));
//
//                //Macheamos el patrón
//                if (matcher.find()) { //Si se encuentra el patrón que hemos definido renombramos
//                    String cod[] = matcher.group(1).split("\\s+");
//                    
//                    if (file.renameTo(new File(generarNombreFichero(file, cod[1].trim())))){
//                        log.append("Fichero "+file.getAbsolutePath()+" ha sido renombrado a " +cod[1].trim()+".pdf"+ System.lineSeparator());
//                        cnt++;
//                    }else
//                        log.append("Fichero "+file.getAbsolutePath()+" NO ha sido renombrado" + System.lineSeparator());
//                }else
//                    log.append("No se ha localizado el número de factura"+ System.lineSeparator());
//                
//                pdDoc.close();
//            } catch (IOException ex) {
//                log.append("------------------------------------------------" + System.lineSeparator());
//                log.append("Error en el procesamiento del fichero" + file.getName() + System.lineSeparator());
//                log.append("------------------------------------------------" + System.lineSeparator());
//            } catch (Exception e) {
//                log.append("------------------------------------------------" + System.lineSeparator());
//                log.append("Error en el procesamiento del fichero" + file.getName() + System.lineSeparator());
//                log.append("------------------------------------------------" + System.lineSeparator());
//            }
//        }
//        log.append(System.lineSeparator() + "**********************************" + System.lineSeparator());
//        log.append("Fin del proceso de renombrado");
//        log.append(System.lineSeparator() + "**********************************" + System.lineSeparator());
//        //limpiamos ficheros no procesados, como los duplicados
//      
//        return cnt;
//    }
//
////    /**
////     * Tratamiento de separador de directorios dependiendo del sistema
////     */
////    private String generarNombreFichero(File file, String codigo) {
////        StringBuilder nombre = new StringBuilder(5);
////        //Concatenamos la ruta del directorio
////        nombre.append(file.getParentFile().getAbsolutePath());
////
////        //Seteamos el separador del sistema de archivos donde se ejecuta
////        nombre.append(File.separator);
////        //contatenamos el codigo de factura y extension
////        nombre.append(codigo);
////        nombre.append(EXT);
////
////        return nombre.toString();
////    }
//
//    public int getFilesSize() {
//        return files.length;
//    }
//
}
