package kh.cocoa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.cocoa.dao.BusinessLogDAO;
import kh.cocoa.dto.BoardDTO;
import kh.cocoa.dto.DocumentDTO;

@Service
public class BusinessLogService implements BusinessLogDAO {
	
	@Autowired
	private BusinessLogDAO bdao;
	
	//업무일지 종류에 따라 문서 저장
	@Override
	public int createLog(int logDoc_seq,DocumentDTO ddto,String selectBy) {
		return bdao.createLog(logDoc_seq,ddto,selectBy);
	}
	//업무일지 seq & files doc_seq 맞추기
	@Override
	public int logDocSelectSeq() {
		return bdao.logDocSelectSeq();
	}
	//임시 문서 저장
	@Override
	public int tempSavedLog(int logDoc_seq, DocumentDTO ddto, String selectBy) {
		System.out.println("서비스 selectBy : "+selectBy);
		return bdao.tempSavedLog(logDoc_seq,ddto,selectBy);
	}
	/*----------------임시저장 보관함-------------------------*/
	
	//글 전체 리스트
	@Override
	public List<BoardDTO> getLogAllList(String status,int writer_code) {
		return bdao.getLogAllList(status,writer_code);
	}
	//일일 리스트
	public List<BoardDTO> dailyList(String status,int writer_code) {
		return bdao.dailyList(status,writer_code);
	}
	// 주간 리스트
	public List<BoardDTO> weeklyList(String status,int writer_code) {
		return bdao.weeklyList(status,writer_code);
	}
	//월별 리스트
	public List<BoardDTO> monthlyList(String status,int writer_code) {
		return bdao.monthlyList(status,writer_code);
	}
	

	/*----------------확인 요청  보관함-------------------------*/
	
	public List<BoardDTO> logAllListR(String status, int pos_code) {
		return bdao.logAllListR(status,pos_code);
	}

	public List<BoardDTO> dailyListR(String status, int pos_code) {
		return bdao.dailyListR(status,pos_code);
	}
	public List<BoardDTO> weeklyListR(String status, int pos_code) {
		return bdao.weeklyListR(status,pos_code);
	}
	public List<BoardDTO> monthlyListR(String status, int pos_code) {
		return bdao.monthlyListR(status,pos_code);
	}
	/*----------------업무일지 보관함-------------------------*/
	public List<BoardDTO> logAllListC(String status, int dept_code) {
		return bdao.logAllListC(status,dept_code);
	}
	public List<BoardDTO> dailyListC(String status, int dept_code) {
		return bdao.dailyListC(status,dept_code);
	}
	public List<BoardDTO> weeklyListC(String status, int dept_code) {
		return bdao.weeklyListC(status,dept_code);
	}
	public List<BoardDTO> monthlyListC(String status, int dept_code) {
		return bdao.monthlyListC(status,dept_code);
	}
	/*----------보낸업무 일지함 ------*/
	//전체
	public List<BoardDTO> sentLogAllList(int writer_code) {
		return bdao.sentLogAllList(writer_code);
	}
	//일일
	public List<BoardDTO> sentLogDailyList(int writer_code) {
		return bdao.sentLogDailyList(writer_code);
	}
	//주간
	public List<BoardDTO> sentLogWeeklyList(int writer_code) {
		return bdao.sentLogWeeklyList(writer_code);
	}
	//월간
	public List<BoardDTO> sentLogMonthlyList(int writer_code) {
		return bdao.sentLogMonthlyList(writer_code);
	}
	/*---------------업무일지 읽기--------------*/
	//글 가져오기
	public DocumentDTO getLogBySeq(int seq) {
		return bdao.getLogBySeq(seq);
	}
	//수정버튼 - 작성자인 경우만 보임
	public int checkWriter(int seq, int writer_code) {
		return bdao.checkWriter(seq,writer_code);
	}
	/*--------------업무일지 수정 시 리스트 불러오기*/
	public DocumentDTO getLogBySeqMod(int seq,DocumentDTO dto) {
		return bdao.getLogBySeqMod(seq,dto);
	}
	/*--------임시보관된 문서 삭제--------*/
	public int logDel(int seq) {
		return bdao.logDel(seq);
	}
	/*확인요청 문서 거절*/
	public int updateStatusReject(int seq, String status) {
		return bdao.updateStatusReject(seq,status);
	}
	/*확인요청 문서 승인*/
	public int updateStatusConfirm(int seq, String report_contents) {
		System.out.println("서비스에서 코멘트?"+report_contents);
		return bdao.updateStatusConfirm(seq, report_contents);
	}
	//수정 페이지 - 임시저장 (일일)
	public int logModifyTempUpdateDaily(DocumentDTO ddto) {
		return bdao.logModifyTempUpdateDaily(ddto);
	}
	
	//수정 페이지 - 임시저장 
	public int logModifyTempUpdate(DocumentDTO ddto) {
		return bdao.logModifyTempUpdate(ddto);
	}
	//수정 페이지 - 수정 후 상신 (일일)
	public int logModifyDaily(DocumentDTO ddto) {
		return bdao.logModifyDaily(ddto);
	}
	//수정 페이지 - 수정 후 상신 (주간 & 월별)
	public int logModify(DocumentDTO ddto) {
		return bdao.logModify(ddto);
	}

	//업데이트 된 문서 seq 와 doc_confirm의 docseq 맞추기
	public int doc_seq() {
		return bdao. doc_seq();
	}
	//승인의 경우 doc_confirm 테이블에 업뎃
	public int docConf(int doc_seq, DocumentDTO ddto) {
		return bdao.docConf(doc_seq,ddto);
	}
	
}
