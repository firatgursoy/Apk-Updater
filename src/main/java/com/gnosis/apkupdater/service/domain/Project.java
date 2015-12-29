package com.gnosis.apkupdater.domain;

import javax.management.relation.Relation;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by F�rat on 18.11.2015.
 */
public class Project {
    private String name;
    private String packageName;
    private Release lastRelease;
    private List<Release> releases = new ArrayList<Release>();

    public Project() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Release getLastRelease() {
        return lastRelease;
    }

    public void setLastRelease(Release lastRelease) {
        this.lastRelease = lastRelease;
    }

    public List<Release> getReleases() {
        return releases;
    }

    public void setReleases(List<Release> releases) {
        this.releases = releases;
    }

}
