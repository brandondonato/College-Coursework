package studentCoursesBackup.myTree;

import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.statusEnum.Status;

public interface SubjectI{
	public void register(Node listener);
	public void unregister(Node listener);
	public void notifyAllObservers(String course, Status status);
}