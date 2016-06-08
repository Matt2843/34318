package utility;

import java.io.Serializable;

public class FileTemplate implements Serializable {
	private static final long serialVersionUID = 4387360185168030899L;
	
	int fileSize = 0;
	String fileType = null;
	String fileName = null;
	Object file = null;
	
	public FileTemplate(int fileSize, String fileType, String fileName, Object file) {
		this.fileSize = fileSize;
		this.fileType = fileType;
		this.fileName = fileName;
		this.file = file;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Object getFile() {
		return file;
	}

	public void setFile(Object file) {
		this.file = file;
	}
	
	
}
