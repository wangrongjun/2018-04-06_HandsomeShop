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
    private Blob pictureData;

    @Override
    public String toString() {
        return "Picture{" +
                "pictureId=" + pictureId +
                ", pictureType=" + pictureType +
                '}';
    }

    public Picture() {
    }

    public Picture(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public Picture(PictureType pictureType, Blob pictureData) {
        this.pictureType = pictureType;
        this.pictureData = pictureData;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public PictureType getPictureType() {
        return pictureType;
    }

    public void setPictureType(PictureType pictureType) {
        this.pictureType = pictureType;
    }

    public Blob getPictureData() {
        return pictureData;
    }

    public void setPictureData(Blob pictureData) {
        this.pictureData = pictureData;
    }
}
