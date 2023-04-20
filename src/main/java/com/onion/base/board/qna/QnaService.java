package com.onion.base.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.onion.base.board.BoardFileVO;
import com.onion.base.board.BoardService;
import com.onion.base.board.BoardVO;
import com.onion.base.board.notice.NoticeService;
import com.onion.base.util.FileManager;
import com.onion.base.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class QnaService implements BoardService{
	
	@Autowired
	private QnaDAO qnaDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload.qna}")
	private String path;

	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		
		pager.makeStartRow();
		pager.makeNum(qnaDAO.getTotalCount(pager));
		
		return qnaDAO.getList(pager);
	}

	@Override
	public int setInsert(BoardVO boardVO, MultipartFile[] multipartFiles) throws Exception {
		int result = qnaDAO.setInsert(boardVO);
		result = qnaDAO.setRefInsert(boardVO);
		
		if(result == 0) {
			throw new Exception();
		}
		
		
		
		
		if(multipartFiles == null) {
			return result;
		}
		
		for (MultipartFile multipartFile : multipartFiles) {
			
			
			BoardFileVO boardFileVO = new BoardFileVO();
			boardFileVO.setFileName(fileManager.saveFile(path, multipartFile));
			boardFileVO.setNum(boardVO.getNum());
			boardFileVO.setOriName(multipartFile.getOriginalFilename());
			
			result = qnaDAO.setFileInsert(boardFileVO);
			
			log.info("Result : {}", result);
			
			if(result == 0) {
				throw new Exception();
			}
		}
		
		return result;
		
		
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardFileVO getFileDetail(BoardFileVO boardFileVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
