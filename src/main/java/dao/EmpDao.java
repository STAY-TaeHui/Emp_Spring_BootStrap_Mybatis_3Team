package dao;

import java.util.List;
import java.sql.Date;

import vo.Emp;

public interface EmpDao {


	public String login(String userId);


	public List<Emp> list(int cpage, int pagesize) ;
	
	//사원 세부 정보
	public Emp detailList(Long empno) ;
	
	public List<Emp> searchEmpno(String empno) ;
	
	public int EmpTotal();
	
	public List<Emp> searchEname(String ename) ;
	
	
	//사원 등록하기
	public int insertEmp(Emp emp) ;
	
	// 날짜가 yyyymmdd 형식으로 입력되었을 경우 Date로 변경하는 메서드
	public Date transformDate(String date) ;
	
	//사원 삭제하기
	public int deleteEmp(long empno) ;
	

	
	//사원정보 업데이트
	public int updateOkEmp(Emp emp) ;
	
	
	
	
}
