package com.gnosis.apkupdater.controller;

import com.gnosis.apkupdater.domain.Project;
import com.gnosis.apkupdater.domain.ProjectContext;
import com.gnosis.apkupdater.domain.Release;
import com.gnosis.apkupdater.service.ProjectService;
import com.gnosis.apkupdater.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by Fırat on 18.11.2015.
 */
@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/uploadProject", method = RequestMethod.POST)
    public String uploadProject(@RequestParam MultipartFile file, @RequestParam String name, @RequestParam String packageName) {
        Date date = new Date();
        String apkDir = packageName + date.getTime() + ".apk";
        Release release = new Release();
        release.setDate(date);
        release.setName(name);
        release.setApkDir(apkDir);

        ProjectContext projectContext = projectService.getProjectContext();
        Project currentProject = projectContext.getProject(packageName);
        currentProject.getReleases().add(release);
        currentProject.setLastRelease(release);
        boolean result = FileUtil.saveMultipart(file, apkDir);
        projectService.saveProjectContext(projectContext);
        return "redirect:/index";
    }


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String getCustomerProjects(Model model) {
        model.addAttribute("projectcontext", projectService.getProjectContext());
        model.addAttribute("project", new Project());
        return "index";
    }

    @RequestMapping(value = "/createProject", method = RequestMethod.POST)
    public String createProject(Project project) {
        if (projectService.getProjectContext().getProject(project.getPackageName()) != null) {
            return "redirect:/index";
        }
        ProjectContext projectContext = projectService.getProjectContext();
        projectContext.getProjects().add(project);
        projectService.saveProjectContext(projectContext);
        return "redirect:/index";
    }

    @RequestMapping(value = "/deleteProject", method = RequestMethod.GET)
    public String deleteProject(String packageName) {
        ProjectContext projectContext = projectService.getProjectContext();
        projectContext.getProject(packageName).getReleases().forEach(project -> FileUtil.deleteFile(project.getApkUrl()));//first we need delete all real files
        projectContext.getProjects().remove(projectContext.getProject(packageName));
        projectService.saveProjectContext(projectContext);

        return "redirect:/index";
    }
}
