package org.springframework.samples.mvc.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.samples.mvc.jpa.entity.News;

public interface JNewsDao   extends JpaSpecificationExecutor<News>,JpaRepository<News, Long> {

}
