package com.learnhall.logic;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/***
 * 题内转换攻击
 * 
 * @author Canyon 2017-04-16 23:30
 */
public class ContentTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	int index;
	String content;

	public void setIndex(int index) {
		this.index = index;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int doStartTag() throws JspException {

		try {
			int indP = content.indexOf("<p");
			String v = content;
			if (indP == 0){
				int indEnd = content.indexOf(">");
				indEnd += indP + 1;
				v = content.substring(0, indEnd) + index + "."
						+ content.substring(indEnd);
			}else{
				v = index + "." + content;
			}
			pageContext.getOut().write(v);
		} catch (Exception e) {
		}
		return super.doStartTag();
	}

}
