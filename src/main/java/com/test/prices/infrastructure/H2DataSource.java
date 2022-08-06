package com.test.prices.infrastructure;

public class H2DataSource {
    private String url;
    private String username;
    private int poolSize;

    public H2DataSource() {
    }

    public H2DataSource(String url, String username, int poolSize) {
        this.url = url;
        this.username = username;
        this.poolSize = poolSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }
}
