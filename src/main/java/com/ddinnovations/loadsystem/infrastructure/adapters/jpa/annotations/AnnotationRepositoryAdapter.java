package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.annotations;

import com.ddinnovations.loadsystem.domain.entity.Annotations;
import com.ddinnovations.loadsystem.domain.entity.Clients;
import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.domain.entity.dto.Id;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsAnnotation;
import com.ddinnovations.loadsystem.domain.entity.response.Pagination;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import com.ddinnovations.loadsystem.domain.repository.AnnotationRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.annotations.mapper.AnnotationMapper;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.filters.AnnotationSpecification;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnnotationRepositoryAdapter extends AdapterOperations<Annotations, AnnotationEntity, String, AnnotationDtoRepository> implements AnnotationRepository {
    protected AnnotationRepositoryAdapter(AnnotationDtoRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Annotations.AnnotationsBuilder.class).build());
    }


    @Override
    public ResponseGlobalPagination<List<Annotations>> findAllAnnotations(String id, ParamsAnnotation params) {
        AnnotationSpecification specification = new AnnotationSpecification(id, params.getFilterCriteriaText(), params.getOrderBy(), params.getStartDate(), params.getEndDate());
        PageRequest pages = PageRequest.of(params.getPage(), params.getLimit(), Sort.by(Sort.Order.desc("createdAt")));
        List<Annotations> annotations = repository.findAll(specification, pages)
                .stream()
                .map(AnnotationMapper::annotationAAnnotationDto)
                .toList();
        return new ResponseGlobalPagination<>(annotations, new Pagination(params.getPage(), params.getLimit(), ((int) repository.count())));

    }

    @Override
    public ResponseGlobal<Annotations> findByIdAnnotation(String id) {
        return new ResponseGlobal<>(AnnotationMapper.annotationAAnnotationDto(getAnnotationById(id)));
    }

    @Override
    public ResponseGlobal<Annotations> createAnnotation(Annotations annotations) {
        AnnotationEntity annotationEntity = AnnotationMapper.annotationDtoAAnnotation(annotations);
        return new ResponseGlobal<>(AnnotationMapper.annotationAAnnotationDto(repository.save(annotationEntity)));
    }

    @Override
    public ResponseGlobal<Annotations> update(String id, Annotations annotations) {
        AnnotationEntity annotationEntity = getAnnotationById(id);
        annotationEntity.setTitle(annotations.getTitle());
        annotationEntity.setDescription(annotations.getDescription());
        return new ResponseGlobal<>(AnnotationMapper.annotationAAnnotationDto(repository.save(annotationEntity)));
    }

    @Override
    public ResponseGlobal<Id> delete(String id) {
        AnnotationEntity annotationEntity = getAnnotationById(id);
        repository.deleteById(annotationEntity.getId());
        return new ResponseGlobal<>(new Id(annotationEntity.getId()));
    }

    private AnnotationEntity getAnnotationById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.ANNOTATION_NOT_FOUND));
    }
}
