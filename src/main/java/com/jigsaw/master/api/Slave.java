package com.jigsaw.master.api;

public interface Slave {

	public void subcribeSubject(Master subject);

	public void update();
}
