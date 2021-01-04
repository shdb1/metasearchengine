package com.metasearchengine.ir60.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.metasearchengine.ir60.constants.MetaSearchEngineConstants;
import com.metasearchengine.ir60.model.NewsResults;
import com.metasearchengine.ir60.util.HTMLParser;
import com.metasearchengine.ir60.util.HttpUtil;



/**
 * @author moshadab
 *
 */
@Service
public class MetaSearchEngineServiceImpl implements MetaSearchEngineService {


	@Override
	public List<NewsResults> searchNews(String queryString) {
		// TODO Auto-generated method stub

		// get yahoo news
		List<NewsResults> finalresult=null;

		try {
			String googleNews = HttpUtil.doHTTPGetID(MetaSearchEngineConstants.ENGINE_GOOGLE_BASE_URL
					+ encode(queryString) + MetaSearchEngineConstants.ENGINE_HELPING_STRING_GOOGLE);

			PrintWriter out = new PrintWriter("filename_google.txt");
			out.println(HTMLParser.parseHTMLFromURL(MetaSearchEngineConstants.ENGINE_GOOGLE_BASE_URL
					+ encode(queryString) + MetaSearchEngineConstants.ENGINE_HELPING_STRING_GOOGLE));

			List<NewsResults> googleResults = parseNews(googleNews,
					MetaSearchEngineConstants.SEARCH_ENGINE_TYPE_GOOGLE);
			String yahooNews = HttpUtil.doHTTPGetID(MetaSearchEngineConstants.ENGINE_YAHOO_BASE_URL
					+ encode(queryString) + MetaSearchEngineConstants.ENGINE_HELPING_STRING_YAHOO);
			List<NewsResults> yahooResults = parseNews(yahooNews, MetaSearchEngineConstants.SEARCH_ENGINE_TYPE_YAHOO);
			  finalresult = rankResults(googleResults, yahooResults);
			// Store the ranked results to file ResultantRanks_A1.txt
			storeResultsToFile(finalresult, "ResultantRanks_A1.txt");
			
 		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return finalresult;
	}

	private String encode(String url)

	{
		try {

			String encodeURL = URLEncoder.encode(url, "UTF-8");

			return encodeURL;

		} catch (UnsupportedEncodingException e) {

			return "Issue while encoding" + e.getMessage();

		}

	}

	public void parseYahooNews(String yahooNews) {
		int rank = 1;
		StringBuffer finalResults = new StringBuffer(MetaSearchEngineConstants.RESULT_HEADER);
		String[] data = yahooNews.split("<a href=");
		for (String urldata : data) {
			if (urldata.contains("class=\"\">")) {
				String[] classData = urldata.split("class=\"\">");
				// </a></h3>
				for (String titles : classData) {
					if (titles.contains("</a></h3>")) {
						String[] titlesData = titles.split("</a></h3><div");
						finalResults.append("\n").append("Yahoo News Search Engine")

								.append(MetaSearchEngineConstants.META_SERACH_ENGINE_RESULTS_SPLITTER).append(rank)
								.append(MetaSearchEngineConstants.META_SERACH_ENGINE_RESULTS_SPLITTER)
								.append(titlesData[0]);
						rank++;
					}

				}
			}
		}
		System.out.println("finalResults:" + finalResults);
	}

	public List<NewsResults> parseNews(String yahooNews, int searchEngineType) {
		int rank = 1;
		String classSplitter = null;
		List<NewsResults> newsResults = new ArrayList<NewsResults>();
		String[] data = yahooNews.split("<a href=");
		System.out.println(data);
		System.out.println("");
		if (searchEngineType == MetaSearchEngineConstants.SEARCH_ENGINE_TYPE_GOOGLE) {
			classSplitter = MetaSearchEngineConstants.CLASS_IDENTIFIER_GOOGLE;
		} else {
			classSplitter = MetaSearchEngineConstants.CLASS_IDENTIFIER_YAHOO;
		}
		for (String urldata : data) {
			if (urldata.contains(classSplitter)) {
				String[] classData = urldata.split(classSplitter);
				// </a></h3>
				for (String titles : classData) {
					if (titles.contains("</a></h3><div")) {
						String[] titlesData = titles.split("</a></h3><div");
						if (searchEngineType == MetaSearchEngineConstants.SEARCH_ENGINE_TYPE_YAHOO) {
							NewsResults newResult = new NewsResults();
							newResult.setSerachEngineName("Yahoo News Search Engine");
							newResult.setTtile(titlesData[0]);
							newResult.setRank(rank);
							newsResults.add(newResult);
						} else {
							NewsResults newResult = new NewsResults();
							newResult.setSerachEngineName("Google News Search Engine");
							newResult.setTtile(titlesData[0].split("\" >")[1]);
							newResult.setRank(rank);
							newsResults.add(newResult);
						}

						rank++;

					}

				}
			}
		}
		Collections.sort(newsResults);
		return newsResults;
	}

	private List<NewsResults> rankResults(List<NewsResults> googleResults, List<NewsResults> yahooResults) {
	 
		int rank = 1;
		List<NewsResults> biggerList = null;
		List<NewsResults> smallerList = null;
		List<NewsResults> finalresult = new ArrayList<NewsResults>();
		if (googleResults.size() > yahooResults.size()) {
			biggerList = googleResults;
			smallerList = yahooResults;
		} else {
			biggerList = yahooResults;
			smallerList = googleResults;
		}

		for (int biggerItemIndex = 0; biggerItemIndex < biggerList.size(); biggerItemIndex++) {
			NewsResults tempResult = biggerList.get(biggerItemIndex);
			tempResult.setRank(rank);
			finalresult.add(tempResult);
			rank++;
			if (biggerItemIndex < smallerList.size()) {
				NewsResults tempResult2 = smallerList.get(biggerItemIndex);
				tempResult2.setRank(rank);
				finalresult.add(tempResult2);
				rank++;
			}

		}
		return finalresult;
	}
	private void storeResultsToFile(List<NewsResults> finalresult,String filename) {
		StringBuffer finaldataString= new StringBuffer(MetaSearchEngineConstants.RESULT_HEADER);
		for (NewsResults newsResult : finalresult) {
			finaldataString.append("\n")
			.append(newsResult.getSerachEngineName())
			.append(MetaSearchEngineConstants.META_SERACH_ENGINE_RESULTS_SPLITTER)
			.append(newsResult.getRank())
			.append(MetaSearchEngineConstants.META_SERACH_ENGINE_RESULTS_SPLITTER)
			.append(newsResult.getTtile());
		}
		try {
			PrintWriter printWriter = new PrintWriter(filename);
			printWriter.println(finaldataString);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
