package com.gnosis.apkupdater.controller;

import com.gnosis.apkupdater.domain.Project;
import com.gnosis.apkupdater.domain.ProjectContext;
import com.gnosis.apkupdater.domain.Release;
import com.gnosis.apkupdater.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Fırat on 18.11.2015.
 */
@Controller
public class ProjectRestController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/getLastVersion", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Release getLastVersion(@RequestParam String packageName) {
        return projectService.getLastRelease(packageName);
    }
    @RequestMapping(value = "/getLastVersionDate", method = RequestMethod.GET)
    public
    @ResponseBody
    String getLastVersion2(@RequestParam String packageName) {
        return projectService.getLastRelease(packageName).getDate().getTime()+"";
    }
    @RequestMapping(value = "/downloadLastVersionByPackageName", method = RequestMethod.GET   , produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
    @ResponseBody
    public FileSystemResource downloadLastVersion(@RequestParam("packageName") String packageName) {
        return new FileSystemResource(projectService.getLastRelease(packageName).getApkUrl());
    }

    @RequestMapping(value = "/downloadLastVersionByProjectName", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource downloadLastVersion2(@RequestParam("projectName") String projectName) {
        //getting last release apk
        List<Project> projects = projectService.getProjectContext().getProjects().stream().filter(project -> project.getName().equals(projectName)).collect(Collectors.toList());
        Collections.sort(projects, new Comparator<Project>() {
            public int compare(Project p1, Project p2) {
                return p1.getLastRelease().getDate().compareTo(p2.getLastRelease().getDate());
            }
        });
        return new FileSystemResource(projects.get(0).getLastRelease().getApkUrl());
    }
}
