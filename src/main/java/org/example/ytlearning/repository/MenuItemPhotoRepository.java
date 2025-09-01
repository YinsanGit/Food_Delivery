package org.example.ytlearning.repository;

import org.example.ytlearning.model.MenuItemPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MenuItemPhotoRepository extends JpaRepository<MenuItemPhoto,Long> {

    List<MenuItemPhoto> findAllByIdIn(Collection<Long> ids);
}
