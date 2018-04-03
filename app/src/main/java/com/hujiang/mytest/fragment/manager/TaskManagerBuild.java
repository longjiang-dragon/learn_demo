package com.hujiang.mytest.fragment.manager;

import android.app.Application;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.List;

/**
 * @author jianglong
 * @desc
 * @date 2018/4/2
 */

//1、默认添加到root节点
//2、通过ap
public class TaskManagerBuild {
    private TaskInfo mRootTaskInfo;
    private Application mApplication;

    public TaskManagerBuild(Application application) {
        this.mApplication = application;
    }


    public TaskManagerBuild addNode(LibInitiation libInitiation) {
        return addNode(libInitiation, null);
    }

    public TaskManagerBuild addNode(LibInitiation libInitiation, String ParentNodeClassName) {
        if (isAddedNode(libInitiation)) {
            Toast.makeText(mApplication, "添加了两个相同的task", Toast.LENGTH_SHORT).show();
            return this;
        }
        TaskInfo tempTaskInfo = generateTaskInfo(libInitiation);
        TaskInfo parentTaskInfo = findNodeByClassName(ParentNodeClassName);
        if (isAddRootNode()) {
            //不存在root节点的情况
            this.mRootTaskInfo = tempTaskInfo;
        } else {
            addNode(tempTaskInfo, parentTaskInfo);
        }
        return this;
    }

    //已添加此节点
    private boolean isAddedNode(LibInitiation libInitiation) {
        TaskInfo temp = findNodeByClassName(libInitiation.getClass().getSimpleName());
        if (null == temp) return false;
        return true;
    }


    private void addNode(TaskInfo newTaskInfo, TaskInfo parentTaskInfo) {
        if (null == parentTaskInfo) {
            mRootTaskInfo.addToChildTaskList(newTaskInfo);
        } else {
            parentTaskInfo.addToChildTaskList(newTaskInfo);
        }
    }

    private TaskInfo findNodeByClassName(String parentNodeClassName) {
        if (TextUtils.isEmpty(parentNodeClassName)) return null;
        if (null == mRootTaskInfo) return null;
        if (mRootTaskInfo.equals(parentNodeClassName)) return mRootTaskInfo;
        return findNodeByClassName(mRootTaskInfo.getChildTaskList(), parentNodeClassName);
    }

    private TaskInfo findNodeByClassName(List<TaskInfo> childTaskInfoList, String descClassname) {
        TaskInfo tempTaskInfo;
        for (TaskInfo taskInfo : childTaskInfoList) {
            if (taskInfo.equals(descClassname)) {
                return taskInfo;
            } else {
                tempTaskInfo = findNodeByClassName(taskInfo.getChildTaskList(), descClassname);
                if (null != tempTaskInfo) return tempTaskInfo;
            }

        }
        return null;
    }

    private TaskInfo generateTaskInfo(LibInitiation libInitiation) {
        return new TaskInfo(libInitiation, mApplication);
    }

    private boolean isAddRootNode() {
        return null == mRootTaskInfo;
    }

    public void startInit() {
        new TaskManager().init(mRootTaskInfo);
    }


}
