package com.metasearchengine.ir60.api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.metasearchengine.ir60.model.NewsResults;

/**
 * @author moshadab
 *
 */
public interface MetaSearchEngineAPI {
	 @RequestMapping("/news/search")
	    public List<NewsResults> rankNews(@RequestParam(value="query", defaultValue="icc schedule 2021") String name);

}
