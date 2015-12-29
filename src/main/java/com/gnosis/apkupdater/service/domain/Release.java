package com.gnosis.apkupdater.domain;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * Created by F�rat on 18.11.2015.
 */
public class Release {
    private String name;
    private Date date;
    private String apkDir;
    private Project project;

    public Release() {
    }

    public File getFile() {
        return new File(apkDir);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getApkDir() {

        return apkDir;
    }

    public String getApkUrl() {
        return System.getProperty("user.home") + File.separator + "projectFiles" + File.separator + apkDir;
    }

    public void setApkDir(String apkDir) {
        this.apkDir = apkDir;
    }

   /* public String getApkUrl() {
        return project.getPackageName() + getDate() + ".apk";
    }*/
}
