package com.metasearchengine.ir60.service;

import java.util.List;

import com.metasearchengine.ir60.model.NewsResults;

/**
 * @author moshadab
 *
 */
public interface MetaSearchEngineService {
	

	public List<NewsResults> searchNews(String queryString);

}
