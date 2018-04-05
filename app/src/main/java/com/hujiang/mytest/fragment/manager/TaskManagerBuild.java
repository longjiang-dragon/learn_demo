package com.hujiang.mytest.fragment.manager;

import android.app.Application;
import android.text.TextUtils;
import android.widget.Toast;

import com.hujiang.mytest.fragment.manager.task.TaskInfo;

import java.util.List;

/**
 * @author jianglong
 * @desc 用于task依赖关系构建
 * @date 2018/4/2
 */

public class TaskManagerBuild {
    private TaskInfo mRootTaskInfo;
    private Application mApplication;

    public TaskManagerBuild(Application application) {
        this.mApplication = application;
    }


    /**
     * 添加一个任务，默认放在根节点下,如根节点不存，就以当前节点为根节点
     */
    public TaskManagerBuild addNode(LibInitiation libInitiation) {
        return addNodeToParent(libInitiation);
    }

    /**
     * 添加一个任务，可能存在下面两类情况：
     * 1、未找到节点ParentNodeClassName，刚添加到根节点下
     * 2、找到节点ParentNodeClassName，放在目标节点下
     */
    public TaskManagerBuild addNodeToParent(LibInitiation libInitiation, String... parentNodeClassName) {
        if (isAddedNode(libInitiation)) {
            Toast.makeText(mApplication, "添加了两个相同的task=" + libInitiation.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
            return this;
        }
        TaskInfo tempTaskInfo = generateTaskInfo(libInitiation);
        if (isAddRootNode()) {
            //不存在root节点的情况
            this.mRootTaskInfo = tempTaskInfo;
        } else {
            addNode(tempTaskInfo, parentNodeClassName);
        }
        return this;
    }

    private void addNode(TaskInfo childNode, String... parentNodeClassNameList) {
        addNodeToRoot(childNode);//所以子接点都是root的子接点
        TaskInfo parentTaskInfo;
        for (String parentNodeClassName : parentNodeClassNameList) {
            parentTaskInfo = findNodeByClassName(parentNodeClassName);
            childAndParentLink(childNode, parentTaskInfo);
        }
    }

    private void addNodeToRoot(TaskInfo childNode) {
        childAndParentLink(childNode, mRootTaskInfo);
    }

    //将两个node  建立关联
    private void childAndParentLink(TaskInfo childTask, TaskInfo parentTask) {
        if (null == parentTask) return;
        parentTask.getChildTaskList().add(childTask);
        childTask.getParentTaskList().add(parentTask);
    }

    //已添加此节点
    private boolean isAddedNode(LibInitiation libInitiation) {
        TaskInfo temp = findNodeByClassName(libInitiation.getClass().getSimpleName());
        if (null == temp) return false;
        return true;
    }

    private TaskInfo findNodeByClassName(String parentNodeClassName) {
        if (TextUtils.isEmpty(parentNodeClassName)) return null;
        if (null == mRootTaskInfo) return null;
        if (mRootTaskInfo.equals(parentNodeClassName)) return mRootTaskInfo;//从根节点开始查找
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
//        if (libInitiation.isRunMainThread()) {
        return new TaskInfo(libInitiation, mApplication);
//        } else {
//            return new AsyncMainThreadTaskInfo(libInitiation, mApplication);
//        }
    }

    private boolean isAddRootNode() {
        return null == mRootTaskInfo;
    }

    public void startInit() {
        new TaskManager().init(mRootTaskInfo);
    }


}
