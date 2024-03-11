package com.ddinnovations.loadsystem.domain.entity.params;

import com.ddinnovations.loadsystem.domain.entity.response.Params;
import lombok.*;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class ParamsUser extends Params {
    private String email;

    public ParamsUser(int page, int limit, Sort sort, String filterCriteriaText, String email) {
        super(page, limit, sort, filterCriteriaText);
        this.email = email;
    }
}
