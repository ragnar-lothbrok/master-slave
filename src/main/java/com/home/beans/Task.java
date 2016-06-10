package com.home.beans;

import java.io.Serializable;

import com.jigsaw.enums.TaskStatusEnum;
import com.jigsaw.master.api.Slave;

public class Task implements Serializable {

	private static final long serialVersionUID = 2845223973388659012L;

	private String taskName;
	private TaskStatusEnum taskStatusEnum;
	private Slave slave;

	public Task() {

	}

	public Task(String taskName, Slave slave) {
		super();
		this.taskName = taskName;
		this.slave = slave;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public TaskStatusEnum getTaskStatusEnum() {
		return taskStatusEnum;
	}

	public void setTaskStatusEnum(TaskStatusEnum taskStatusEnum) {
		this.taskStatusEnum = taskStatusEnum;
	}

	public Slave getObserver() {
		return slave;
	}

	public void setObserver(Slave observer) {
		this.slave = observer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taskName == null) ? 0 : taskName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (taskName == null) {
			if (other.taskName != null)
				return false;
		} else if (!taskName.equals(other.taskName))
			return false;
		return true;
	}

}
