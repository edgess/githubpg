package com.example.demo5;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class OilService {
	@Autowired
	private OilRepository oilRepository;

	public List<Oil> findAll(){
		return oilRepository.findAll();
	}

//	@Transactional
	public void deleteById(Integer id) {
		oilRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Oil getOneById(Integer id) {
		return oilRepository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public Oil getLastOne() {
		return oilRepository.getall().get(0);
	}

	@Transactional
	public void saveNew(Oil oil) {
		oil.setDate(new Date());
		oilRepository.saveAndFlush(oil);
	}

	@Transactional
	public void edit(Oil oil) {
		oilRepository.saveAndFlush(oil);
	}

	@Transactional(readOnly = true)
	public Page<Oil> getPage(int pageNo, int pageSize) {
		Sort sort = new Sort(Direction.DESC, "id");
		PageRequest pageable = new PageRequest(pageNo - 1, pageSize, sort);
		return oilRepository.findAll(pageable);
	}
}
