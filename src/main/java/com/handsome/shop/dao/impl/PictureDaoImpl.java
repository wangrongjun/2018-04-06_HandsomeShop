package com.handsome.shop.dao.impl;

import com.handsome.shop.dao.PictureDao;
import com.handsome.shop.entity.Picture;
import com.handsome.shop.framework.HibernateDao;
import org.springframework.stereotype.Repository;

@Repository
public class PictureDaoImpl extends HibernateDao<Picture> implements PictureDao {
}
