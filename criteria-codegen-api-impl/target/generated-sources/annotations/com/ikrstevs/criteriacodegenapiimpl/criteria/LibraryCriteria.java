package com.ikrstevs.criteriacodegenapiimpl.criteria;

import com.ikrstevs.criteriacodegenapi.filter.*;
import lombok.Data;
@Data
public class LibraryCriteria{

private StringFilter city;
private InstantFilter yearFounded;
private LongFilter id;
}
