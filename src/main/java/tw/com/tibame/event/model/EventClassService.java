package tw.com.tibame.event.model;

public class EventClassService {
	private EventClassDAO_interface dao;
	public EventClassService() {
		dao = new EventClassJDBCDAO();
	}
	
//	public int insert(String[] eventClassNumber) {
//		Integer[] eventClassNumberToINT = new Integer[eventClassNumber.length];
//		for(int i=0 ; i<eventClassNumberToINT.length ; i++) {
//			eventClassNumberToINT[i] = Integer.valueOf(eventClassNumber[i]);
//		}
//		dao.insert(eventClassNumberToINT);
//		return 0;
//	}
}