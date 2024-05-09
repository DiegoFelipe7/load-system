package com.ddinnovations.loadsystem.infrastructure.points.controller;

import com.ddinnovations.loadsystem.application.service.AnnotationService;
import com.ddinnovations.loadsystem.domain.entity.Annotations;
import com.ddinnovations.loadsystem.domain.entity.Clients;
import com.ddinnovations.loadsystem.domain.entity.dto.CustomerIndicatorDto;
import com.ddinnovations.loadsystem.domain.entity.dto.Id;
import com.ddinnovations.loadsystem.domain.entity.enums.OrderBy;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsAnnotation;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsClients;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/annotation")
@RequiredArgsConstructor
public class AnnotationController {
    private final AnnotationService annotationService;

    @PostMapping()
    public ResponseGlobal<Annotations> createAnnotation(@RequestBody Annotations annotation) {
        return annotationService.createAnnotation(annotation);
    }

    @GetMapping(path = "/{id}")
    public ResponseGlobal<Annotations> findByIdAnnotation(@PathVariable("id") String id) {
        return annotationService.findByIdAnnotation(id);
    }

    @GetMapping(path = "/loan/{id}")
    public ResponseGlobalPagination<List<Annotations>> findAllAnnotations(
            @PathVariable("id") String id,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "limit", defaultValue = "10", required = false) int limit,
            @RequestParam(value = "filterCriteriaText", defaultValue = "", required = false) String filterCriteriaText,
            @RequestParam(value = "orderBy", defaultValue = "", required = false) OrderBy orderBy,
            @RequestParam(value = "startDate", defaultValue = "", required = false) String startDate,
            @RequestParam(value = "endDate", defaultValue = "", required = false) String endDate) {
        return annotationService.findAllAnnotations(id, new ParamsAnnotation(page, limit, filterCriteriaText, orderBy, startDate,endDate));
    }

    @PutMapping(path = "/{id}")
    public ResponseGlobal<Annotations> updateAnnotation(@PathVariable("id") String id, @RequestBody Annotations annotation) {
        return annotationService.update(id, annotation);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseGlobal<Id> deleteAnnotation(@PathVariable("id") String id) {
        return annotationService.delete(id);
    }


}
