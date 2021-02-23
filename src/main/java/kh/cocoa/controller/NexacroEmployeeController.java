package kh.cocoa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.uiadapter17.spring.core.annotation.ParamDataSet;
import com.nexacro.uiadapter17.spring.core.data.DataSetRowTypeAccessor;
import com.nexacro.uiadapter17.spring.core.data.NexacroResult;
import com.nexacro17.xapi.data.DataSet;

import kh.cocoa.dto.DepartmentsDTO;
import kh.cocoa.dto.EmployeeDTO;
import kh.cocoa.dto.NexacroSearchDTO;
import kh.cocoa.dto.PositionDTO;
import kh.cocoa.dto.TeamDTO;
import kh.cocoa.service.DepartmentsService;
import kh.cocoa.service.EmployeeService;
import kh.cocoa.service.NexacroEmployeeService;
import kh.cocoa.service.PositionService;
import kh.cocoa.service.TeamService;

@Controller
@RequestMapping("/nexEmployee")
public class NexacroEmployeeController {

	@Autowired
	EmployeeService eservice;
	@Autowired
	DepartmentsService ddservice;
	@Autowired
	TeamService tservice;
	@Autowired
	PositionService pservice;
	
	@Autowired
	NexacroEmployeeService neservice;
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	
	//리스트 불로오기 온로드
	@RequestMapping("/nexEmpList.nex")
	public NexacroResult dsEmpList() {
		NexacroResult nr = new NexacroResult();
		//사원 전체 불러오기
		List<EmployeeDTO> emp_list = eservice.getAllEmployeeOrderByCode();
		//부서목록 불러오기(전체 : -10)
		List<DepartmentsDTO> dept_list = ddservice.getDeptListWithout0();
		//팀목록 불러오기(전체 : -10)
		List<TeamDTO> team_list = tservice.getAllTeamList();
		//직위 목록 불러오기
		List<PositionDTO> pos_list = pservice.getAllPosList();
		
		//콤보에 전체 항목 넣기 위해 : 넥사에서 하는방법 찾기
		/*
		PositionDTO pos_all = new PositionDTO().builder().code(-10).name("전체").build();
		pos_list.add(0,pos_all);
		DepartmentsDTO dept_all = new DepartmentsDTO(-10,"전체",0);
		dept_list.add(0, dept_all);
		TeamDTO team_all = new TeamDTO().builder().code(-10).name("전체").build();
		team_list.add(0,team_all);
		*/
		
		nr.addDataSet("out_emp_list",emp_list);
		nr.addDataSet("out_dept_list", dept_list);
		nr.addDataSet("out_team_list", team_list);
		nr.addDataSet("out_pos_list", pos_list);
		return nr;
	}
	
	//검색 
	@RequestMapping("/nexEmpSearch.nex")
	public NexacroResult nexEmpSearch(@ParamDataSet(name="in_ds_search")NexacroSearchDTO dto) {
		int dept_code = dto.getDept_code();
		int team_code = dto.getTeam_code();
		int pos_code = dto.getPos_code();
		String search = dto.getSearch();
		String searchWhat = dto.getSearchWhat();
		System.out.println("nexEmpSearch 도착. 들어온 값 : ");
		System.out.println(dto);
		System.out.println("검색하고나서 ㅣ ");
		List<EmployeeDTO> emp_list = eservice.searchEmployee(dto);
		System.out.println(emp_list);
		
		NexacroResult nr = new NexacroResult();
		nr.addDataSet("out_emp_list", emp_list);
		return nr;
	}
	
	//지금까지 작업한 내용 저장
	@RequestMapping("/nexSave.nex")
	public NexacroResult nexSave(@ParamDataSet(name="in_ds_employee")List<Map<String,Object>> dataList) throws Exception {
		NexacroResult nr = new NexacroResult();
		System.out.println("nexSave.nex 도착");
		int size = dataList.size();
		
		List<EmployeeDTO> addList = new ArrayList<>();	
		
        for (int i=0; i<size; i++) {
            Map<String,Object> emp = dataList.get(i);
            
            int rowType = Integer.parseInt(String.valueOf(emp.get(DataSetRowTypeAccessor.NAME)));
            if (rowType == DataSet.ROW_TYPE_INSERTED){
               System.out.println("추가된 로우 : "+ emp);
               
               System.out.println("랜덤 8자리 : " + neservice.getRandomStr(8));
               pwEncoder = new BCryptPasswordEncoder();
   			   String password = pwEncoder.encode(neservice.getRandomStr(8));
               
               String name = (String) emp.get("name");
               String phone = (String) emp.get("phone");
               String office_phone = (String) emp.get("office_phone");
               String address = (String) emp.get("address");
               String email = (String) emp.get("email");
               String b_email = (String) emp.get("b_email");
               String gender = (String) emp.get("gender");
               String withdraw = (String) emp.get("withdraw");
               
               String s_hire_date = (String) emp.get("hire_date");
               String year = s_hire_date.substring(0, 4);
               String month = s_hire_date.substring(4, 6);
               String day = s_hire_date.substring(6, 8);
               String full_hire_date = year+"-"+month+"-"+day;
               System.out.println("full date : "+full_hire_date);
               java.sql.Date hire_date =java.sql.Date.valueOf(full_hire_date);
               
               int dept_code = (int)emp.get("dept_code");
               int pos_code = (int)emp.get("pos_code");
               int team_code = (int)emp.get("team_code");
   			   
               EmployeeDTO dto = new EmployeeDTO().builder().name(name).password(password).phone(phone).office_phone(office_phone).address(address).email(email).b_email(b_email).gender(gender).hire_date(hire_date).withdraw(withdraw).dept_code(dept_code).pos_code(pos_code).team_code(team_code).build();
               System.out.println(dto);
               //int result = eservice.addOneEmployee(dto);
               //System.out.println("하나추가 : "+result);
               addList.add(dto);
               
            }else if (rowType == DataSet.ROW_TYPE_UPDATED){
            	System.out.println("수정된 로우 : "+ emp);
            }
        }
        
        int result = eservice.addEmployee(addList);
        System.out.println("여러행 추가" + result);
		return nr;
	}

	
    @ExceptionHandler(NullPointerException.class)
    public Object nullex(Exception e) {
        System.err.println(e.getClass());
        return "error";
    }
	
	
}
