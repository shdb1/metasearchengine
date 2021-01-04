package com.metasearchengine.ir60.model;

public class NewsResults implements Comparable<NewsResults> {

	private String ttile;
	private String url;
	private int rank;
	private String serachEngineName;

	public String getTtile() {
		return ttile;
	}

	public void setTtile(String ttile) {
		this.ttile = ttile;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getSerachEngineName() {
		return serachEngineName;
	}

	public void setSerachEngineName(String serachEngineName) {
		this.serachEngineName = serachEngineName;
	}

	@Override
	public String toString() {
		return "NewsResults [ttile=" + ttile + ", url=" + url + ", rank=" + rank + ", serachEngineName="
				+ serachEngineName + "]";
	}

	@Override
	public int compareTo(NewsResults o) {
		// TODO Auto-generated method stub
		if (this.rank > o.rank)
			return 1;
		else if (this.rank < o.rank)
			return -1;
		else
			return 0;
	}

}