package ransomdetection;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import bean.Event;
import dao.Dao;

import com.opensymphony.xwork2.ActionSupport;

public class RansomReport extends ActionSupport {

	
	private static final long serialVersionUID = 1L;
	ResultSet rs = null;
	Event evt  = null;
	List<Event> evtList = null;
	Dao dao = new Dao();
	private boolean noData = false;

	@Override
	public String execute() throws Exception {
		try {
		    evtList = new ArrayList<Event>();
			rs = dao.getReport();
			int i = 0;
			if (rs != null) {
				while (rs.next()) {
					i++;
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDateTime dateTime = LocalDateTime.parse(rs.getString(1), formatter);
					evt=new Event(dateTime,rs.getString(2),rs.getString(3),rs.getString(4));
					evtList.add(evt);
				}
			}
			if (i == 0) {
				noData = false;
			} else {
				noData = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "report";
	}

	public boolean isNoData() {
		return noData;
	}

	public void setNoData(boolean noData) {
		this.noData = noData;
	}

	public List<Event> getEvtList() {
		return evtList;
	}

	
}