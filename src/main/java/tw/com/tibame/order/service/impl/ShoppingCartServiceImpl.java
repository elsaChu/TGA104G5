package tw.com.tibame.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tibame.order.dao.ShoppingCartDAO;
import tw.com.tibame.order.service.ShoppingCartService;
import tw.com.tibame.order.vo.ShoppingCartVO;
import tw.com.tibame.order.vo.ShowShoppingCartVO;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {
	@Autowired
	private ShoppingCartDAO shoppingCartDAO;

	public ShoppingCartServiceImpl(ShoppingCartDAO shoppingCartDAO) {
		super();
		this.shoppingCartDAO = shoppingCartDAO;
	}

	@Override
	public List<ShowShoppingCartVO> getAll() {
		return shoppingCartDAO.getAll();
	}

	@Override
	public ShowShoppingCartVO getByPrimaryKey(Integer shoppingCartNo) {
		if (shoppingCartNo != null) {
			return shoppingCartDAO.getByShoppingCartNo(shoppingCartNo);
		}
		return null;
	}

	@Override
	public List<ShowShoppingCartVO> getByMemberNumber(Integer number) {
		if (number != null) {
			return shoppingCartDAO.getByMemberNumber(number);
		}
		return null;
	}

	@Override
	public ShoppingCartVO insert(ShoppingCartVO shoppingCartVO) {
		if (shoppingCartVO != null) {
			ShoppingCartVO temp = shoppingCartDAO.getByMemberNoAndProdNo(shoppingCartVO.getNumber(), shoppingCartVO.getProdNo());
			if (temp == null) {
				return shoppingCartDAO.insert(shoppingCartVO);
			} else {
				return shoppingCartDAO.update(shoppingCartVO);
			}
		}
		return null;
	}

	@Override
	public ShoppingCartVO update(ShoppingCartVO shoppingCartVO) {
		if (shoppingCartVO != null) {
			ShoppingCartVO temp = shoppingCartDAO.getByMemberNoAndProdNo(shoppingCartVO.getNumber(), shoppingCartVO.getProdNo());
			if (temp != null) {
				return shoppingCartDAO.update(shoppingCartVO);
			}
		}
		return null;
	}

	@Override
	public ShoppingCartVO updateQty(Integer shoppingCartNo, Integer shoppingQty) {
		if (shoppingCartNo != null && shoppingQty != null) {
			ShoppingCartVO temp = shoppingCartDAO.getByPrimaryKey(shoppingCartNo);
			if (temp != null) {
				temp.setShoppingQty(shoppingQty);

				return shoppingCartDAO.update(temp);
			}
		}
		return null;
	}

	@Override
	public boolean delete(Integer shoppingCartNo) {
		if (shoppingCartNo != null) {
			ShoppingCartVO temp = shoppingCartDAO.getByPrimaryKey(shoppingCartNo);
			if (temp != null) {
				return shoppingCartDAO.delete(shoppingCartNo);
			}
		}
		return false;
	}
	
	@Override
	public boolean deleteAllByMemberNumber(Integer number) {
		if (number != null) {
			List<ShoppingCartVO> temp = shoppingCartDAO.findByMemberNumber(number);
			
			for(ShoppingCartVO shoppingCartVO : temp) {
				shoppingCartDAO.delete(shoppingCartVO.getShoppingCartNo());
			}
			return true;
		}
		return false;
	}

}
