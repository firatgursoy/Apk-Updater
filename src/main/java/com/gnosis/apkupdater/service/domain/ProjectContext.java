package com.gnosis.apkupdater.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Fırat on 18.11.2015.
 */
@Component
public class ProjectContext {
    private List<Project> projects = new ArrayList<Project>();

    public ProjectContext() {
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Project getProject(final String packageName) {
        try {
            return getProjects().stream().filter(project -> project.getPackageName().equals(packageName)).collect(Collectors.toList()).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
