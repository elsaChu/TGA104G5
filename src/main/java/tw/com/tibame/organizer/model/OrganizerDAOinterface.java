package tw.com.tibame.organizer.model;

import java.util.List;

public interface OrganizerDAOinterface {
	
    public void insert(OrganizerVO organizerVO);
    public void update(OrganizerVO organizerVO);
    public void update2(OrganizerVO organizerVO);
    public void delete(Integer empno);
    public OrganizerVO findByPrimaryKey(Integer organizerNumber);
    public List<OrganizerVO> selectAll();
    public OrganizerVO findByAccount(String oAccout);
    public List <OrganizerVO> searchOrganizerByAll(String searchString);
	void updateActivateStatus(String account);
	Boolean isActivated(String accountName);
	
}
