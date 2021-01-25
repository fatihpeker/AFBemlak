
package controller;

import dbutil.DocumentDbUtil;
import entity.Document;
import entity.Ilan;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

@ManagedBean
@SessionScoped

public class DocumentController implements Serializable{
    private Document document;
    private DocumentDbUtil documentDbUtil;
    private Ilan ilantest;
    private Part doc;
    
    public DocumentController() throws Exception {
        document=new Document();
        documentDbUtil = documentDbUtil.getInstance();
        ilantest=Ilan.ilanInstance;
    }
    private final String uploadto= "/Users/bilal/OneDrive/Belgeler/ProgAppProject/EMLAK3/web/resources/upload/";
  
    public void upload(){
          try {
            InputStream input=doc.getInputStream();
            String fileName=Paths.get(getSubmittedFileName(doc)).getFileName().toString();
            String newName=fileName.split("[.]")[0]+".png";
            File file=new File(uploadto+newName);
            Files.copy(input, file.toPath());
            
            if(document==null)
                document=new Document();
            document.setFileName(newName);
            document.setFilePath(file.getParent());
            documentDbUtil.insert(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
     private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    public String getUploadto() {
        return uploadto;
    }
    
    public Part getDoc() {
        return doc;
    }

    public void setDoc(Part doc) {
        this.doc = doc;
    }
    
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Ilan getIlantest() {
        return ilantest;
    }

    public void setIlantest(Ilan ilantest) {
        this.ilantest = ilantest;
    }
    
    
    
    
}
