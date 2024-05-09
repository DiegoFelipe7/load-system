package com.ddinnovations.loadsystem.domain.repository;

import com.ddinnovations.loadsystem.domain.entity.Annotations;
import com.ddinnovations.loadsystem.domain.entity.dto.Id;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsAnnotation;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;

import java.util.List;

public interface AnnotationRepository {
    ResponseGlobalPagination<List<Annotations>> findAllAnnotations(String id,ParamsAnnotation params);
    ResponseGlobal<Annotations> findByIdAnnotation(String id);
    ResponseGlobal<Annotations> createAnnotation(Annotations annotations);
    ResponseGlobal<Annotations> update(String id, Annotations annotations);
    ResponseGlobal<Id> delete(String id);
}
