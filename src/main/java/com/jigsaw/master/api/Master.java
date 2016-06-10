package com.jigsaw.master.api;

import com.home.beans.Task;

public interface Master {

	public void registerObserver(Slave observer);

	public void unRegisterObserver(Slave observer);

	public void notifyObservers();

	public void postMessage(Task msg);
	
	public void updateTask(Task task);
}
