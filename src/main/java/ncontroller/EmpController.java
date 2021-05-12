package ncontroller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.EmpService;
import vo.Emp;

@Controller
public class EmpController {
	
	EmpService empservice;
	
	@Autowired
	public void setEmpservice(EmpService empservice) {
		this.empservice = empservice;
	}
	
	
	@RequestMapping("Main.do")
	public String index() {
		System.out.println("THIS IS INDDEX");
		return "EmpTable.do";   //  /WEB-INF/views/index.jsp

	}
//	@RequestMapping("login.do")
//	public String login(String id, String password) {
//
//	}


	@RequestMapping("EmpTable.do")
	public ModelAndView list(String cp, String ps) {
		
		ModelAndView mv = empservice.list(cp, ps);
		
		if(mv == null) {
			return null; 
		}
		
		return mv;
	}
	
	@RequestMapping("detailView.do")
	public ModelAndView detailList(Long empno, String type) {
		
		ModelAndView mv = empservice.detailList(empno, type);
		
		
		if(mv == null) {
			return null; 
		}
		
		return mv;

	}
	
	
	@RequestMapping(value = "update.do", method=RequestMethod.GET)
	public ModelAndView updateEmp(Long empno, String type) {
		
		ModelAndView mv = empservice.updateEmp(empno,type);
		
			
		if(mv == null) {
			return null; 
		}
		
		return mv;
	}
	
	@RequestMapping(value = "updateOK.do", method=RequestMethod.POST)
	public String updateEmp2(String empno, String ename,String job,String mgr,String hiredate,
			String sal,String comm, String deptno,String filename) {
		
		
		Emp emp = new Emp();
		emp.setEmpno(Long.parseLong(empno));
		emp.setEname(ename);
		emp.setJob(job);
		emp.setMgr(Long.parseLong(mgr));
		emp.setHiredate(new Date());
		emp.setSal(Long.parseLong(sal));
		emp.setComm(Long.parseLong(comm));
		emp.setDeptno(Long.parseLong(deptno));
		emp.setFilename(filename);
		
		System.out.println(emp.toString());
		
		
		ModelAndView mv = empservice.updateOkEmp(emp);
		
			
		if(mv == null) {
			return null; 
		}
		
		return "redirect:/detailView.do?empno=" + empno;
		
	}
	
	@RequestMapping("delete.do")
	public String deleteEmp(String empno) {
		
		String url = empservice.deleteEmp(Long.parseLong(empno));
		

		
		return url;
		
	}
	
	
	@RequestMapping(value = "EmpWrite.do", method=RequestMethod.GET)
	public String insertEmp() {
		
		return "EmpWrite";
	}
	
	@RequestMapping(value = "EmpWrite.do", method=RequestMethod.POST)
	public String insertEmp(String empno, String ename,String job,String mgr,String hiredate,
			String sal,String comm, String deptno,String filename) {
		
		Emp emp = new Emp();
		emp.setEmpno(Long.parseLong(empno));
		emp.setEname(ename);
		emp.setJob(job);
		emp.setMgr(Long.parseLong(mgr));
		emp.setHiredate(new Date());
		emp.setSal(Long.parseLong(sal));
		emp.setComm(Long.parseLong(comm));
		emp.setDeptno(Long.parseLong(deptno));
		emp.setFilename(filename);
		
		System.out.println(emp.toString());
		
		
		ModelAndView mv = empservice.insertEmp(emp);
		
			
		if(mv == null) {
			return null; 
		}
		
		return mv.getViewName();
		
	}
	

}
