package com.metasearchengine.ir60.util;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author moshadab
 *
 */
public class HTMLParser {

	public static String parseHTMLFromURL(String searchUrl) {
		
		System.out.println("URL TO PARSE:"+searchUrl);
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);

		HtmlPage page = null;
		try {
			page = client.getPage(searchUrl);
		} catch (FailingHttpStatusCodeException | IOException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		} finally {
			client.close();
		}
		return page.asText();

	}

}
