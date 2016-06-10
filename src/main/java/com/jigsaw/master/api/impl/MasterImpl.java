package com.jigsaw.master.api.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.home.beans.Task;
import com.jigsaw.enums.TaskStatusEnum;
import com.jigsaw.master.api.Master;
import com.jigsaw.master.api.Slave;
import com.jigsaw.slave.api.impl.SlaveImpl;

public class MasterImpl implements Master {

	BlockingQueue<Slave> observerList = new LinkedBlockingQueue<Slave>();

	BlockingQueue<Task> bQueue = new LinkedBlockingQueue<Task>();

	private boolean changed;

	public void registerObserver(Slave observer) {
		try {
			this.observerList.add(observer);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void unRegisterObserver(Slave observer) {
		try {
			this.observerList.remove(observer);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private Slave getFreeSlave() {
		Slave freeSlave = null;
		for (Slave slave : this.observerList) {
			if (freeSlave == null) {
				freeSlave = slave;
			} else if (((SlaveImpl) freeSlave).getSlaveTaskQueue().queueSize() > ((SlaveImpl) slave).getSlaveTaskQueue().queueSize()) {
				freeSlave = slave;
			}
		}
		return ((SlaveImpl) freeSlave).getSlaveTaskQueue().queueSize() == 10 ? null : freeSlave;
	}

	public void notifyObservers() {
		try {
			if (changed) {
				for (Task task : bQueue) {
					if (task.getTaskStatusEnum().name().equalsIgnoreCase(TaskStatusEnum.PENDING.name())) {
						Slave observer = getFreeSlave();
						if (observer == null) {
							System.out.println("Slaves capacities are full");
						} else {
							task.setTaskStatusEnum(TaskStatusEnum.PROCESSING);
							task.setObserver(observer);
							if (((SlaveImpl) observer).getSlaveTaskQueue().addToQueue(task)) {
								observer.update();
							}
						}
					}
				}
			}
			changed = false;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void postMessage(Task task) {
		System.out.println("Message Posted to Master:" + task.getTaskName());
		task.setTaskStatusEnum(TaskStatusEnum.PENDING);
		bQueue.add(task);
		this.changed = true;
		notifyObservers();
	}

	public void updateTask(Task task) {
		bQueue.remove(task);
		bQueue.add(task);
	}

}
