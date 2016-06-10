
import com.home.beans.Task;
import com.jigsaw.master.api.Master;
import com.jigsaw.master.api.Slave;
import com.jigsaw.master.api.impl.MasterImpl;
import com.jigsaw.slave.api.impl.SlaveImpl;

public class MainClass {

	public static void main(String[] args) {
		Master master1 = new MasterImpl();

		Slave slave1 = new SlaveImpl("slave1");
		Slave slave2 = new SlaveImpl("slave2");
		Slave slave3 = new SlaveImpl("slave3");

		slave1.subcribeSubject(master1);
		slave2.subcribeSubject(master1);
		slave3.subcribeSubject(master1);

		Task t1 = new Task("Hello boss... this is task1", null);
		Task t2 = new Task("Hello boss... this is task2", null);
		Task t3 = new Task("Hello boss... this is task3", null);

		master1.postMessage(t1);
		master1.postMessage(t2);
		master1.postMessage(t3);

	}
}
