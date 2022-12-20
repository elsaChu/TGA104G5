package tw.com.tibame.management.model;

public class BulletinService {
	private BulletinDAOInterface dao;
	public BulletinService(){
		dao = new BulletinDAO();
	}
	public BulletinVO addBulletin(BulletinVO bean) {
		dao.insert(bean);
		return bean;
	}
}
