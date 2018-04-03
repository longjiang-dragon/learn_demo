package com.hujiang.mytest.fragment.manager;

import android.app.Application;
import android.text.TextUtils;

import java.util.List;

/**
 * @author jianglong
 * @desc
 * @date 2018/4/2
 */

//1、默认添加到root节点
//2、通过ap
public class TaskManagerBuild {
    private TaskInfo<LibInitiation> mRootTaskInfo;
    private Application mApplication;

    public TaskManagerBuild(Application application) {
        this.mApplication = application;
    }


    public TaskManagerBuild addNode(LibInitiation libInitiation) {
        return addNode(libInitiation, null);
    }

    public TaskManagerBuild addNode(LibInitiation libInitiation, String ParentNodeClassName) {
        TaskInfo<LibInitiation> tempTaskInfo = generateTaskInfo(libInitiation);
        TaskInfo<LibInitiation> parentTaskInfo = findParentNodeByClassName(ParentNodeClassName);
        if (isAddRootNode()) {
            //不存在root节点的情况
            this.mRootTaskInfo = tempTaskInfo;
        } else {
            addNode(tempTaskInfo, parentTaskInfo);
        }
        return this;
    }


    private void addNode(TaskInfo<LibInitiation> newTaskInfo, TaskInfo<LibInitiation> desTaskInfo) {
        if (null == desTaskInfo) {
            mRootTaskInfo.addToChildTaskList(newTaskInfo);
        } else {
            desTaskInfo.addToChildTaskList(newTaskInfo);
        }
    }

    private TaskInfo<LibInitiation> findParentNodeByClassName(String parentNodeClassName) {
        if (TextUtils.isEmpty(parentNodeClassName)) return null;
        if (null == mRootTaskInfo) return null;
        return findParentNodeByClassName(mRootTaskInfo.getChildTaskList(), parentNodeClassName);
    }

    private TaskInfo<LibInitiation> findParentNodeByClassName(List<TaskInfo<LibInitiation>> childTaskInfoList, String descClassname) {
        TaskInfo<LibInitiation> tempTaskInfo;
        for (TaskInfo<LibInitiation> taskInfo : childTaskInfoList) {
            if (taskInfo.equals(descClassname)) {
                return taskInfo;
            } else {
                tempTaskInfo = findParentNodeByClassName(taskInfo.getChildTaskList(), descClassname);
                if (null != tempTaskInfo) return tempTaskInfo;
            }

        }
        return null;
    }

    private TaskInfo<LibInitiation> generateTaskInfo(LibInitiation libInitiation) {
        return new TaskInfo<>(libInitiation, mApplication);
    }

    private boolean isAddRootNode() {
        return null == mRootTaskInfo;
    }

    public void startInit() {
        new TaskManager().init(mRootTaskInfo);
    }


}
