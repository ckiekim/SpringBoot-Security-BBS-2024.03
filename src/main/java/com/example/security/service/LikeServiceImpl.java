package com.example.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.dao.LikeDao;
import com.example.security.entity.Like;

@Service
public class LikeServiceImpl implements LikeService {
	@Autowired private LikeDao likeDao;
	
	@Override
	public Like getLike(int bid, int suid) {
		return likeDao.getLike(bid, suid);
	}

	@Override
	public Like getLikeByLid(int lid) {
		return likeDao.getLikeByLid(lid);
	}

	@Override
	public List<Like> getLikeList(int bid) {
		return likeDao.getLikeList(bid);
	}

	@Override
	public void insertLike(Like like) {
		likeDao.insertLike(like);
	}

	@Override
	public int toggleLike(Like like) {
		like = likeDao.getLike(like.getBid(), like.getSuid());
		int value = like.getValue() == 0 ? 1 : 0;
		like.setValue(value);
		likeDao.updateLike(like);
		return value;
	}

	@Override
	public int getLikeCount(int bid) {
		List<Like> list = likeDao.getLikeList(bid);
		int count = 0;
		for (Like like: list)
			count += like.getValue();
		return count;
	}

}
