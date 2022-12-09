package tw.com.tibame.staff.model;

import java.util.*;

public interface permissionDAO_interface {

	public permissionVO findByPrimaryKey(Integer permissionNumber);
	public List<permissionVO> getAll();
}
