package com.jigsaw.slave.api.impl;

import com.home.beans.Task;
import com.jigsaw.enums.TaskStatusEnum;
import com.jigsaw.master.api.Master;
import com.jigsaw.master.api.Slave;

public class SlaveImpl implements Slave {

	private Master master;
	private String observerName;
	private boolean isCapacityFull;
	private SlaveTaskQueue slaveTaskQueue;

	public SlaveTaskQueue getSlaveTaskQueue() {
		if (slaveTaskQueue == null) {
			slaveTaskQueue = new SlaveTaskQueue();
		}
		return slaveTaskQueue;
	}

	public void setSlaveTaskQueue(SlaveTaskQueue slaveTaskQueue) {
		this.slaveTaskQueue = slaveTaskQueue;
	}

	public boolean isCapacityFull() {
		return isCapacityFull;
	}

	public void setCapacityFull(boolean isCapacityFull) {
		this.isCapacityFull = isCapacityFull;
	}

	public SlaveImpl(String name) {
		this.observerName = name;
	}

	public void subcribeSubject(Master subject) {
		this.master = subject;
		this.master.registerObserver(this);
	}

	public void update() {
		System.out.println("Item added in Queue of : "+observerName);
		for (Task task : slaveTaskQueue.getbQueue()) {
			if(task.getTaskStatusEnum().name().equalsIgnoreCase(TaskStatusEnum.PROCESSING.name())){
				doTask(task);
			}
		}
	}
	
	private void doTask(Task task){
		System.out.println("Task performed : "+task.getTaskName());
		task.setTaskStatusEnum(TaskStatusEnum.FINISHED);
	}

}
