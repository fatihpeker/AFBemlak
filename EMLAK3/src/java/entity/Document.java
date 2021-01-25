
package entity;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Document {
   private int id;
   private int ilanId;
   private String fileName;
   private String filePath;
   
   public Document(){
       
   }

    public Document(int id, int ilanId, String fileName, String filePath) {
        this.id = id;
        this.ilanId = ilanId;
        this.fileName = fileName;
        this.filePath = filePath;
    }
   
   
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIlanId() {
        return ilanId;
    }

    public void setIlanId(int ilanId) {
        this.ilanId = ilanId;
    }
   

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    } 
}
