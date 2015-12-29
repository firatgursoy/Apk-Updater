package com.gnosis.apkupdater.service;

import com.gnosis.apkupdater.domain.ProjectContext;
import com.gnosis.apkupdater.domain.Release;
import com.gnosis.apkupdater.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Created by Fırat on 18.11.2015.
 */
@Service
public class ProjectService {
    private final String PROJECT_CONTEXT_FILE = "projectcontext.json";

    public ProjectContext getProjectContext() {
        try {
            return JsonUtil.fromJson(new File(PROJECT_CONTEXT_FILE), ProjectContext.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveProjectContext(ProjectContext projectContext) {
        try {
            JsonUtil.toJSonAsFile(new File(PROJECT_CONTEXT_FILE), projectContext);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Release getLastRelease(String packageName) {
        return getProjectContext().getProject(packageName).getLastRelease();
    }


}
