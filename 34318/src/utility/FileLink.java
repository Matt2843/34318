package utility;

public class FileLink {
	
	private final String FILE_ID;
	private final String FILE_NAME;
	
	public FileLink(String FILE_ID, String FILE_NAME) {
		this.FILE_ID = FILE_ID;
		this.FILE_NAME = FILE_NAME;
	}
	
	public String getFileID() {
		return FILE_ID;
	}
	
	public String getFileName() {
		return FILE_NAME;
	}
	
	@Override
	public String toString() {
		return FILE_NAME;
	}

}
