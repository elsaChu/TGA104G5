package tw.com.tibame.product.dao;

import java.util.List;

import tw.com.tibame.product.vo.ViewProductVO;

public interface ViewProductDAO {
	List<ViewProductVO> findAll();

	public List<ViewProductVO> findByEventType(ViewProductVO vo);

	public List<ViewProductVO> findProductLaunch(boolean isPOn);

	public ViewProductVO findByPrimaryKey(Integer prodNo);
}
