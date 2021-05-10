package com.open.player.browser.bean;

/**
 * Des:
 * Author:
 * Date:21-5-10
 * UpdateRemark:
 */
public final class VideoFile {
    private String name;
    private String path;
    private boolean isDirectory = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }
}
