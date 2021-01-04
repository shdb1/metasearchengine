package com.metasearchengine.ir60.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.metasearchengine.ir60.api.MetaSearchEngineAPI;
import com.metasearchengine.ir60.model.NewsResults;
import com.metasearchengine.ir60.service.MetaSearchEngineService;

/**
 * @author moshadab
 *
 */
@RestController
public class MetaSearchEngineController implements MetaSearchEngineAPI {

	@Autowired
	MetaSearchEngineService metaSearchEngineService;

	@Override
	public List<NewsResults> rankNews(String query) {
		// TODO Auto-generated method stub
		System.out.println("name to search is:"+query);
		return metaSearchEngineService.searchNews(query);
		
	}

}
