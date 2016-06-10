package com.jigsaw.slave.api.impl;

import java.io.Serializable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.home.beans.Task;
import com.jigsaw.enums.TaskStatusEnum;

public class SlaveTaskQueue implements Serializable {

	private static final long serialVersionUID = 7284714373689502172L;

	BlockingQueue<Task> bQueue = new LinkedBlockingQueue<Task>();

	public boolean addToQueue(Task task) {
		if (queueSize() < 10) {
			bQueue.add(task);
			return true;
		}
		return false;
	}

	public int queueSize() {
		int count = 0;
		for (Task task : bQueue) {
			if (!task.getTaskStatusEnum().name().equalsIgnoreCase(TaskStatusEnum.FINISHED.name())) {
				count++;
			}
		}
		return count;
	}

	protected BlockingQueue<Task> getbQueue() {
		return bQueue;
	}

}
