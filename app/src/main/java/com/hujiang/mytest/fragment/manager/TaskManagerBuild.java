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
    public TaskManagerBuild addNodeToParent(LibInitiation libInitiation, String... parentNodeClassNames) {
        if (!judgeNodeCanAdd(libInitiation, parentNodeClassNames)) return this;
        TaskInfo tempTaskInfo = generateTaskInfo(libInitiation);
        if (isAddRootNode()) {
            //不存在root节点的情况
            this.mRootTaskInfo = tempTaskInfo;
        } else {
            addNode(tempTaskInfo, parentNodeClassNames);
        }
        return this;
    }

    private void addNode(TaskInfo childNode, String... parentNodeClassNames) {
        addNodeToRoot(childNode);//所以子接点都是root的子接点
        if (null == parentNodeClassNames) return;
        TaskInfo parentTaskInfo;
        for (String parentNodeClassName : parentNodeClassNames) {
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

    //判断此节点是否能被添加
    private boolean judgeNodeCanAdd(LibInitiation libInitiation, String... parentNodeClassNameList) {
        if (isAddedNode(libInitiation)) {
            Toast.makeText(mApplication, "添加了两个相同的task=" + libInitiation.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (null == parentNodeClassNameList || parentNodeClassNameList.length == 0) return true;
        for (String parentNodeClassName : parentNodeClassNameList) {
            //这里判断child和parent不能是同一个
            if (parentNodeClassName.equals(libInitiation.getClass().getSimpleName())) {
                Toast.makeText(mApplication, "父节点指向错误=" + libInitiation.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
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
        return new TaskInfo(libInitiation, mApplication);
    }

    private boolean isAddRootNode() {
        return null == mRootTaskInfo;
    }

    public void startInit() {
        new TaskManager().init(mRootTaskInfo);
    }


}
