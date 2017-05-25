package model;

public class CommentsVo {

	private int cm_idx;
	private String cm_writer;
	private String cm_date;
	private int b_idx;
	private String cm_content;
	private int cm_parent;
	private int cm_depth;
	private int cm_order;
	private String cm_delete;
	
	public int getCm_idx() {
		return cm_idx;
	}
	public void setCm_idx(int cm_idx) {
		this.cm_idx = cm_idx;
	}
	public String getCm_writer() {
		return cm_writer;
	}
	public void setCm_writer(String cm_writer) {
		this.cm_writer = cm_writer;
	}
	public String getCm_date() {
		return cm_date;
	}
	public void setCm_date(String cm_date) {
		this.cm_date = cm_date;
	}
	public int getB_idx() {
		return b_idx;
	}
	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}
	public String getCm_content() {
		return cm_content;
	}
	public void setCm_content(String cm_content) {
		this.cm_content = cm_content;
	}
	public int getCm_parent() {
		return cm_parent;
	}
	public void setCm_parent(int cm_parent) {
		this.cm_parent = cm_parent;
	}
	public int getCm_depth() {
		return cm_depth;
	}
	public void setCm_depth(int cm_depth) {
		this.cm_depth = cm_depth;
	}
	public int getCm_order() {
		return cm_order;
	}
	public void setCm_order(int cm_order) {
		this.cm_order = cm_order;
	}
	public String getCm_delete() {
		return cm_delete;
	}
	public void setCm_delete(String cm_delete) {
		this.cm_delete = cm_delete;
	}
}
