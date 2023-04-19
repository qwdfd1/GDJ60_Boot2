package com.onion.base.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onion.base.board.BoardFileVO;
import com.onion.base.board.BoardService;
import com.onion.base.board.BoardVO;
import com.onion.base.util.FileManager;
import com.onion.base.util.Pager;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NoticeService implements BoardService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload.notice}")
	private String path;

	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		return noticeDAO.getDetail(boardVO);
	}

	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		pager.makeStartRow();
		pager.makeNum(noticeDAO.getTotalCount(pager));
		
		
		
		return noticeDAO.getList(pager);
	}

	@Override
	public int setInsert(BoardVO boardVO, MultipartFile [] multipartFiles) throws Exception {
		
		int result= noticeDAO.setInsert(boardVO);
		log.error("NUM =======> {}", boardVO.getNum());
		
		if(multipartFiles != null) {
			for (MultipartFile multipartFile : multipartFiles) {
				if(!multipartFile.isEmpty())  {
					String fileName = fileManager.saveFile(path, multipartFile);
					BoardFileVO boardFileVO = new BoardFileVO();
					
					boardFileVO.setFileName(fileName);
					boardFileVO.setOriName(multipartFile.getOriginalFilename());
					boardFileVO.setNum(boardVO.getNum());
					result = noticeDAO.setFileInsert(boardFileVO);
				}
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
		return noticeDAO.getFileDetail(boardFileVO);
		
	}
	
	
	
	

}
