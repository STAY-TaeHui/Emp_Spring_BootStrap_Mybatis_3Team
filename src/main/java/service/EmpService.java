package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import dao.EmpDao;
import vo.Emp;

@Service
public class EmpService {
	
	private SqlSession sqlsession;

	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
//	public String login(String userId, String password) {
//		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
//		
//	}


	public ModelAndView list(String cp, String ps){
		
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);

		if (cp == null || cp.trim().equals("")) {
			cp = "1";
		}
		if (ps == null || ps.trim().equals("")) {
			ps = "5";
		}
		int pagesize = Integer.parseInt(ps);
		int cpage = Integer.parseInt(cp);
		int totalcount = empdao.EmpTotal();
		int pagecount = 0;
		
		
		if (totalcount % pagesize == 0) { 
			pagecount = totalcount / pagesize;
		} else {
			pagecount = (totalcount / pagesize) + 1;
		}
		List<Emp> elist = empdao.list(cpage, pagesize);
		System.out.println(elist);
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("list", elist);
		mv.addObject("pagesize", pagesize);
		mv.addObject("pagecount", pagecount);
		mv.addObject("cpage", cpage);
		mv.setViewName("EmpTable");
		
		return mv;
		
	}
	
	//사원 세부 정보
	public ModelAndView detailList(Long empno, String type) {
		
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		
		Emp emp = empdao.detailList(empno);
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("emp", emp);
		mv.addObject("type", type);
		mv.setViewName("detailView");
		
		return mv;
	}
	
	//사원정보 업데이트 뷰
	public ModelAndView updateEmp(long empno, String type) {
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		
		Emp emp = empdao.detailList(empno);
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("emp", emp);
		mv.addObject("type", type);
		mv.setViewName("updateView");
		
		
		
		return mv;
	}
	
	
	//사원정보 업데이트 뷰 오케이
	public ModelAndView updateOkEmp(Emp emp) {
		
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		
		empdao.updateOkEmp(emp);
		
		ModelAndView mv = new ModelAndView();
		
	
		return mv;
	}
	
	// 삭제해버림 쓸모없음 해고
	public String deleteEmp(long empno) {
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		
		empdao.deleteEmp(empno);
		
		return "redirect:/EmpTable.do";
	}
	
	//사원 등록하기
	public ModelAndView insertEmp(Emp emp) {
		
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		
		empdao.insertEmp(emp);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/EmpTable.do");
	
		return mv;
	}
	
//	
//	public List<Emp> searchEmpno(String empno) ;
//	
//	
//	public List<Emp> searchEname(String ename) ;
//	
//	
//	
//	
//	
//	// 날짜가 yyyymmdd 형식으로 입력되었을 경우 Date로 변경하는 메서드
//	public Date transformDate(String date) ;
//	
//	//사원 삭제하기
//	
//	
//
//	

//	
	

}
