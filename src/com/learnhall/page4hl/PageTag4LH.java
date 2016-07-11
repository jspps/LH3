package com.learnhall.page4hl;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.bowlong.io.FileRw;
import com.bowlong.lang.StrEx;
import com.bowlong.objpool.StringBufPool;
import com.bowlong.util.page.PageEnt;
import com.learnhall.content.JSONContext;

/**
 * 分页标签
 * 
 * @ClassName: PageTag4LH
 * @author Canyon
 * 
 */
@SuppressWarnings("rawtypes")
public class PageTag4LH extends TagSupport {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 2771143709830017519L;
	static String pageTxt = "";
	private String action;

	public void setAction(String action) {
		this.action = action;
		if (StrEx.isEmpty(pageTxt)) {
			String path = JSONContext.class.getClassLoader().getResource("")
					.getPath();
			path = path + "com/learnhall/page4hl/page_cur.txt";
			pageTxt = FileRw.readStr(path);
		}
	}

	private PageEnt pe;

	public void setName(String name) {
		Object result = this.pageContext.findAttribute(name);
		if (result != null && result instanceof PageEnt) {
			this.pe = (PageEnt) result;
		}
	}

	@Override
	public int doEndTag() throws JspException {
		if (this.pe == null) {
			return SKIP_BODY;
		}
		String pg = "";
		StringBuffer buff = StringBufPool.borrowObject();
		int allPage = pe.getTotalPages();
		int curPage = pe.getPage();
		try {
			boolean isMore = allPage > 8;
			boolean isRt = isMore;
			int begPage = 1;
			int endPage = 1;
			if (isMore) {
				int difPage = allPage - curPage;
				if (difPage > 8) {
					begPage = curPage;
					endPage = begPage + 7;
				} else {
					isRt = false;
					endPage = allPage;
					begPage = endPage - 7;
				}
			} else {
				begPage = 1;
				endPage = allPage;
			}

			for (; begPage <= endPage; begPage++) {
				buff.append("<a href=\"javascript:void(0);\"");

				if (begPage == curPage) {
					buff.append(" style=\"margin:0px 2px;padding:1px 12px;border:1px solid #f45819;\"");
				}else{
					buff.append(" style=\"margin:0px 2px;padding:1px 12px;\"");
				}

				buff.append(" onclick=\"click2sendPaging(").append(begPage)
						.append(")\">").append(begPage).append("</a>");
			}

			pg = buff.toString();
			if (isMore) {
				if (isRt) {
					pg = pg + "...";
				} else {
					pg = "..." + pg;
				}
			}
		} finally {
			StringBufPool.returnObject(buff);
		}

		try {
			String ret = "";
			if (!StrEx.isEmpty(pg))
				ret = StrEx.fmt(pageTxt, pg, action, curPage, allPage);
			JspWriter writer = this.pageContext.getOut();
			writer.print(ret);
			writer.flush();
			writer.clearBuffer();
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}
}
