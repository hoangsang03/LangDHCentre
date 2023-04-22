package com.langdaihoc.langdhcentre.entity.subEntity;

import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "images")
public class Image {
    protected Image() {};
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false)
    private Long imageId;

    @ManyToOne(cascade = CascadeType.ALL , fetch =  FetchType.LAZY)
    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    private BaseStore store;

    @Column(name = "url_image")
    private String urlImage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(imageId, image.imageId) && Objects.equals(urlImage, image.urlImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, urlImage);
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId=" + imageId +
                ", urlImage='" + urlImage + '\'' +
                '}';
    }
}
