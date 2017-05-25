package model;

public class BoardFile {
	private int f_id;
	private String originFileName;
	private int size;
	private String uri;
	
	
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public String getOriginFileName() {
		return originFileName;
	}
	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	@Override
	public String toString() {
		return "BoardFile [f_id=" + f_id + ", originFileName=" + originFileName + ", size=" + size + ", uri=" + uri
				+ "]";
	}
	
	
}
