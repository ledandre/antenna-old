package br.com.ledtom.antenna.model.entity;

import java.util.List;
import br.com.orangescript.antenna.news.beans.News;

public class NewsList {

    private List<News> news;

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
}
