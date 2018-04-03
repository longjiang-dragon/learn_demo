package com.gradle.learn;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class MyPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.gradle.addListener(new TimeListener())
        project.task('MyFirstPlugin') << {
            project.logger.warn "MMMMMMMMMMMMMhello my plugin"
            println "MMMMMMMMMMMMMhello my plugin"
        }
    }
}

