package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {

	private File file;
	private String filename;
	public DownloadView(File file, String filename) {
		this.file = file;
		this.filename = filename;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		resp.setContentType("application/download; UTF-8");
		resp.setContentLength((int) file.length());

		String filename = new String(this.filename.getBytes("UTF-8"), "ISO-8859-1");
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");
		resp.setHeader("Content-Transfer-Encoding", "binary");

		OutputStream out = resp.getOutputStream();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

}
