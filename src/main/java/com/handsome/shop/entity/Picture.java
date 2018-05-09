package com.handsome.shop.entity;

import com.handsome.shop.framework.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Where(clause = BaseEntity.OBSOLETE_DATE_IS_NULL)
public class Picture extends BaseEntity {

    public enum PictureType {
        jpg,
        png,
        bmp,
        gif,
        ico,
    }

    @Id
    @GeneratedValue
    private Integer pictureId;
    @Enumerated(EnumType.STRING)
    private PictureType pictureType;
    private Blob picture;

    public Picture() {
    }

    public Picture(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public Picture(PictureType pictureType, Blob picture) {
        this.pictureType = pictureType;
        this.picture = picture;
    }
}
