package studentCoursesBackup.myTree;

import studentCoursesBackup.statusEnum.Status;

public interface ObserverI {
	public void update(String course, Status status);
}